package pl.pawluczuk.monika.monacs.view;

import javax.swing.text.*;

/**
 * Umozliwia zablokowanie edycji tekstu powstalego w wyniku przetwarzania poprzednich komend i biezacego tekstu prompt.
 * @author monika_pawluczuk
 *
 */
class CommandLineFilter extends DocumentFilter 
{
	
	int promptPosition = 0;
	
	/**
	 * Ustala gdzie konczy sie prompt
	 * @param promptPos pozycja na ktorej konczy sie zablokowany tekst
	 */
	public void setPromptPosition(int promptPos)
	{
		promptPosition = promptPos;
	}
	
	@Override
    public void insertString(final FilterBypass filterBypass, final int offset, final String string, final AttributeSet attributeSet)
            throws BadLocationException 
    {
        if (offset >= promptPosition) 
        {
            super.insertString(filterBypass, offset, string, attributeSet);
        }
    }
    
	@Override
    public void remove(final FilterBypass filterBypass, final int offset, final int length) 
    		throws BadLocationException 
    {
        if (offset >= promptPosition) 
        {
            super.remove(filterBypass, offset, length);
        }
    }

	@Override
    public void replace(final FilterBypass filterBypass, final int offset, final int length, final String text, final AttributeSet attributeSet)
            throws BadLocationException 
    {
        if (offset >= promptPosition) 
        {
            super.replace(filterBypass, offset, length, text, attributeSet);
        }
    }
}