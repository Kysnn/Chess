


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerPlayer extends Thread {
	
    private String lastMessage = "";
	private Game game;
	private DatagramSocket serverSocket;
	
	public ServerPlayer(Game game,int socket)
	{
		this.game = game;
		try {
			serverSocket = new DatagramSocket(socket);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		this.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

		    public void uncaughtException(Thread t, Throwable e) {
		      System.err.println(t + " throws exception: " + e);
		    }
		 });
	}
	
	public void run()
	{   
		String message  = "ajdaranik";
		System.out.println("Server Started");
		
		
		
		while(true)
		{
			
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data,data.length);
			try {
				serverSocket.receive(packet);
			} catch (Exception e) {
				e.printStackTrace();
			}
                        
			 message = new String(packet.getData());
                         if(message.trim().equals("selam"))
			{
				sendData("hgbabo".getBytes(),packet.getPort(),packet.getAddress());
				game.getBoard().enableBoard();
				System.out.println(("CLIENT > " + packet.getAddress().toString() + message).trim());
			}
			if(!message.equals(lastMessage))
                        {

                            if(message.trim().contains("mv"))
                            {
                                    System.out.println(("CLIENT > " + packet.getAddress().toString() + message).trim());
                                    String calcer1 = message.charAt(2) + "";
                                    String calcer2 = message.charAt(3) + "";
                                    game.getBoard().tableAsSquare[Integer.parseInt(calcer1)][Integer.parseInt(calcer2)].getActionListeners()[0].actionPerformed(null);
                            }
                            lastMessage = message;
                        }
                       
                            
			
			System.out.println(("CLIENT > " + packet.getAddress().toString() + message).trim());
			sendData(game.mvmsg.getBytes(),packet.getPort(),packet.getAddress());
		
		}
		
              
	}
	public void sendData(byte[] data,int portNumber,InetAddress ip)
	{
		DatagramPacket packet = new DatagramPacket(data,data.length,ip,portNumber);
		try {
			serverSocket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
