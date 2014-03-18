package pl.pawluczuk.monika.monacs.model.commands;
import java.io.*;

import pl.pawluczuk.monika.monacs.model.*;


/**
 * Komenda pozwalajaca uzytkownikowi na zapisanie otawrtych plikow.
 * @author monika_pawluczuk
 *
 */
public class SaveDoc implements MonacsCommand 
{

	@Override
	public String executeCommand(String[] arguments, MonacsContext context)
	{
		for (String arg : arguments)
			{
				String fileName = arg;
				Writer writer = null;
				try 
				{
					writer = new FileWriter(fileName);
					context.saveToStream(fileName, writer);
					
				} catch (IOException e)
				{
					return "File save error";
				}
				catch (IllegalArgumentException e)
				{
					return "Invalid tab!";
				}
				finally 
				{
					try 
					{
						if (writer != null)
						writer.close();
					} catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			}
		return "Saved " + arguments.length + " document(s).";
	}

	@Override
	public int getMinArgumentsCount() 
	{
		return 1;
	}

	@Override
	public String getUsage() 
	{
		return "savedoc file-name [file-name-2] ... [file-name-n]";
	}

}
