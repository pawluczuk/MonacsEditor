package pl.pawluczuk.monika.monacs;
import pl.pawluczuk.monika.monacs.view.MainView;


public class Monacs 
{
	private Monacs() 
	{
		
	}
	
	public static void main(String[] arguments)
	{
		/*BlockingQueue<Command> bq = new LinkedBlockingQueue<Command>();
		MainView view = new MainView(bq);
		CommandController ctrl = new CommandController(mainView, bq);
		ctrl.start(); */
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                MainView mainView = new MainView();
                mainView.setVisible(true);
            }
        });
	}
}
