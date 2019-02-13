package seabattlegame.websocket.shared.dto;

import seabattlegui.SquareState;

public class ShowSquare extends DataTransferObject{
    private int x, y;
    private SquareState squareState;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public SquareState getSquareState() {
        return squareState;
    }

    public ShowSquare(int playerNr, int x, int y, SquareState squareState, boolean player) {
        super(player ? DTOType.SHOWSQUAREPLAYER : DTOType.SHOWSQUAREOPPONENT, playerNr);
        this.x = x;
        this.y = y;
        this.squareState = squareState;
    }
}
