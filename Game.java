package sinanchess12apr;

public class Game {
	private ServerPlayer server;
	private ClientPlayer client;
	private Chessboard cb;
	String mvmsg = "";
	String whoIsThis;
	String whoseTurn = "white";
	public Game(String serverIP) //client side constructor
	{
		client = new ClientPlayer(this,serverIP);
		whoIsThis = "client";
	}
	public Game(){
		server = new ServerPlayer(this,10002);
		whoIsThis = "server";
	};//standart constructor for server side.
	
	public void enableChessboard()
	{
		cb.enableBoard();
	}
	public void disableChessboard()
	{
		cb.disableBoard();
	}
	public Chessboard getBoard()
	{
		return cb;
	}
	
	public void startGame()
	{
		cb = new Chessboard(this);
		if(client != null)
			client.start();
		else
			server.start();
	}
	public void changeTurn()
	{
		if(whoseTurn.equals("white"))
			whoseTurn = "black";
		else
			whoseTurn = "white";
		
	}
	

}
