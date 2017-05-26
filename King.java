

import java.awt.Color;
import javax.swing.ImageIcon;

public class King extends Piece {

    public King(int X, int Y, String SIDE, Chessboard CB) {
        super(X, Y, SIDE, CB);
        this.type = "king";
        icon = new ImageIcon(AssetHolder.getInstance().getPiece(this.side, this.type));
    }

    @Override
    void calculateWhereCanItGo() {
        // TODO Auto-generated method stub

        if ((x - 1 <= 7 && x - 1 >= 0)) //StepFront
        {
            if (cB.tableAsSquare[x - 1][y].onThis == null) {
                availablePos.add(cB.tableAsSquare[x - 1][y]);
            } else if (!cB.tableAsSquare[x - 1][y].onThis.side.equals(this.side)) {
            	availablePos.add(cB.tableAsSquare[x - 1][y]);
            }
        }
        if ((x + 1 <= 7 && x + 1 >= 0))//StepBack
        {
            if (cB.tableAsSquare[x + 1][y].onThis == null) {
            	availablePos.add(cB.tableAsSquare[x + 1][y]);
            } else if (!cB.tableAsSquare[x + 1][y].onThis.side.equals(this.side)) {
            	availablePos.add(cB.tableAsSquare[x + 1][y]);
            }
        }
        if ((y + 1 <= 7 && y + 1 >= 0))//Right
        {
            if (cB.tableAsSquare[x][y + 1].onThis == null) {
                availablePos.add(cB.tableAsSquare[x][y + 1]);
            } else if (!cB.tableAsSquare[x][y + 1].onThis.side.equals(this.side)) {
            	availablePos.add(cB.tableAsSquare[x][y + 1]);
            }
        }
        if ((y - 1 <= 7 && y - 1 >= 0))//Left
        {
            if (cB.tableAsSquare[x][y - 1].onThis == null) {
                availablePos.add(cB.tableAsSquare[x][y - 1]);
            } else if (!cB.tableAsSquare[x][y - 1].onThis.side.equals(this.side)) {
            	availablePos.add(cB.tableAsSquare[x][y - 1]);
            }
        }
        if ((x - 1 <= 7 && x - 1 >= 0) && (y + 1 <= 7 && y + 1 >= 0))//FrontRight
        {
            if (cB.tableAsSquare[x - 1][y + 1].onThis == null) {
                availablePos.add(cB.tableAsSquare[x - 1][y + 1]);
            } else if (!cB.tableAsSquare[x - 1][y + 1].onThis.side.equals(this.side)) {
            	availablePos.add(cB.tableAsSquare[x - 1][y + 1]);
            }
        }
        if ((x - 1 <= 7 && x - 1 >= 0) && (y - 1 <= 7 && y - 1 >= 0))//FrontLeft
        {
            if (cB.tableAsSquare[x - 1][y - 1].onThis == null) {
                availablePos.add(cB.tableAsSquare[x - 1][y - 1]);
            } else if (!cB.tableAsSquare[x - 1][y - 1].onThis.side.equals(this.side)) {
                availablePos.add(cB.tableAsSquare[x - 1][y - 1]);
            }
        }
        if ((x + 1 <= 7 && x + 1 >= 0) && (y + 1 <= 7 && y + 1 >= 0))//BackRight
        {
            if (cB.tableAsSquare[x + 1][y + 1].onThis == null) {
                availablePos.add(cB.tableAsSquare[x + 1][y + 1]);
            } else if (!cB.tableAsSquare[x + 1][y + 1].onThis.side.equals(this.side)) {
            	availablePos.add(cB.tableAsSquare[x + 1][y + 1]);
            }

        }
        if ((x + 1 <= 7 && x + 1 >= 0) && (y - 1 <= 7 && y - 1 >= 0))//BackLeft
        {
            if (cB.tableAsSquare[x + 1][y - 1].onThis == null) {
                availablePos.add(cB.tableAsSquare[x + 1][y - 1]);
            } else if (!cB.tableAsSquare[x + 1][y - 1].onThis.side.equals(this.side)) {
            	availablePos.add(cB.tableAsSquare[x + 1][y - 1]);
            }
        }

    }

    @Override
    void move() {
        // TODO Auto-generated method stub

    }

}
