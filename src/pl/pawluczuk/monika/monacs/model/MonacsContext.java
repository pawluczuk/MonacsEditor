package pl.pawluczuk.monika.monacs.model;

import java.io.*;

/**
 * Udostepnia mozliwosc kontrolowania widoku z poziomu modelu (pomost miedzy modelem i widokiem)
 * 
 * @author monika_pawluczuk
 */
public interface MonacsContext 
{
	/**
	 * Otwiera nowa karte z JTextArea i wczytuje dane z pliku
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void addPage(String fileName) throws IOException;
	
	/**
	 * Zamyka karte o danej nazwie pliku
	 * 
	 * @param id numer karty
	 * @throws Exception
	 */
	public void removePage(String fileName) throws Exception;
	
	/**
	 * Pobiera tekst z karty o danej nazwie pliku
	 * 
	 * @param fileName
	 * @return Tekst danej karty (String)
	 * @throws IOException
	 */
	public String getPage(String fileName) throws IOException;
	
	/**
	 * Informuje ktora karta jest aktualnie na focusie
	 * @return indeks karty
	 */
	public int getActivePage();
	
	/**
	 * Informuje jaki plik jest otawrty w karcie o danym indeksie.
	 * @param i indeks karty
	 * @return nazwa pliku
	 */
	public String getPageName(int pageIndex);
	
	/**
	 * Pobiera nazwe karty - a wiec jej sciezke i zapisuje dane z tej karty do pliku.
	 * @param fileName
	 * @param writer
	 * @throws IOException
	 */
	public void saveToStream(String fileName, Writer writer) throws IOException;
	
	/**
	 * Kasuje podswietlenie z karty o danej nazwie pliku.
	 * @param fileName
	 */
	public void dehighlight(String fileName);
	
	/**
	 * Podswietla wybrany tekst w danym pliku.
	 * @param fileName
	 * @param StartOffset poczatek zaznaczenia
	 * @param EndOffset koniec zaznaczenia
	 * @param lineNumber linia w ktorej ma nastapic zaznaczenie
	 * @throws IOException
	 */
	public void highlight(String fileName, int startOffset, int endOffset, int lineNumber) throws IOException;
	
	/**
	 * Przesyla nowy tekst do karty o danej nazwie pliku.
	 * @param fileName
	 * @param newText
	 */
	public void replace(String fileName, String newText);
	
	/**
	 * Zamyka aplikacje.
	 */
	public void exitApp();
}
