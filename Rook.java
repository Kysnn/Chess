

import java.awt.Color;
import javax.swing.ImageIcon;

public class Rook extends Piece {

    public Rook(int X, int Y, String SIDE, Chessboard CB) {
        super(X, Y, SIDE, CB);
        this.type = "rook";
        icon = new ImageIcon(AssetHolder.getInstance().getPiece(this.side, this.type));
    }

    @Override
    void calculateWhereCanItGo() {

        //limit to up
        for (int i = x - 1; i >= 0; i--) {
            if (cB.tableAsSquare[i][y].onThis != null) {
                if (!cB.tableAsSquare[i][y].onThis.side.equals(this.side)) {
                    availablePos.add(cB.tableAsSquare[i][y]);
                }
                break;
            } else {
            	availablePos.add(cB.tableAsSquare[i][y]);
            }

        }
        //limit to down

        for (int i = x + 1; i <= 7; i++) {
            if (cB.tableAsSquare[i][y].onThis != null) {
                if (!cB.tableAsSquare[i][y].onThis.side.equals(this.side)) {
                	availablePos.add(cB.tableAsSquare[i][y]);
                }
                break;
            } else {
            	availablePos.add(cB.tableAsSquare[i][y]);
            }

        }

        //limit to right
        for (int i = y + 1; i <= 7; i++) {
            if (cB.tableAsSquare[x][i].onThis != null) {
                if (!cB.tableAsSquare[x][i].onThis.side.equals(this.side)) {
                    availablePos.add(cB.tableAsSquare[x][i]);
                }
                break;
            } else {
            	availablePos.add(cB.tableAsSquare[x][i]);
            }

        }
        //limit to left
            for (int i = y - 1; i >= 0; i--) {

                if (cB.tableAsSquare[x][i].onThis != null) {
                    if (!cB.tableAsSquare[x][i].onThis.side.equals(this.side)) {
                    	availablePos.add(cB.tableAsSquare[x][i]);
                    }
                    break;
                } else {
                	availablePos.add(cB.tableAsSquare[x][i]);
                }

            }
        

    }

    @Override
    void move() {

    }

}
