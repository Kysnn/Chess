
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientPlayer extends Thread {

	private String lastMessage = "";
	private Game game;
	private InetAddress ip;
	private DatagramSocket clientSocket;
    
	public ClientPlayer(Game game, String ip) {
		
		try {
			this.game = game;
			this.ip = InetAddress.getByName(ip);
			clientSocket = new DatagramSocket();
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		}
		this.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

		    public void uncaughtException(Thread t, Throwable e) {
		      System.err.println(t + " throws exception: " + e);
		    }
		 });
	}

	public void run() {

		sendData("selam".getBytes(), 10002);
		
		System.out.println("Client started");
		String message = "ajdaranik";
		while (true) {
			
			sendData(game.mvmsg.getBytes(), 10002);
			
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				clientSocket.receive(packet);
			} catch (IOException e) {
				System.err.println("Error while receiving packet");
			}

			message = new String(packet.getData());
			if (message.trim().equals("hgbabo")) {

				game.getBoard().enableBoard();
				System.out.println(("SERVER > " + packet.getAddress().toString() + message).trim());
			}
			if (!message.equals(lastMessage)) {

				if (message.trim().contains("mv")) {
					System.out.println(("SERVER > " + packet.getAddress().toString() + message).trim());
					String calcer1 = message.charAt(2) + "";
					String calcer2 = message.charAt(3) + "";
					game.getBoard().tableAsSquare[Integer.parseInt(calcer1)][Integer.parseInt(calcer2)]
							.getActionListeners()[0].actionPerformed(null);
				}
				
				lastMessage = message;
			}
			

			System.out.println(("SERVER > " + packet.getAddress().toString() + message).trim());
		
	
		}
	}

	public void sendData(byte[] data, int portNumber) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ip, portNumber);
		try {
			clientSocket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
