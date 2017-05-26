
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Chessboard extends JFrame {
	Game game;
	TableSquare square;
	ArrayList<TableSquare> lastClickedSquares = new ArrayList<>();
	short clickCounter = 0;
	Image img;
	JPanel p;
	boolean isSideWhite ;
	final short squareSize = 75;
	short queueCounter = 0;
	boolean normalPaintTraverse = true;
	int[][] tableAsNumber; //representation of the table with numbers
	TableSquare[][] tableAsSquare ; //representation of the table with Squares
	
	
	public Chessboard(Game game) {
		this.game = game;
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		createABoard();
		setUpPieces();
		drawPieces();
		//disableBoard();
		
	}

	public void createABoard() {
		
		p = new JPanel();
		tableAsSquare = new TableSquare[8][8];
		tableAsNumber = new int[8][8];
		setTitle("Chess");
		setSize(600, 800 );
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(getInsets().top);
	
		for (int i = 0; i < 8; i++)
			for (int k = 0; k < 8; k++) {
				square = new TableSquare(i,k);
				SquareListener sl = new SquareListener(square);
				
				square.addActionListener(sl);
				tableAsSquare[i][k] = square;
				p.setLayout(null);
				square.setBounds(k * squareSize, i * squareSize, squareSize, squareSize);
				if (queueCounter % 8 == 0) 
				{
					normalPaintTraverse = !normalPaintTraverse;
					queueCounter = 0;
				}
				if (normalPaintTraverse) 
				{
					if (k % 2 == 0) 
					{
						square.setBackground(Color.BLACK);
					} 
					else 
					{
						square.setBackground(Color.WHITE);
					}
				} 
				else {
					if (k % 2 == 0)
					{
						square.setBackground(Color.WHITE);
					}
					else {
						square.setBackground(Color.BLACK);
					}
				}
				++queueCounter;
				
				p.add(square);

			}
		
		add(p,BorderLayout.CENTER);
		pack();
		setVisible(true);
	}
	//Index numbers for pieces : 
	//1:King,2:Queen,3:Bishop,4:Knight,5:Rook,6:Pawn
	//Side Indexes (white/black) : Piece index + 0(white)/1(black)
	@Override
	public Dimension getPreferredSize()
	{
		if(System.getProperty("os.name").contains("Windows"))
		return new Dimension(608,630);
		else
		return new Dimension(600,600);
	}
	void setUpPieces()
	{
		//backline of black
		tableAsSquare[0][0].onThis = new Rook(0, 0, "black", this);
		tableAsSquare[0][1].onThis = new Knight(0,1,"black",this);
		tableAsSquare[0][2].onThis = new Bishop(0, 2,"black",this);
		tableAsSquare[0][3].onThis = new Queen(0, 3,"black",this);
		tableAsSquare[0][4].onThis = new King(0, 4,"black",this);
		tableAsSquare[0][5].onThis = new Bishop(0, 5,"black",this);
		tableAsSquare[0][6].onThis = new Knight(0, 6,"black",this);
		tableAsSquare[0][7].onThis = new Rook(0,7,"black",this);
		//frontline of black
		tableAsSquare[1][0].onThis = new Pawn(1, 0,"black",this);
		tableAsSquare[1][1].onThis = new Pawn(1, 1,"black",this);
		tableAsSquare[1][2].onThis = new Pawn(1, 2,"black",this);
		tableAsSquare[1][3].onThis = new Pawn(1, 3,"black",this);
		tableAsSquare[1][4].onThis = new Pawn(1, 4,"black",this);
		tableAsSquare[1][5].onThis = new Pawn(1, 5,"black",this);
		tableAsSquare[1][6].onThis = new Pawn(1, 6,"black",this);
		tableAsSquare[1][7].onThis = new Pawn(1, 7,"black",this);
		
		//backline of white
		tableAsSquare[7][0].onThis = new Rook(7, 0, "white", this);
		tableAsSquare[7][1].onThis = new Knight(7,1,"white",this);
		tableAsSquare[7][2].onThis = new Bishop(7,2,"white",this);
		tableAsSquare[7][3].onThis = new Queen(7, 3,"white",this);
		tableAsSquare[7][4].onThis = new King(7, 4,"white",this);
		tableAsSquare[7][5].onThis = new Bishop(7,5,"white",this);
		tableAsSquare[7][6].onThis = new Knight(7,6,"white",this);
		tableAsSquare[7][7].onThis = new Rook(7,7,"white",this);
		
		//frontline of white
		
		tableAsSquare[6][0].onThis = new Pawn(6,0,"white",this);
		tableAsSquare[6][1].onThis = new Pawn(6, 1,"white",this);
		tableAsSquare[6][2].onThis = new Pawn(6, 2,"white",this);
		tableAsSquare[6][3].onThis = new Pawn(6, 3,"white",this);
		tableAsSquare[6][4].onThis = new Pawn(6, 4,"white",this);
		tableAsSquare[6][5].onThis = new Pawn(6, 5,"white",this);
		tableAsSquare[6][6].onThis = new Pawn(6, 6,"white",this);
		tableAsSquare[6][7].onThis = new Pawn(6, 7,"white",this);
	}
	
	public void drawPieces()
	{
				
		for(int i = 0 ; i < 8 ; i++)
		{	for(int k = 0 ; k < 8 ; k++)
			{
				TableSquare traver = tableAsSquare[i][k];
				if(traver.onThis != null)
				{
					traver.setIcon(traver.onThis.icon);
				}
				else
				{
					traver.setIcon(null);
				}
										
			}
		 	
		}
	}
	

	public class SquareListener implements ActionListener {
		TableSquare square;
		int kind;
		public SquareListener(TableSquare _square)
		{
			
			square = _square;
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			lastClickedSquares.add(square);		
			System.out.println(square.x + " " + square.y);
			game.mvmsg = "mv" + square.x + "" + square.y;
			if(square.onThis != null)
			{
				        square.onThis.clearTheList();
					square.onThis.showWhereCanItGo();
				
			
			}
			
			
				
			if(lastClickedSquares.size() != 1)
				{
				Piece actioner = lastClickedSquares.get(lastClickedSquares.size()-2).onThis;
				//System.out.println("Last's " + lastClickedSquares.get(lastClickedSquares.size()-2).x + " " + lastClickedSquares.get(lastClickedSquares.size()-2).y);
				//Drawing and clearing blue paths
				if(square.onThis == null  && lastClickedSquares.get(lastClickedSquares.size()-2).onThis != null && !lastClickedSquares.get(lastClickedSquares.size()-2).onThis.getAvailablePosList().contains(square))
					{clearFromBlues();}
						
				else if(square.onThis == null && actioner != null && actioner.getAvailablePosList().contains(square) )
				{
					System.out.println("gitmesi gerek!");
					clearFromBlues();
					square.onThis = returnNewVictor(actioner, square.x, square.y);
					actioner = null;
					lastClickedSquares.get(lastClickedSquares.size()-2).onThis = null;
					
					lastClickedSquares.clear();
					drawPieces();
                                        clearFromBlues();
				}
				else if(square.onThis != null  && lastClickedSquares.get(lastClickedSquares.size()-2).onThis != null && !lastClickedSquares.get(lastClickedSquares.size()-2).onThis.getAvailablePosList().contains(square))
					{
					clearFromBlues();//System.out.println("need to clear from blues!");
					//lastClickedSquares.clear();
					square.onThis.showWhereCanItGo();
					}
				else if(square.onThis != null  && lastClickedSquares.get(lastClickedSquares.size()-2).onThis != null && lastClickedSquares.get(lastClickedSquares.size()-2).onThis.getAvailablePosList().contains(square)
						&& !lastClickedSquares.get(lastClickedSquares.size()-2).onThis.side.equalsIgnoreCase(square.onThis.side))
				{
				clearFromBlues();
				System.out.println(lastClickedSquares.get(lastClickedSquares.size()-2).onThis.type + " is gonna eat that motherfucker!");
				
				
				square.onThis = null;
				square.onThis = returnNewVictor(lastClickedSquares.get(lastClickedSquares.size()-2).onThis, square.x, square.y);
				
				lastClickedSquares.get(lastClickedSquares.size()-2).onThis = null;
				
				lastClickedSquares.clear();
				drawPieces();
				}

				else if(square.onThis != null  && lastClickedSquares.get(lastClickedSquares.size()-2).onThis != null && lastClickedSquares.get(lastClickedSquares.size()-2).onThis.getAvailablePosList().contains(square))
					{
					clearFromBlues();
					square.onThis.showWhereCanItGo();
					}
			
			    //End drawing and clearing blue paths
				
				
				}
			else
				{
				//System.out.println("Last's " + lastClickedSquares.get(lastClickedSquares.size()-1).x + " " + lastClickedSquares.get(lastClickedSquares.size()-1).y);
				
					
				}
			
			++clickCounter;
			//System.out.println("Piece on it : " + square.onThis.x + " " + square.onThis.y);
		}
		
	}
	public void clearFromBlues()
	{
		
		boolean normalPaintTraverse2 = true;
		int queueCounter2 = 0;
	
		for (int i = 0; i < 8; i++)
			for (int k = 0; k < 8; k++) {
				 
							
				if (queueCounter2 % 8 == 0) 
				{
					normalPaintTraverse2 = !normalPaintTraverse2;
					queueCounter2 = 0;
				}
				if (normalPaintTraverse2) 
				{
					if (k % 2 == 0) 
					{
						tableAsSquare[i][k].setBackground(Color.BLACK);
					} 
					else 
					{
						tableAsSquare[i][k].setBackground(Color.WHITE);
					}
				} 
				else {
					if (k % 2 == 0)
					{
						tableAsSquare[i][k].setBackground(Color.WHITE);
					}
					else {
						tableAsSquare[i][k].setBackground(Color.BLACK);
					}
				}
				++queueCounter2;
				
				

			}
		

	}
	public Piece returnNewVictor(Piece victor,int X , int Y)
	
	{
		
		String TYPE = victor.type;
		String SIDE = victor.side;
		Piece answer = null;
		switch(TYPE)
		{
		case "bishop":
		{
		  answer = new Bishop(X,Y,SIDE,this);
		  break;
		}
		case "queen":
		{
		  answer = new Queen(X,Y,SIDE,this);
		  break;
		}
		case "rook":
		{
		  answer = new Rook(X,Y,SIDE,this);
		  break;
		}
		case "knight":
		{
		  answer = new Knight(X,Y,SIDE,this);
		  break;
		}
		case "king":
		{
		  answer = new King(X,Y,SIDE,this);
		  break;
		}
		case "pawn":
		{
		  answer = new Pawn(X,Y,SIDE,this);
		  break;
		}
		
		}
		game.changeTurn();
		
		return answer;
		
	}
	
	public void disableBoard()
	{
		for (int i = 0; i < 8; i++)
			for (int k = 0; k < 8; k++)
				tableAsSquare[i][k].setEnabled(false);
	}
	public void enableBoard()
	{
		for (int i = 0; i < 8; i++)
			for (int k = 0; k < 8; k++)
				tableAsSquare[i][k].setEnabled(true);
	}
	

}
