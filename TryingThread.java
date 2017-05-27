package sinanchess12apr;

public class TryingThread extends Thread {
	int cnt = 0 ;
	public void run()
	{
		while(true)
		{
			
			System.out.println(++cnt);
		}
	}

}
