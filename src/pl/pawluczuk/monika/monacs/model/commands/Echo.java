package pl.pawluczuk.monika.monacs.model.commands;

import pl.pawluczuk.monika.monacs.model.MonacsCommand;
import pl.pawluczuk.monika.monacs.model.MonacsContext;

/**
 * Komenda echo - jak w terminalu.
 * @author monika_pawluczuk
 *
 */
public class Echo implements MonacsCommand 
{

	@Override
	public String executeCommand(String[] arguments, MonacsContext context) 
	{
		return arguments[0];
	}

	@Override
	public int getMinArgumentsCount() 
	{
		return 1;
	}

	@Override
	public String getUsage() 
	{
		return "echo text";
	}

}