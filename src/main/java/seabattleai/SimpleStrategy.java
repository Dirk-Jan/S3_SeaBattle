package seabattleai;

import domain.Field;
import domain.Square;

import java.util.Random;

public class SimpleStrategy implements IStrategy {
    private Random random;

    public SimpleStrategy() {
        random = new Random();
    }


//    public Field fireShot(Square square) {
//        Field position = new Field();
////        SquareState state;
////        do {
////            int x = random.nextInt(square.getPosX());
////            int y = random.nextInt(square.getPosY());
////            position = square.getPosition(x,y);
////            state = position.getState();
////        } while (state == SquareState.SHOTHIT ||
////                state == SquareState.SHOTMISSED ||
////                state == SquareState.SHIPSUNK);
//
//        return position;
//    }

    public Square fireShot(){
        int min = 0, max = 9;
        int x = random.nextInt(max - min + 1) + min;
        int y = random.nextInt(max - min + 1) + min;
        return new Square(y, x);
    }

}
