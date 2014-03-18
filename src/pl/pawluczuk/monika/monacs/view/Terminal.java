package pl.pawluczuk.monika.monacs.view;

import java.text.MessageFormat;

import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;

/**
 * Tworzy okno terminala.
 * @author monika_pawluczuk
 *
 */
public class Terminal extends JTextArea implements DocumentListener 
{

	private static final long serialVersionUID = 1L;
	private TerminalListener tListener;
	private CommandLineFilter filter;
	private String prompt;
	public Terminal(TerminalListener listener, String prompt)
	{
		super();
		tListener = listener;
		filter = new CommandLineFilter();
		((AbstractDocument)getDocument()).setDocumentFilter(filter);
		this.prompt = prompt;
		setText(prompt);
		filter.setPromptPosition(prompt.length());
		getDocument().addDocumentListener(this);
	}
	
	@Override
	public void append(String str)
	{
		
		super.append(MessageFormat.format("{0}{1}{2}", str, System.getProperty("line.separator"), prompt));
		filter.setPromptPosition(getDocument().getLength());
	}
	
	@Override
	public void changedUpdate(DocumentEvent arg0) 
	{

	}
	
	@Override
	public void insertUpdate(DocumentEvent arg0) 
	{
		int documentLength = getDocument().getLength();
		try {
			if (documentLength > 0 && getText(documentLength - 1, 1).equals("\n"))
			{   
				String line = getText(filter.promptPosition, documentLength - filter.promptPosition - 1);
			    if (tListener != null)
			    	tListener.commandRequested(line);
				filter.setPromptPosition(documentLength);
			}
		} catch (BadLocationException e) 
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) 
	{
		
	}
	
}
