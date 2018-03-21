package domain;


import seabattlegui.ISeaBattleGUI;
import seabattlegui.ShipType;
import seabattlegui.ShotType;
import seabattlegui.SquareState;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {

    private int playerNr;
    private List<Ship> shipsToPlace;

    private boolean readyToStart = false;

    private Field field;

    private ISeaBattleGUI gui;

    public boolean isReadyToStart() {
        return readyToStart;
    }

    public void setReadyToStart(boolean readyToStart) {
        this.readyToStart = readyToStart;
    }

    public List<Ship> getShipsToPlace() {
        return shipsToPlace;
    }

    public int getPlayerNr() {
        return playerNr;
    }

    public Player(int playerNr, ISeaBattleGUI gui) {
        this.playerNr = playerNr;
        this.shipsToPlace = fillInventoryWithShips();
        this.field = new Field();
        this.gui = gui;
    }

    //if inventory missing shipsToPlace we have to make the button unclickable
    private List<Ship> fillInventoryWithShips(){
        List<Ship> ships = new ArrayList<Ship>();
        ships.add(new AircraftCarrier());
        ships.add(new BattleShip());
        ships.add(new Cruiser());
        ships.add(new MineSweeper());
        ships.add(new Submarine());
        return ships;
    }

    //hij mag geen schepen buiten het bord plaatsen want dan krijg je sws een error
    //afvangen watvoor schip het is, daarnaast kijken of het horizontaal of verticaal geplaatst kan worden
    public void placeShipsAutomatically() {
        int randomX, randomY;
        Random randomGenerator = new Random();

        for(Ship ship : shipsToPlace) {
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


    //Mention left upper corner means place 0, grid goes from 0 to 9.
    //TODO: prio=low -> maybe improve upper instruction
    public boolean placeShip(ShipType shipType, int bowX, int bowY, boolean horizontal) {
        Ship shipToPlace = null;
        List<Square> location = new ArrayList<Square>();

        for (Ship ship : shipsToPlace) {
            if (ship.shipType == shipType) {
                shipToPlace = ship;
            }
        }
        //why null?
        if (shipToPlace == null) {
            return false;
        }
        else{
//            for(int i =0; i < shipToPlace.length; i++){
//                if(horizontal){
//                    gui.showSquarePlayer(this.playerNr, bowX,bowY, SquareState.SHIP);
//                    location.add(new Square(bowY, bowX));
//                    bowX++;
//                }
//                else{
//                    gui.showSquarePlayer(this.playerNr, bowX,bowY, SquareState.SHIP);
//                    location.add(new Square(bowY, bowX));
//                    bowY++;
////                    removeShipFromInventory(shipToPlace); //cannot place another Ship like this
//                }
//
//            }
//            removeShipFromInventory(shipToPlace); //cannot place another Ship like this
//
//            shipToPlace.setLocation(location);
//            field.addShip(shipToPlace);

            placeShipOnField(shipToPlace, bowX, bowY, horizontal);
        }

        //hier gaat wat fout geeft errors
        if (canShipBePlaced(shipToPlace, bowX, bowY, horizontal)) {
           //removeShipFromInventory(shipToPlace);
           //placeShipOnField(shipToPlace, bowX, bowY, horizontal);
            return true;
        }

        return true; //false;
    }

    public void fireShot(){

    }

    private void placeShipOnField(Ship ship, int bowX, int bowY, boolean horizontal) {
//        gui.showSquarePlayer(this.playerNr, bowX, bowY, SquareState.SHIP);
        List<Square> location = new ArrayList<Square>();
        for(int i =0; i < ship.length; i++){
            if(horizontal){
                gui.showSquarePlayer(this.playerNr, bowX,bowY, SquareState.SHIP);
                location.add(new Square(bowY, bowX));
                bowX++;
            }
            else{
                gui.showSquarePlayer(this.playerNr, bowX,bowY, SquareState.SHIP);
                location.add(new Square(bowY, bowX));
                bowY++;
            }

        }
        removeShipFromInventory(ship); //cannot place another Ship like this

        ship.setLocation(location);
        field.addShip(ship);
    }

    private boolean canShipBePlaced(Ship shipToPlace, int bowX, int bowY, boolean horizontal) {
        return field.canShipBePlaced(shipToPlace, bowX, bowY, horizontal);
    }


    //TODO: Fix squarestate change on click
    public boolean removeShip(int posX, int posY){
        Square squareToCheck = new Square(posY, posX);

        for(int q = 0; q < field.getShips().size(); q++){
            Ship ship = field.getShips().get(q);
            //ship still needs a location
            for(int i = 0; i < ship.getLocation().size(); i++){

                if(ship.getLocation().get(i).getPosX() ==  squareToCheck.getPosX() && ship.getLocation().get(i).getPosY() ==  squareToCheck.getPosY()){
                    for(int k = 0; k < ship.getLocation().size(); k++){
                        gui.showSquarePlayer(this.playerNr, ship.getLocation().get(k).getPosX(), ship.getLocation().get(k).getPosY(), SquareState.WATER);

                    }
                    field.removeShip(field.getShips().get(q));
                    addShiptoInventory(ship);
                    return true;
                }
            }
        }
        return false;
    }



    public void removeShipFromInventory(Ship ship){
        shipsToPlace.remove(ship);
    }

    private void addShiptoInventory(Ship ship){
        shipsToPlace.add(ship);
    }

    // TODO: prio=medium -> remove all ships at a time
    public boolean removeAllShips() {
        for (Ship ship : field.getShips()) {
            //removeShip(ship.getLocation().get().getPosX(), ship.getLocation().get().getPosY());
            for(int i = 0; i < ship.getLocation().size(); i++){
                removeShip(ship.getLocation().get(i).getPosX(), ship.getLocation().get(i).getPosY());
            }
        }

        return true;
    }


    public ShotType receiveShot(int posX, int posY) {
        // TODO Implement receiveShot
        throw new NotImplementedException();
    }
}
