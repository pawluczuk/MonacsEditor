package pl.pawluczuk.monika.monacs.model.commands;
import pl.pawluczuk.monika.monacs.model.MonacsCommand;
import pl.pawluczuk.monika.monacs.model.MonacsContext;

/**
 * Komenda pozwalajaca uzytkownikowi na wyjscie z programu.
 * @author monika_pawluczuk
 *
 */
public class Exit implements MonacsCommand 
{

	@Override
	public String executeCommand(String[] arguments, MonacsContext context) 
	{
		 context.exitApp();
		 return "exit";
	}

	@Override
	public int getMinArgumentsCount() 
	{
		return 0;
	}

	@Override
	public String getUsage() 
	{
		return "exit";
	}

}
