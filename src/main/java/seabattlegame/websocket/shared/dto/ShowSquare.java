package seabattlegame.websocket.shared.dto;

import seabattlegui.SquareState;

public class ShowSquare extends DataTransferObject{
    private int playerNr, x, y;
    private SquareState squareState;

    public int getPlayerNr() {
        return playerNr;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public SquareState getSquareState() {
        return squareState;
    }

    public ShowSquare(int playerNr, int x, int y, SquareState squareState) {
        super("showsquare");
        this.playerNr = playerNr;
        this.x = x;
        this.y = y;
        this.squareState = squareState;
    }
}
