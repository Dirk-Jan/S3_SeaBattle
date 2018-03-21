package domain;

import seabattlegui.SquareState;

import java.util.List;

public class Square {
    private int posY;
    private int posX;

    private SquareState squareState;

    public SquareState getSquareState() {
        return squareState;
    }

    public void setSquareState(SquareState squareState) {
        this.squareState = squareState;
    }

    public Square(int posY, int posX) {
        this.posY = posY;
        this.posX = posX;
        this.squareState = SquareState.WATER;

    }

//    public void setPosY(int posY) {
//        this.posY = posY;
//    }
//
//    public void setPosX(int posX) {
//        this.posX = posX;
//    }

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }
}
