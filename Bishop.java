

import java.awt.Color;
import javax.swing.ImageIcon;

public class Bishop extends Piece {
    
    public Bishop(int X, int Y, String SIDE, Chessboard CB) {
        super(X, Y, SIDE, CB);
        this.type = "bishop";
        icon = new ImageIcon(AssetHolder.getInstance().getPiece(this.side, this.type));
    }
    
    @Override
    void calculateWhereCanItGo() {
        
        
    		int cntr = 1;
            for (int i = y + 1; i <= 7; i++) { //CrossBackRight
            	
                if (x + cntr > 7) {
                    break;
                }
                
                
                if (cB.tableAsSquare[x + cntr][i].onThis == null) {
                    availablePos.add(cB.tableAsSquare[x + cntr][i]);
                  
                } else {
                   if (!cB.tableAsSquare[x + cntr][i].onThis.side.equals(this.side))  {
                	   availablePos.add(cB.tableAsSquare[x + cntr][i]);
                    }
                    break;
                }
                
                cntr++;
                
            }


            cntr = 1;
            for (int i = y - 1; i >= 0; i--) { //CrossBackLeft
            	
                if (x + cntr > 7) {
                    break;
                }
                
                if (cB.tableAsSquare[x + cntr][i].onThis == null) {
                	availablePos.add(cB.tableAsSquare[x + cntr][i]);
                } else {
                    if (!cB.tableAsSquare[x + cntr][i].onThis.side.equals(this.side)) {
                    	availablePos.add(cB.tableAsSquare[x + cntr][i]);
                    }
                    break;
                }

                cntr++;
            }
            
            
            cntr = 1;
            for (int i = y + 1; i <= 7; i++) { //CrossFrontRight
            	
                if (x - cntr < 0) {
                    break;
                }
               
                if (cB.tableAsSquare[x - cntr][i].onThis == null) {
                	availablePos.add(cB.tableAsSquare[x - cntr][i]);
                } else {
                    if (!cB.tableAsSquare[x - cntr][i].onThis.side.equals(this.side)) {
                    	availablePos.add(cB.tableAsSquare[x - cntr][i]);
                    }
                    break;
                }
                cntr++;
            }
            
            cntr = 1;
            for (int i = y - 1; i >= 0; i--) { //CrossFrontLeft  
            	
                if (x - cntr <  0) {
                    break;
                }
                
                if (cB.tableAsSquare[x - cntr][i].onThis == null) {
                	availablePos.add(cB.tableAsSquare[x - cntr][i]);
                } else {
                    if (!cB.tableAsSquare[x - cntr][i].onThis.side.equals(this.side)) {
                    	availablePos.add(cB.tableAsSquare[x - cntr][i]);
                    }
                    break;
                }
                cntr++;
            }
        
    }
    
    @Override
    void move() {
        
    }
    
}
