package pl.pawluczuk.monika.monacs.model;

import java.util.*;

import pl.pawluczuk.monika.monacs.util.ReflectionHelper;

/**
 * Klasa zajmujaca sie obsluga polecen
 */
public class CommandProcessor 
{
	private static final String COMMANDS_PACKAGE = "pl.pawluczuk.monika.monacs.model.commands";
	private static boolean isInitialized = false;
	private static Map<String, MonacsCommand> commandMap = new HashMap<String, MonacsCommand>();
	
	/**
	 * Uzywajac odzwierciedlania, przeszukuje pakiet zawierajacy komendy i tworzy mape przypoprzadkowujaca
	 * nazwie komendy jej wystapienie (instancje)
	 */
	private static void initialize()
	{
		try
		{
			ArrayList<Class<?>> klasy = ReflectionHelper.getClasses(COMMANDS_PACKAGE);
			for (Class<?> klasa : klasy)
			{
				if (MonacsCommand.class.isAssignableFrom(klasa))
				{
					MonacsCommand command = (MonacsCommand) klasa.newInstance();
					String commandName = klasa.getSimpleName().toLowerCase(Locale.getDefault());
					commandMap.put(commandName, command);
				}
			}
			isInitialized = true;
		} catch (Exception e)
		{
			return;
		}
	}
	
	public static String resolve(String arg, MonacsContext context)
	{
		if (arg.equals("$"))
		{
			int activePageIndex = context.getActivePage();
			if (activePageIndex == -1)
				return arg;
			return resolve("$" + activePageIndex, context);
		}
		if (arg.startsWith("$"))
		{
			try 
			{
				int pageIndex = Integer.parseInt(arg.substring(1));
				return context.getPageName(pageIndex);
			}
			catch (NumberFormatException numberFormatException) 
			{
				return arg;
			}
		}
		return arg;
	}
	
	public synchronized static String process(String commandText, MonacsContext context)
	{
		//podzielmy string na tablice stringow po spacji
		if (!isInitialized)
		{
			initialize();
		}
		
		String[] commandArray = commandText.split(" "); // TODO regex do rozdzielania 
		// antlr 
		if (commandArray.length == 0 || commandArray[0].equals(""))
		{
			return "";
		}
		String commandName = commandArray[0].toLowerCase();
		if (!commandMap.containsKey(commandName))
		{
			return "Unrecognized command: " + commandName;
		}
		MonacsCommand command = commandMap.get(commandName);
		if (command.getMinArgumentsCount() > commandArray.length - 1)
		{
			return "Usage: " + command.getUsage();
		}
		
		String[] args = Arrays.copyOfRange(commandArray, 1, commandArray.length);
		ArrayList<String> filteredArgs = new ArrayList<String>();
		for (String arg : args) 
		{
			filteredArgs.add(resolve(arg, context));
		}
		
		return command.executeCommand(filteredArgs.toArray(new String[args.length]), context);
	}
	

}
