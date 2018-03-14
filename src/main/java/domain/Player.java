package domain;


import seabattlegui.ShipType;
import seabattlegui.ShotType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {

    private int playerNr;
    private List<ship> ships;

    private boolean readyToStart = false;

    public boolean isReadyToStart() {
        return readyToStart;
    }

    public void setReadyToStart(boolean readyToStart) {
        this.readyToStart = readyToStart;
    }

    public List<ship> getShips() {
        return ships;
    }

    public int getPlayerNr() {
        return playerNr;
    }

    public Player(int playerNr) {
        this.playerNr = playerNr;
        this.ships = fillInventoryWithShips();
    }

    private List<ship> fillInventoryWithShips(){
        List<ship> ships = new ArrayList<ship>();
        // TODO add ships
        return ships;
    }

    public void placeShipsAutomatically() {
        int randomX, randomY;
        Random randomGenerator = new Random();

        for(ship ship : ships) {
            randomX = getRandomNumber(randomGenerator, 0, 9);
            randomY = getRandomNumber(randomGenerator, 0, 9);

            while(canShipBePlaced(ship, randomX, randomY, true)){
                randomX = getRandomNumber(randomGenerator, 0, 9);
                randomY = getRandomNumber(randomGenerator, 0, 9);
            }

            placeShipOnField(ship, randomX, randomY, true);
        }
    }

    private int getRandomNumber(Random random, int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public ShotType fireShot(int posX, int posY){

        return null;
    }

    public boolean placeShip(ShipType shipType, int bowX, int bowY, boolean horizontal) {
        ship shipToPlace = null;
        for (ship ship : ships) {
            if (ShipType.AIRCRAFTCARRIER == shipType) {   // TODO change shipType.Ari.. to ship.getType()
                shipToPlace = ship;
            }
        }

        if (shipToPlace == null) {
            return false;
        }

        if (canShipBePlaced(shipToPlace, bowX, bowY, horizontal)) {
            removeShipFromInventory(shipToPlace);
            placeShipOnField(shipToPlace, bowX, bowY, horizontal);
            return true;
        }

        return false;
    }

    public void fireShot(){

    }

    public boolean placeShip(){
       return true;
    }

    private void placeShipOnField(ship ship, int bowX, int bowY, boolean horizontal) {
        // TODO add ahip to field (FIELD required)
    }

    private boolean canShipBePlaced(ship shipToPlace, int bowX, int bowY, boolean horizontal) {
        // TODO Implement canshipbeplaced algorithm (Field required)
    }


    public boolean removeShip(int posX, int posY){
        // TODO Implement remove ship (Field required)

        return false;
    }

    public void removeShipFromInventory(ship ship){
        ships.remove(ship);
    }

    public boolean removeAllShips() {
        // TODO Implement remove all ships (Field class required)
        return true;
    }

    public ShotType receiveShot(int posX, int posY) {
        // TODO Implement receiveShot
    }
}
