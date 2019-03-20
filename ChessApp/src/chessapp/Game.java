

public class Game {
	private ServerPlayer server;
	private ClientPlayer client;
	private Chessboard cb;
	String mvmsg = "";
	String whoIsThis;
	String whoseTurn = "white";
	boolean isOfflineGame = false;
	public Game(String serverIP) //client side constructor
	{
		client = new ClientPlayer(this,serverIP);
		whoIsThis = "black";
	}
	public Game(){
		server = new ServerPlayer(this,10002);
		whoIsThis = "white";
	};//standart constructor for server side.
	
	public Game(boolean value)
	{
		isOfflineGame = value;
	}
	public ServerPlayer getServer() {
		return server;
	}
	public void setServer(ServerPlayer server) {
		this.server = server;
	}
	public ClientPlayer getClient() {
		return client;
	}
	public void setClient(ClientPlayer client) {
		this.client = client;
	}
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
		try
		{
		cb = new Chessboard(this);
               // disableChessboard();
		if(client != null)
			{
			client.start();
			cb.setTitle("Client");
			}
		else
		{
			server.start();
			cb.setTitle("Server");
		}
		}
		catch (Throwable ex) {
	        System.err.println("Uncaught exception - " + ex.getMessage());
	        ex.printStackTrace(System.err);
	    }
	}
	public void changeTurn()
	{
		if(whoseTurn.equals("white"))
			whoseTurn = "black";
		else
			whoseTurn = "white";
		
	}
	

}
