package alphamate;

import java.net.DatagramSocket;
import java.net.SocketException;

public class SocketThreadCreator extends Thread {

	Thread product = null;
	String ipForClient = " ";
	Game game = null;
	String type ;
	DatagramSocket socket ;
    public SocketThreadCreator(Game game,String ip) {
    	this.game = game;
    	ipForClient = ip;
    	type = "client";
    	try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	
	}
    public SocketThreadCreator(Game game)
    {
    	this.game = game;
    	type = "server";
    	try {
			socket = new DatagramSocket(10002);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	@Override
	public void run() {
		
		while(true)
		{

			
			System.err.println("Code is working");
			if(type.equals("server"))
			{
				product = new ServerPlayer(game,socket);
				product.start();
			}
			else if(type.equals("client"))
			{
				product = new ClientPlayer(game, ipForClient);
				product.start();
			}
			try {
				sleep(10000);
				product.interrupt();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	

}
