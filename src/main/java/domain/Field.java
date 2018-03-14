package domain;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private List<Square> squares = new ArrayList<Square>();
    private List<ship> ships;

    public Field() {

    }

    public Field(List<Square> squares, List<ship> ships) {
        createField();
        this.ships = ships;
    }

    public List<Square> getSquares() {
        return squares;
    }

    public List<ship> getShips() {
        return ships;
    }

    private void createField(){
        int posY = 1;
        int posX = 0; //starts with 0 because, pos++ comes first in the loop. Otherwise it won't add the latest position

        //create all squares
        //goes from Yn and X1 to X10
        for (int i = 0; i<100; i++){
            posX++;
            Square square = new Square(posY, posX);

            squares.add(square);

            if(posX==10 && (i%10)==0){
                posX=0;
                posY++;
            }
        }

    }
}
