
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Pawn extends Piece {

    public Pawn(int X, int Y, String SIDE, Chessboard CB) {
        super(X, Y, SIDE, CB);
        this.type = "pawn";
        icon = new ImageIcon(AssetHolder.getInstance().getPiece(this.side, this.type));
    }

    @Override
    void calculateWhereCanItGo() {

        if (cB.tableAsSquare[x][y].onThis.side.equals("white")) {      //WhitePawn
            if (cB.tableAsSquare[x][y].onThis.x == 6) {
                if (cB.tableAsSquare[x - 1][y].onThis == null) { //FirstMove
                    availablePos.add(cB.tableAsSquare[x - 1][y]);
                    if (cB.tableAsSquare[x - 2][y].onThis == null) {
                        availablePos.add(cB.tableAsSquare[x - 2][y]);
                    }
                }
                if (y != 7) {
                    if (cB.tableAsSquare[x - 1][y + 1].onThis != null && !cB.tableAsSquare[x - 1][y + 1].onThis.side.equals("white")) {
                        availablePos.add(cB.tableAsSquare[x - 1][y + 1]);
                    }
                }
                if (y != 0) {
                    if (cB.tableAsSquare[x - 1][y - 1].onThis != null && !cB.tableAsSquare[x - 1][y - 1].onThis.side.equals("white")) {
                        availablePos.add(cB.tableAsSquare[x - 1][y - 1]);
                    }
                }

            } else if (x != 0) {
                if (cB.tableAsSquare[x - 1][y].onThis == null) {
                    availablePos.add(cB.tableAsSquare[x - 1][y]);
                }

                if (y != 7) {
                    if (cB.tableAsSquare[x - 1][y + 1].onThis != null && !cB.tableAsSquare[x - 1][y + 1].onThis.side.equals("white")) {
                        availablePos.add(cB.tableAsSquare[x - 1][y + 1]);
                    }
                }
                if (y != 0) {
                    if (cB.tableAsSquare[x - 1][y - 1].onThis != null && !cB.tableAsSquare[x - 1][y - 1].onThis.side.equals("white")) {
                        availablePos.add(cB.tableAsSquare[x - 1][y - 1]);
                    }
                }

            }
        } else {                                                       //BlackPawn
            if (cB.tableAsSquare[x][y].onThis.x == 1) {
                if (cB.tableAsSquare[x + 1][y].onThis == null) { //FirtMove
                    availablePos.add(cB.tableAsSquare[x + 1][y]);
                    if (cB.tableAsSquare[x + 2][y].onThis == null) {
                        availablePos.add(cB.tableAsSquare[x + 2][y]);
                    }
                }
                if (y != 7) {
                    if (cB.tableAsSquare[x + 1][y + 1].onThis != null && !cB.tableAsSquare[x + 1][y + 1].onThis.side.equals("black")) {
                        availablePos.add(cB.tableAsSquare[x + 1][y + 1]);
                    }
                }
                if (y != 0) {
                    if (cB.tableAsSquare[x + 1][y - 1].onThis != null && !cB.tableAsSquare[x + 1][y - 1].onThis.side.equals("black")) {
                        availablePos.add(cB.tableAsSquare[x + 1][y - 1]);
                    }
                }

            } else if (x != 7) {
                if (cB.tableAsSquare[x + 1][y].onThis == null) {
                    availablePos.add(cB.tableAsSquare[x + 1][y]);
                }
                if (y != 7) {
                    if (cB.tableAsSquare[x + 1][y + 1].onThis != null && !cB.tableAsSquare[x + 1][y + 1].onThis.side.equals("black")) {
                        availablePos.add(cB.tableAsSquare[x + 1][y + 1]);
                    }
                }
                if (y != 0) {
                    if (cB.tableAsSquare[x + 1][y - 1].onThis != null && !cB.tableAsSquare[x + 1][y - 1].onThis.side.equals("black")) {
                        availablePos.add(cB.tableAsSquare[x + 1][y - 1]);
                    }
                }

            }
        }

    }

    @Override
    void move() {
        // TODO Auto-generated method stub

    }

}
