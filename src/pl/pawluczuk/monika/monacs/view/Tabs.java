package pl.pawluczuk.monika.monacs.view;

import java.awt.Color;
import java.awt.Component;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import pl.pawluczuk.monika.monacs.model.MonacsContext;


/**
 * Tworzy panel z kartami.
 * @author monika_pawluczuk
 *
 */
public class Tabs extends JTabbedPane implements MonacsContext 
{

	private static final long serialVersionUID = 1L;
	private AppContext appContext;
	
	public Tabs(AppContext appC)
	{
		appContext = appC;
	}

	@Override
	public void addPage(String fileName) throws IOException 
	{
		JTextArea jTextArea = new JTextArea();
		JScrollPane tabScroller = new JScrollPane(jTextArea);
		tabScroller.getViewport().add(jTextArea);
		tabScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		for (Component comp : getComponents())
		{
			if (getTitleAt(indexOfComponent(comp)).equals(fileName))
			{
				setSelectedComponent(comp);
				return;
			}
		}
		if (fileName != null) 
		{
			Reader reader = null;
			try 
			{
				reader = new FileReader(fileName);
				jTextArea.read(reader, null);
			} catch(IOException e) 
			{
				throw e;
			}
			finally 
			{
				if (reader != null)
				reader.close();
			}
		}
		setSelectedComponent(
				add(fileName == null ? "Untitled" + getComponentCount() : fileName, tabScroller));
	}

	@Override
	public void removePage(String fileName) throws Exception 
	{
		for (Component comp : getComponents())
		{
			if (getTitleAt(indexOfComponent(comp)).equals(fileName)) 
			{
				remove(comp);
			} 
		}
		throw new IllegalArgumentException();
	}
	
	@Override
	public String getPage(String fileName) 
	{
		JTextArea area = findTab(fileName);
		if (area == null)
			return null;
		return area.getText();
	}
	

	@Override
	public int getActivePage() 
	{
		return getSelectedIndex();
	}


	@Override
	public String getPageName(int pageNumber) 
	{
		if (getComponentCount() - 1 < pageNumber)
			return null;
		return getTitleAt(pageNumber);
	}
	
	/**
	 * Znajduje karte o danej nazwie pliku
	 * @param fileName (String)nazwa pliku
	 * @return JTextArea
	 */
	private JTextArea findTab(final String fileName) 
	{
		for (Component comp : getComponents())
		{
			if (getTitleAt(indexOfComponent(comp)).equals(fileName)) 
			{
				return ((JTextArea)((JScrollPane)comp).getViewport().getComponent(0));
			} 
		}
		throw new IllegalArgumentException();
	}
	
	@Override
	public void saveToStream(final String tabName, Writer writer) throws IOException 
	{
		try 
		{
			JTextArea area = findTab(tabName);
			area.write(writer);
		}
		catch (IllegalArgumentException e) 
		{
			throw e;
		}
		catch (IOException e) 
		{
			throw e;
		}
	}

	@Override
	public void exitApp() 
	{
		appContext.requestExit();
	}
	
	HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.PINK);

	@Override
	public void dehighlight(final String fileName)
	{
		JTextArea ourTab = findTab(fileName);
		Highlighter hilighter = ourTab.getHighlighter();
		if (hilighter == null) 
		{
			return;
		}
		hilighter.removeAllHighlights();
	}
	

	@Override
	public void highlight(final String fileName, final int startOffset, final int endOffset, final int lineNumber) throws IOException 
	{
		JTextArea ourTab = findTab(fileName);
		Highlighter hilighter = ourTab.getHighlighter();
		if (hilighter == null)
		{
			hilighter = new DefaultHighlighter();
			ourTab.setHighlighter(hilighter);
		}
		try 
		{
			int newStartOffset = ourTab.getLineStartOffset(lineNumber) + startOffset;
			int newEndOffset = ourTab.getLineStartOffset(lineNumber) + endOffset;
			ourTab.select(newStartOffset, newEndOffset);
			hilighter.addHighlight(newStartOffset, newEndOffset, painter);
		} catch (BadLocationException e) 
		{
			return;
		}
	}

	@Override
	public void replace(final String fileName, String newText) 
	{
		JTextArea ourTab = findTab(fileName);
		ourTab.setText(newText);
	}

}
