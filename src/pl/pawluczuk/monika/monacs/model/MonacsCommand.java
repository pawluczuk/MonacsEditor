package pl.pawluczuk.monika.monacs.model;
/**
 * Interfejs implementowany przez wszystkie komendy.
 */
public interface MonacsCommand 
{
	/**
	 * Wykonuje komende z przekazanymi argumentami
	 * @param arguments
	 * @param context pozwala na kontrolowanie widoku
	 * @return
	 */
	String executeCommand(String[] arguments, MonacsContext context);

	/**
	 * Informuje o minimalnej liczbie argumentow potrzebnej do wywolania komendy.
	 * @return minimalna liczba argumentow
	 */
	int getMinArgumentsCount();

	/**
	 * Podaje informacje o sposobie wywolywania komendy, wyswietlana w przypadku przekazania niewystarczajacej liczby argumentow
	 * @return String z opisem wywolania komendy
	 */
	String getUsage();
}
