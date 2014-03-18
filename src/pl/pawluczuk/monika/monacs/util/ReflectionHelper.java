package pl.pawluczuk.monika.monacs.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Klasa pozwalajaca na znalezienie wszystkich dostepnych aktualnie komend.
 * @author monika_pawluczuk
 *
 */
public class ReflectionHelper {
	private static final String CLASS_FILE_EXTENSION = ".class";

	private ReflectionHelper()
	{
		
	}
	
	/**
	 * Przeszukuje wszystkie dostepne klasy z class loadera, ktore naleza do podanego pakietu i podpakietow.
	 *
	 * @param packageName The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static ArrayList<Class<?>> getClasses(String packageName)
	        throws ClassNotFoundException, IOException 
	{
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    assert classLoader != null;
	    String path = packageName.replace('.', '/');
	    Enumeration<URL> resources = classLoader.getResources(path);
	    List<File> dirs = new ArrayList<File>();
	    while (resources.hasMoreElements()) 
	    {
	        URL resource = resources.nextElement();
	        dirs.add(new File(resource.getFile()));
	    }
	    ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
	    for (File directory : dirs) 
	    {
	        classes.addAll(findClasses(directory, packageName));
	    }
	    return classes;
	}
	
	/**
	 * Znajduje wszystkie klasy znajdujace sie w danym katalogu w danej paczce
	 * @param directory katalog do przeszukania
	 * @param packageName paczka do przeszukwania
	 * @return Lista klas
	 * @throws ClassNotFoundException
	 */
	public static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException 
	{
	    List<Class<?>> classes = new ArrayList<Class<?>>();
	    if (!directory.exists()) 
	    {
	        return classes;
	    }
	    File[] files = directory.listFiles();
	    for (File file : files) 
	    {
	        if (file.isDirectory()) 
	        {
	            assert !file.getName().contains(".");
	            classes.addAll(findClasses(file, packageName + "." + file.getName()));
	        } 
	        else if (file.getName().endsWith(CLASS_FILE_EXTENSION)) 
	        {
	            classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - CLASS_FILE_EXTENSION.length())));
	        }
	    }
	    return classes;
	}
}
