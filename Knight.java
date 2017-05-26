
import java.awt.Color;
import javax.swing.ImageIcon;

public class Knight extends Piece {

    public Knight(int X, int Y, String SIDE, Chessboard CB) {
        super(X, Y, SIDE, CB);
        this.type = "knight";
        icon = new ImageIcon(AssetHolder.getInstance().getPiece(this.side, this.type));
    }
    
    @Override
    void calculateWhereCanItGo() {
    	
        if ((x - 2 <= 7 && x - 2 >= 0) && (y + 1 <= 7 && y + 1 >= 0)) //FrontRight
        {
            if (cB.tableAsSquare[x - 2][y + 1].onThis == null) {
                availablePos.add(cB.tableAsSquare[x - 2][y + 1]);
            } else if (!cB.tableAsSquare[x - 2][y + 1].onThis.side.equals(this.side)) {
            	availablePos.add(cB.tableAsSquare[x - 2][y + 1]);
            }
        }

        if ((x - 2 <= 7 && x - 2 >= 0) && (y - 1 <= 7 && y - 1 >= 0)) //FrontLeft
        {
            if (cB.tableAsSquare[x - 2][y - 1].onThis == null) {
                availablePos.add(cB.tableAsSquare[x - 2][y - 1]);
            } else if (!cB.tableAsSquare[x - 2][y - 1].onThis.side.equals(this.side)) {
            	availablePos.add(cB.tableAsSquare[x - 2][y - 1]);
            }
        }
        if ((x - 1 <= 7 && x - 1 >= 0) && (y + 2 <= 7 && y + 2 >= 0)) //RightFront
        {
            if (cB.tableAsSquare[x - 1][y + 2].onThis == null) {
                availablePos.add(cB.tableAsSquare[x - 1][y + 2]);
            } else if (!cB.tableAsSquare[x - 1][y + 2].onThis.side.equals(this.side)) {
            	availablePos.add(cB.tableAsSquare[x - 1][y + 2]);
            }
        }
        if ((x - 1 <= 7 && x - 1 >= 0) && (y - 2 <= 7 && y - 2 >= 0)) //LeftFront
        {
            if (cB.tableAsSquare[x - 1][y - 2].onThis == null) {
                availablePos.add(cB.tableAsSquare[x - 1][y - 2]);
            } else if (!cB.tableAsSquare[x - 1][y - 2].onThis.side.equals(this.side)) {
            	availablePos.add(cB.tableAsSquare[x - 1][y - 2]);
            }
        }
        if ((x + 2 <= 7 && x + 2 >= 0) && (y - 1 <= 7 && y - 1 >= 0)) //BackLeft
        {
            if (cB.tableAsSquare[x + 2][y - 1].onThis == null) {
                availablePos.add(cB.tableAsSquare[x + 2][y - 1]);
            } else if (!cB.tableAsSquare[x + 2][y - 1].onThis.side.equals(this.side)) {
            	availablePos.add(cB.tableAsSquare[x + 2][y - 1]);
            }
        }
        if ((x + 2 <= 7 && x + 2 >= 0) && (y + 1 <= 7 && y + 1 >= 0))//BackRight
        {
            if (cB.tableAsSquare[x + 2][y + 1].onThis == null) {
                availablePos.add(cB.tableAsSquare[x + 2][y + 1]);
            } else if (!cB.tableAsSquare[x + 2][y + 1].onThis.side.equals(this.side)) {
            	availablePos.add(cB.tableAsSquare[x + 2][y + 1]);
            }
        }
        if ((x + 1 <= 7 && x + 1 >= 0) && (y + 2 <= 7 && y + 2 >= 0))//RightBack
        {
            if (cB.tableAsSquare[x + 1][y + 2].onThis == null) {
                availablePos.add(cB.tableAsSquare[x + 1][y + 2]);
            } else if (!cB.tableAsSquare[x + 1][y + 2].onThis.side.equals(this.side)) {
            	availablePos.add(cB.tableAsSquare[x + 1][y + 2]);
            }
        }
        if ((x + 1 <= 7 && x + 1 >= 0) && (y - 2 <= 7 && y - 2 >= 0))//LeftBack
        {
            if (cB.tableAsSquare[x + 1][y - 2].onThis == null) {
                availablePos.add(cB.tableAsSquare[x + 1][y - 2]);
            } else if (!cB.tableAsSquare[x + 1][y - 2].onThis.side.equals(this.side)) {
                availablePos.add(cB.tableAsSquare[x + 1][y - 2]);
            }
        }

    }

    @Override
    void move() {

    }

}
