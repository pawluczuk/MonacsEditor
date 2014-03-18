package pl.pawluczuk.monika.monacs.model.commands;

import java.io.*;

import pl.pawluczuk.monika.monacs.model.MonacsCommand;
import pl.pawluczuk.monika.monacs.model.MonacsContext;


/**
 * Komenda pozwalajaca uzytkownikowi na zamiane danego lancucha w tekscie danego pliku na nowy.
 * @author monika_pawluczuk
 *
 */
public class Replace implements MonacsCommand
{

	@Override
	public String executeCommand(String[] arguments, MonacsContext context) 
	{
		StringBuilder results = new StringBuilder();
		StringBuilder newText = new StringBuilder();
		String fileName = arguments[0];
		String keyword = arguments[1];
		String newWord = arguments[2];
		int lineCounter = 0;
		try {
			context.dehighlight(fileName);
			BufferedReader reader = new BufferedReader(new StringReader(context.getPage(fileName)));
			String line;
			while ((line = reader.readLine()) != null)
			{	
				int start = 0;
				  while (true) {
				    int found = line.indexOf(keyword, start);
				    if (found != -1) 
				    {
				    	results.append("\nLine: " + lineCounter + " Column: " + found);
				    }
				    else 
				    {
				    	break;
				    }
				    start = found + 2;
				  }
				  newText.append(line.replace(keyword, newWord) +"\n");
				  lineCounter++;
			}
			results.trimToSize();
			newText.trimToSize();
			context.replace(fileName, newText.toString());
		}
		catch (Exception ne)
		 {
			 return "No such tab!";
		 }
		if (results.length() < 1)
			return "No occurences found to be replaced.";
		else
			return results.toString();
	}

	@Override
	public int getMinArgumentsCount() 
	{
		return 2;
	}

	@Override
	public String getUsage() 
	{
		return "replace file-name original-string new-string";
	}

}
