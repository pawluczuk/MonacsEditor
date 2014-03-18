package pl.pawluczuk.monika.monacs.model.commands;

import pl.pawluczuk.monika.monacs.model.MonacsCommand;
import pl.pawluczuk.monika.monacs.model.MonacsContext;

/**
 * Komenda ktora zamyka dana przez uzytkownika karte.
 * @author monika_pawluczuk
 *
 */
public class Close implements MonacsCommand 
{

	@Override
	public String executeCommand(String[] arguments, MonacsContext context) 
	{
		try 
		{
			context.removePage(arguments[0]);
		} catch (NumberFormatException e) 
		{
			return "Failed closing tab";
		} catch (Exception e) 
		{
			return "Failed closing tab";
		}
		return "Tab closed";
	}

	@Override
	public int getMinArgumentsCount() 
	{
		return 1;
	}

	@Override
	public String getUsage() 
	{
		return "close filename";
	}

}
