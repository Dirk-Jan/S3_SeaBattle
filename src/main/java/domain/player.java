package domain;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class player {

    public void fireShot(){

    }

    public boolean placeShip(){
       return true;
    }
    public void removeShip(){

    }

    public List<ship> getShipsFromInventory(){
        throw new NotImplementedException();
    }

    public void addShipToInventory(ship ship){

    }

    public void removeShipFromInventory(ship ship){

    }
}
