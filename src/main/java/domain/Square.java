package domain;

import java.util.List;

public class Square {
    private int posY;
    private int posX;

    public Square(int posY, int posX) {
        this.posY = posY;
        this.posX = posX;
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
