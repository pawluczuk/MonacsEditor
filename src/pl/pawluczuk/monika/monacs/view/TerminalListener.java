package pl.pawluczuk.monika.monacs.view;
/**
 * Stanowi obsluge zdarzen terminala
 * @author monika_pawluczuk
 *
 */
public interface TerminalListener
{
	/**
	 * Przekazuje komende z terminala do MainView.
	 * @param command
	 */
	public void commandRequested(String command);
}