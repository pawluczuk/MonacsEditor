package pl.pawluczuk.monika.monacs.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import pl.pawluczuk.monika.monacs.controller.CommandController;
import pl.pawluczuk.monika.monacs.model.MonacsContext;


/**
 * Glowne okno programu
 * @author monika_pawluczuk
 *
 */
public class MainView extends JFrame implements TerminalListener, AppContext, WindowListener
{
	private static final int DIVIDERLOCATION = 400;
	private static final int WINDOWSIZE = 600;
	private static final String TERMINALFONT = java.awt.Font.MONOSPACED;
	private static final long serialVersionUID = 9057701788989786181L;
	public static final String PROMPT = "Monacs>";
	
	private JSplitPane splitPane;
	private MonacsContext context;
	private Terminal console;
	private Tabs tabs;
	private CommandController controller;
	private JScrollPane consoleScroller;
	private Font terminalFont;
	/**
	 * Tworzy caly UI
	 */
	public MainView()
	{	
		super("Monacs");
		initUI();
	}
	
	/**
	 * Inicjuje graficzny interfejs uzytkownka.
	 */
	private void initUI()
	{
		addWindowListener(this);
		terminalFont = new Font(TERMINALFONT, Font.BOLD, 14);
		controller = new CommandController(this);
		controller.open();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(WINDOWSIZE, WINDOWSIZE);
		console = new Terminal(this, PROMPT);
		console.setFont(terminalFont);
		console.setBackground(new Color(164, 164, 164));
		console.setForeground(Color.WHITE);
		console.setWrapStyleWord(true);
		console.setLineWrap(true);
		consoleScroller = new JScrollPane(console);
		tabs = new Tabs(this);
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabs, consoleScroller);
		splitPane.setDividerLocation(DIVIDERLOCATION);
		splitPane.setResizeWeight(1);
		getContentPane().add(splitPane);
		context = tabs;
	}
	
	/**
	 * Przekazuje wynik wykonania komendy do terminala.
	 * @param result
	 */
	public void commandExecuted(final String result)
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				console.append(result);		
				}
		});
	}

	@Override
	public void commandRequested(String command) 
	{
		controller.enqueue(command);
	}
	
	/**
	 * Udostepnia pomost miedzy modelem a widokiem.
	 * @return kontekst
	 */
	public MonacsContext getContext() 
	{
		return context;
	}

	@Override
	public void requestExit() 
	{
		controller.close();
		dispose();
	}

	@Override
	public void windowActivated(WindowEvent arg0) 
	{
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) 
	{
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		requestExit();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) 
	{
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) 
	{
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) 
	{
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) 
	{
		
	}
}
