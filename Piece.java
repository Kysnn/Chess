

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.Icon;

public abstract class Piece {
	protected int x,y;
	protected String side;//white or black
	protected String type;//king queen rook or pawn etc.    
	protected Icon icon;
	protected Chessboard cB;
	protected ArrayList<TableSquare> availablePos;
	abstract void calculateWhereCanItGo();
	abstract void move();
	protected void showWhereCanItGo()
	{
		calculateWhereCanItGo();
		for(int i = 0 ; i < availablePos.size() ; ++i)
			availablePos.get(i).setBackground(Color.BLUE);
	}

	public Piece(int X,int Y,String SIDE,Chessboard CB)
	{
		x = X;y = Y;side = SIDE;cB = CB;
		availablePos = new ArrayList<>();
	}
	
	public ArrayList<TableSquare> getAvailablePosList()
	{
		return availablePos;
	}
        public void clearTheList(){        
            availablePos.clear();
        }
	
}
