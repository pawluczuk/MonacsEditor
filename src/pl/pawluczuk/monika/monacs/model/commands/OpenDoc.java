package pl.pawluczuk.monika.monacs.model.commands;

import java.io.IOException;

import pl.pawluczuk.monika.monacs.model.*;


/**
 * Otwiera nowa karte i wczytuje do niej plik z podanej sciezki
 * @author monika_pawluczuk
 *
 */
public class OpenDoc implements MonacsCommand 
{

	@Override
	public String executeCommand(String[] arguments, MonacsContext context) 
	{
		for (String arg : arguments)
			{
				try 
				{
					context.addPage(arg);
				}
				catch (IOException e) 
				{
					return "Opening file failed.";
				}
				catch (Exception ne)
				{
					return "No such tab!";
				}
			}
		return "Opened " + arguments.length + " document(s).";
	}

	@Override
	public int getMinArgumentsCount() 
	{
		return 1;
	}

	@Override
	public String getUsage() 
	{
		return "opendoc file-name [file-name-2] ... [file-name-n]";
	}

}
