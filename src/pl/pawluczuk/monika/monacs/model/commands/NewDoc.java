package pl.pawluczuk.monika.monacs.model.commands;

import java.io.IOException;

import pl.pawluczuk.monika.monacs.model.MonacsCommand;
import pl.pawluczuk.monika.monacs.model.MonacsContext;


/**
 * Komenda pozwalajaca uzytkownikowi na stworzenie nowej karty z pustym plikiem.
 * @author monika_pawluczuk
 *
 */
public class NewDoc implements MonacsCommand 
{

	@Override
	public String executeCommand(String[] arguments, MonacsContext context) 
	{
		try 
		{
			context.addPage(null);
		}
		catch (IOException e) 
		{
			return "Could not create new file.";
		}
		return "Opened a new document.";
	}

	@Override
	public int getMinArgumentsCount() 
	{
		return 0;
	}

	@Override
	public String getUsage() 
	{
		return null;
	}

}
