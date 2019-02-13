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

    protected Field field;

    public ISeaBattleGUI getGui() {
        return gui;
    }

    private ISeaBattleGUI gui;

    private String name;

    public String getName() {
        return name;
    }

    public boolean isReadyToStart() {
        return readyToStart;
    }

    public boolean setReadyToStart(boolean readyToStart) {
        this.readyToStart = readyToStart;
        return this.readyToStart;
    }

    public List<Ship> getShipsToPlace() {
        return shipsToPlace;
    }

    public int getPlayerNr() {
        return playerNr;
    }

    public Player(int playerNr, ISeaBattleGUI gui, String name) {
        this.playerNr = playerNr;
        this.shipsToPlace = fillInventoryWithShips();
        this.field = new Field();
        this.gui = gui;
        this.name = name;
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



    public void placeShipsAutomatically() {
        int randomX, randomY, randomHor;
        Random randomGenerator = new Random();

        for(int i = 0; i < shipsToPlace.size()+i; i++){
        //for(Ship ship : shipsToPlace) {
            randomX = getRandomNumber(randomGenerator, 0, 9);
            randomY = getRandomNumber(randomGenerator, 0, 9);
            randomHor = getRandomNumber(randomGenerator, 0,1);

            boolean horizontal = randomHor==0? true: false;
            while(!canShipBePlaced(shipsToPlace.get(0), randomX, randomY, horizontal)){
                randomX = getRandomNumber(randomGenerator, 0, 9);
                randomY = getRandomNumber(randomGenerator, 0, 9);
            }

            placeShip(shipsToPlace.get(0).shipType, randomX, randomY, horizontal);
            //placeShipOnField(shipsToPlace.get(0), randomX, randomY, horizontal);
        }
    }

//region oude code
    //        int randomX, randomY, horizontal;
//        Random randomGenerator = new Random();
//        boolean howToPlace;
//
//        //Step 1.) Horizontal or vertical placement?
//        horizontal = getRandomNumber(randomGenerator, 0, 1);
//
//        howToPlace = (horizontal==0) ? true : false;
//
//        //Step 2.) Create random coordinates
//        //Step 3.) Check if ship length doesn't go out of grid
//        //Step 4.) Check if ship overlaps another ship
//
//
//
//        for(Ship ship : shipsToPlace) {
//            randomX = getRandomNumber(randomGenerator, 0, 9);
//            randomY = getRandomNumber(randomGenerator, 0, 9);
//
//            while(canShipBePlaced(ship, randomX, randomY, true)){
//                randomX = getRandomNumber(randomGenerator, 0, 9);
//                randomY = getRandomNumber(randomGenerator, 0, 9);
//            }
//
//            placeShipOnField(ship, randomX, randomY, true);
//        }
// endregion


    private int getRandomNumber(Random random, int min, int max) { return random.nextInt((max - min) + 1) + min; }

    public ShotType fireShot(int posX, int posY){

        return null;
    }


    //Mention left upper corner means place 0, grid goes from 0 to 9.
    //TODO: prio=low -> maybe improve upper instruction

    //place ship checks wether the ship can be placed or not
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
                if(gui != null) {
                    gui.showSquarePlayer(this.playerNr, bowX, bowY, SquareState.SHIP);
                }
                location.add(new Square(bowY, bowX));
                bowX++;
            }
            else{
                if(gui != null) {
                    gui.showSquarePlayer(this.playerNr, bowX, bowY, SquareState.SHIP);
                }
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
        if(field.getShips().size() > 0){
            for (int q = 0; field.getShips().size() != 0; q++) {
                Ship ship = field.getShips().get(0);

                removeShip(ship.getLocation().get(0).getPosX(), ship.getLocation().get(0).getPosY());
            }
            return true;
        }
        return false;
    }


    public ShotType receiveShot(int posX, int posY) {
//        boolean shotHit = field.shipPresentOnLocation(posX, posY);
//        return field.registerShot(posX, posY);
        ShotType shotType = field.registerShot(posX, posY);
        if(shotType == ShotType.SUNK){

        }
        return shotType;
//        return shotHit ? ShotType.HIT : ShotType.MISSED;
    }

    public List<Square> getShipLocation(int x, int y){
        return field.getShipLocation(x, y);
    }

    public boolean shipPresentOnLocation(int x, int y){
        return field.shipPresentOnLocation(x, y);
    }
}
