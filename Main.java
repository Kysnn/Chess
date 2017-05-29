package alphamate;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Chessboard cb = new Chessboard();
		try
		{
     		MainMenu mm = new MainMenu();
		}
		catch (Throwable ex) {
	        System.err.println("Uncaught exception - " + ex.getMessage());
	        ex.printStackTrace(System.err);
	    }
	}

}



