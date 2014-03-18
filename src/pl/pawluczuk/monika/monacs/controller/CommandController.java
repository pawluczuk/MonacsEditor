package pl.pawluczuk.monika.monacs.controller;

import java.util.concurrent.*;

import pl.pawluczuk.monika.monacs.model.CommandProcessor;
import pl.pawluczuk.monika.monacs.view.*;


/**
 * Pobiera zadania od uzytkownika (widoku), posredniczy w przekazywaniu polecen do modelu i odbiera informacje zwrotne
 * @author monika_pawluczuk
 *
 */
public class CommandController 
{
	private MainView view;
	private Thread producer;
	private Thread consumer; //!!
	private BlockingQueue<String> queue;
	private boolean consumerActive;
	
	public CommandController(MainView mainView)
	{
		view = mainView;
		queue = new LinkedBlockingQueue<String>();
		
		consumer = new Thread( new Runnable() 
		{
			@Override
			public void run() 
			{
				while(consumerActive)
				{
					try 
					{
						view.commandExecuted(CommandProcessor.process(queue.take(), view.getContext()));						
					} 
					catch (InterruptedException e) 
					{
						return;
					}
				}
			}
		} );
	}
	
	/**
	 * Tworzy watek do przetwarzania komend.
	 */
	public void open()
	{
		consumerActive = true;
		consumer.start();
	}
	
	/**
	 * Dodaje wywolana przez uzytkownika metode na kolejke.
	 * @param cmd
	 */
	public void enqueue(String cmd)
	{
		queue.add(cmd);
	}
	
	/**
	 * Zamyka watek do przetwarzania komend.
	 */
	public void close()
	{
		consumerActive = false;
		enqueue("");
	}
	
}
