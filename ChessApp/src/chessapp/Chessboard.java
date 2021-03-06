

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
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Chessboard extends JFrame {
	Game game;
	TableSquare square;
	JTextField infoField;
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
	Piece checker = null;
	
	
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
		setResizable(true);
		infoField = new JTextField(20);
		infoField.setSize(200,30);
		infoField.setBounds(10,700,200,30);
		infoField.setText("Game begins, white starts");
		infoField.setEnabled(false);
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
		add(infoField,BorderLayout.SOUTH);
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
		return new Dimension(615,670);
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
					
						clearFromBlues();
						if(isThereCheck())
						{
							Piece temp = lastClickedSquares.get(lastClickedSquares.size()-2).onThis;
							square.onThis = returnNewVictor(actioner, square.x, square.y);
							lastClickedSquares.get(lastClickedSquares.size()-2).onThis = null;
							if(!isThereCheck())
							{
								actioner = null;
								lastClickedSquares.clear();
								clearFromBlues();
								drawPieces();
								if(isThereCheck())
								{
									if(endOfTheGame())
									{      JOptionPane.showMessageDialog(null,"Checkmate! "+checker.side+" wins!");
										disableBoard();
									}
									isThereCheck();
								}
							}
							else
							{
								square.onThis = null;
								lastClickedSquares.get(lastClickedSquares.size()-2).onThis = temp;
							}
							
						}
						else if(!isThereCheck())
						{
							Piece temp = lastClickedSquares.get(lastClickedSquares.size()-2).onThis;
							square.onThis = returnNewVictor(actioner, square.x, square.y);
							lastClickedSquares.get(lastClickedSquares.size()-2).onThis = null;
							if(!isThereCheck() ||(isThereCheck() && checker.side.equals(square.onThis.side)))
							{
								actioner = null;
								lastClickedSquares.get(lastClickedSquares.size()-2).onThis = null;
								lastClickedSquares.clear();
								clearFromBlues();
								drawPieces();
								if(isThereCheck())
								{
									if(endOfTheGame())
									{        JOptionPane.showMessageDialog(null,"Checkmate! "+checker.side+" wins!");
										disableBoard();
									}
									isThereCheck();
								}
							}
							if(isThereCheck() && !checker.side.equals(square.onThis.side))
							{
								square.onThis = null;
								if(lastClickedSquares.size() >= 2)
									lastClickedSquares.get(lastClickedSquares.size()-2).onThis = temp;
							}
							

						}
									

						
												
																
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
				if(isThereCheck())
				{
					Piece tempVictor = lastClickedSquares.get(lastClickedSquares.size()-2).onThis;
					Piece tempVictim = square.onThis;
					square.onThis = returnNewVictor(lastClickedSquares.get(lastClickedSquares.size()-2).onThis, square.x, square.y);
					lastClickedSquares.get(lastClickedSquares.size()-2).onThis = null;
					if(!isThereCheck())
					{
												
						lastClickedSquares.clear();
						drawPieces();
						if(isThereCheck())
						{
							if(endOfTheGame())
							{  
                                                         JOptionPane.showMessageDialog(null,"Checkmate! "+checker.side+" wins!");
								disableBoard();
							}
							isThereCheck();
						}
					}
					else if(isThereCheck() && tempVictor.type.equals("king") )
					{         JOptionPane.showMessageDialog(null,"Checkmate! "+checker.side+" wins!");
						disableBoard();
					}
					else if(isThereCheck() && !tempVictor.type.equals("king") )
					{
						square.onThis = tempVictim;
						if(lastClickedSquares.size() >= 2)
							lastClickedSquares.get(lastClickedSquares.size()-2).onThis = tempVictor;
					}
					
				}
				else if(!isThereCheck())
				{
					Piece tempVictor = lastClickedSquares.get(lastClickedSquares.size()-2).onThis;
					Piece tempVictim = square.onThis;
					square.onThis = returnNewVictor(lastClickedSquares.get(lastClickedSquares.size()-2).onThis, square.x, square.y);
					lastClickedSquares.get(lastClickedSquares.size()-2).onThis = null;
					if(!isThereCheck() ||(isThereCheck() && checker.side.equals(square.onThis.side)))
					{
						
						lastClickedSquares.clear();
						clearFromBlues();
						drawPieces();
						if(isThereCheck())
						{
							if(endOfTheGame())
							{  JOptionPane.showMessageDialog(null,"Checkmate! "+checker.side+" wins!");
                                                        
								disableBoard();
							}
							isThereCheck();
						}
					}
					if(isThereCheck() && !checker.side.equals(square.onThis.side))
					{
						square.onThis = tempVictim;
						if(lastClickedSquares.size() >= 2)
							lastClickedSquares.get(lastClickedSquares.size()-2).onThis = tempVictor;
					}

				}
							

				
				
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
		  if(X == 0 || X == 7)
			  answer = new Queen(X,Y,SIDE,this);
		  else
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
	
	public boolean isThereCheck()
	{
		boolean answer = false;
		TableSquare whiteKingThrone = null;
		TableSquare blackKingThrone = null;
		
		
		
		for(int i = 0 ; i < 8 ; ++i)
		{
			for(int k = 0 ; k < 8 ; ++k)
			{
		         if(tableAsSquare[i][k].onThis != null && tableAsSquare[i][k].onThis.type.equals("king"))
		         {
		        	 if(tableAsSquare[i][k].onThis.side.equals("black"))
		        	 {
		        		 blackKingThrone = tableAsSquare[i][k];
		        		 
		        	 }
		        	 else if(tableAsSquare[i][k].onThis.side.equals("white"))
		        	 {
		        		 whiteKingThrone = tableAsSquare[i][k];
		        		 
		        	 }
		         }
			}
		}
		//System.out.println(whiteKingThrone.x + " " + whiteKingThrone.y + " white king dwells here");
		//System.out.println(blackKingThrone.x + " " + blackKingThrone.y + " black king dwells here");
		
		for(int i = 0 ; i < 8 ; ++i)
		{
			for(int k = 0 ; k < 8 ; ++k)
			{
				if(tableAsSquare[i][k].onThis != null)
				{
					Piece currentPiece = tableAsSquare[i][k].onThis;
					//System.out.println(currentPiece.side + " " + currentPiece.type);
					currentPiece.calculateWhereCanItGo();
										
					if(currentPiece.side.equals("black") && currentPiece.getAvailablePosList().contains(whiteKingThrone))
						{
							infoField.setText(currentPiece.side + " " + currentPiece.type + " is threatening the king!");
							checker = currentPiece;
							answer = true;
							break;
						}
					
					if(currentPiece.side.equals("white") && currentPiece.getAvailablePosList().contains(blackKingThrone))
					{
						infoField.setText(currentPiece.side + " " + currentPiece.type + " is threatening the king!");
						checker = currentPiece;
						answer = true;
						break;
					}
					
				}

			}
		}
		if(!answer)
		{
			infoField.setText("");
		}
		return answer;
	}
	public Piece findVictimKing()
	{
		String colorOfVictim = "";
		if(checker.side.equals("white"))
			colorOfVictim = "black";
		else if(checker.side.equals("black"))
			colorOfVictim = "white";
		Piece victimKing = null;
		
		//Find the king which is in trouble.
		for(int i = 0 ; i < 8 ; ++i)
		{
			for(int k = 0 ; k < 8 ; ++k)
			{
				if(tableAsSquare[i][k].onThis != null && tableAsSquare[i][k].onThis.type.equals("king") && tableAsSquare[i][k].onThis.side.equals(colorOfVictim))
				{
					victimKing = tableAsSquare[i][k].onThis;
					break;
				}
			}
		}
		return victimKing;
	}
    public boolean endOfTheGame() {
    	
            	
    		boolean result = true;
    		Piece victimKing = null;
    		
    		victimKing = findVictimKing();
    		
    		Piece copyVictimKing = new King(victimKing.x,victimKing.y,victimKing.side,this);
    		victimKing.calculateWhereCanItGo();
    		
    		//If the king can eat the checker then it is not checkmate , but the checker is guarded with another piece then it is checkmate.
    		if(victimKing.availablePos.contains(tableAsSquare[checker.x][checker.y]))
    		{
				Piece tempVictor = victimKing;
				Piece tempVictim = checker;
				
				tableAsSquare[tempVictim.x][tempVictim.y].onThis = returnNewVictor(victimKing,tempVictim.x,tempVictim.y);
				tableAsSquare[tempVictor.x][tempVictor.y].onThis = null;
							
				if(isThereCheck())
				{
					System.err.println("KORUMALI MAT!!!");
					return true;
				}
				else
				{
					tableAsSquare[tempVictim.x][tempVictim.y].onThis = checker;
					tableAsSquare[tempVictor.x][tempVictor.y].onThis = victimKing;
					result = false;	
				}
    			
    		}
    		//If any ally side can block the checker
    		for(int i = 0 ; i < 8 ; ++i)
    		{
    			for(int j = 0 ; j < 8 ; ++j)
    			{
    				if(tableAsSquare[i][j].onThis != null)
    				{
    					Piece current = tableAsSquare[i][j].onThis;
    					
    					if(current.side.equals(victimKing.side) && !current.type.equals("king"))
    					{
    						current.calculateWhereCanItGo();
    						int originAvilablePosSize = current.availablePos.size();
    						ArrayList<TableSquare> originList = new ArrayList<>(current.availablePos); 
    						
    						if(current.type.equals("rook"))
    						System.err.println("1Current piece size " + current.availablePos.size());
    						for(int k = 0 ; k < originAvilablePosSize ; ++k)
    						{
    							
    							if(current.type.equals("rook"))
    								System.out.println(k);
    							Piece tempCurrent = current;
    							Piece tempTarget = tableAsSquare[originList.get(k).x][originList.get(k).y].onThis;
    							if(tempTarget != null)
    							System.out.println(tempTarget.side + " " + tempTarget.type);
    							int targetX = originList.get(k).x;
    							int targetY = originList.get(k).y;
    							
    							tableAsSquare[originList.get(k).x][originList.get(k).y].onThis = tempCurrent;
    							//tableAsSquare[tempCurrent.x][tempCurrent.y].onThis = null;
    							
    							if(isThereCheck())
    							{
    								if(current.type.equals("rook"))
    								System.err.println("2Current piece size " + current.availablePos.size());
    								tableAsSquare[targetX][targetY].onThis = tempTarget;
    								
    								
    								
    							}
    							else
    							{
    								System.err.println("3Current piece size " + current.availablePos.size());
    								tableAsSquare[targetX][targetY].onThis = tempTarget;
    								result = false;
    								break;
    							}
    							
    						}
    					}
    				}
    				
    			}
    		}
    		for(int i = 0 ; i < victimKing.availablePos.size() ; ++i)
    	       {
    	        Piece dumbKing = new King(victimKing.availablePos.get(i).x,victimKing.availablePos.get(i).y,copyVictimKing.side,this);
    	        tableAsSquare[victimKing.availablePos.get(i).x][victimKing.availablePos.get(i).y].onThis = dumbKing;
    	        tableAsSquare[victimKing.x][victimKing.y].onThis = null;
    	                                System.out.println("dumbking"+dumbKing.x+" "+dumbKing.y+" "+dumbKing.side+" "+dumbKing.type);
    	                              
    	        if(isThereCheck())
    	        {   
    	         tableAsSquare[dumbKing.x][dumbKing.y].onThis = null;
    	         tableAsSquare[copyVictimKing.x][copyVictimKing.y].onThis = copyVictimKing;
    	         
    	                                        
    	        }
    	        else if(!isThereCheck())
    	        {
    	         tableAsSquare[dumbKing.x][dumbKing.y].onThis = null;
    	         tableAsSquare[copyVictimKing.x][copyVictimKing.y].onThis = copyVictimKing;
    	                                       
    	         result = false;
    	         break;
    	        }
    	        
    	              
    	       }
    		
    		
    		
    		
    		
    		
    			
    		      return result;  
    		}
	

}
