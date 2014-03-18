package pl.pawluczuk.monika.monacs.model.commands;

import java.io.BufferedReader;
import java.io.StringReader;

import pl.pawluczuk.monika.monacs.model.MonacsCommand;
import pl.pawluczuk.monika.monacs.model.MonacsContext;


/**
 * Komenda pozwalajaca uzytkownikowi na znajdowanie tekstu w wybranej karcie.
 * @author monika_pawluczuk
 *
 */
public class Find implements MonacsCommand
{
	@Override
	public String executeCommand(String[] arguments, MonacsContext context) 
	{
		StringBuilder results = new StringBuilder();
		String fileName = arguments[0];
		String keyword = arguments[1];
		int lineCounter = 0;
		try 
		{
			context.dehighlight(fileName);
			BufferedReader reader = new BufferedReader(new StringReader(context.getPage(fileName)));
			String line;
			while ((line = reader.readLine()) != null)
			{	
				int start = 0;
				  while (true) 
				  {
				    int found = line.indexOf(keyword, start);
				    if (found != -1) 
				    {
				    	results.append("\nLine: " + lineCounter + " Column: " + found);
				    	context.highlight(fileName, found, found + keyword.length(), lineCounter);
				    }
				    else 
				    {
				    	break;
				    }
				    start = found + 2;
				  }
				  lineCounter++;
			}
			results.trimToSize();
		}
		catch (Exception ne)
		 {
			 return "No such tab!";
		 }
		if (results.length() < 1)
			return "No occurences found.";
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
		return "find file-name string";
	}

}
