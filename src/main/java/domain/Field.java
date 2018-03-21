package domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Field {
    private List<Square> squares = new ArrayList<Square>();
    private List<Ship> ships = new ArrayList<Ship>();
    private List<Square> tempLocation;


    public Field() {

    }

    public Field(List<Square> squares, List<Ship> ships) {
        createField();
        this.ships = ships;
    }

    public List<Square> getSquares() {
        return squares;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void addShip(Ship ship){
        ships.add(ship);
    }

    public void removeShip(Ship ship){
        ships.remove(ship);
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
    private void setTempLocation(int shipLength, int posX, int posY, boolean horizontal){
        tempLocation = new ArrayList<Square>();
        for(int i = 0; i < shipLength; i++){
            if(horizontal)
                tempLocation.add(new Square(posY+i, posX));
            else
                tempLocation.add(new Square(posY, posX+i));
        }
    }

    public boolean canShipBePlaced(Ship ship, int bowX, int bowY, boolean horizontal) {
        //Step 1.) Check if ship length doesn't go out of grid
        if(horizontal){
            if(ship.length+bowX > 9){
                return false;
            }
        }
        else
            if(ship.length+bowY > 9){
                return false;
            }
        //Set temporary ship location
        setTempLocation(ship.length, bowX, bowY, horizontal);

        //Step 2.) Check if ship overlaps another ship
        for(Square square : tempLocation){
           if(shipPresentOnLocation(square.getPosX(), square.getPosY()))
               return false;
        }
        //region oude code
        // Check for coliding ships or if Ship is outside of playingfield
        //        for(int i = 0; i < ship.length; i++){
        //            if(horizontal){
        //                if(bowX + i > 10 || shipPresentOnLocation(bowX + i, bowY)){
        //                    return false;
        //                }
        //            }
        //            else{
        //                if(bowY + i > 10 || shipPresentOnLocation(bowX, bowY + i)){
        //                    return false;
        //                }
        //            }
        //        }
        //endregion

        return true;
    }

    private boolean shipPresentOnLocation(int x, int y) {
        for(Ship ship : ships){
            for(Square square : ship.getLocation()){
                if(square.getPosX() == x && square.getPosY() == y){
                    return true;
                }
            }
        }
        return false;
    }
}
