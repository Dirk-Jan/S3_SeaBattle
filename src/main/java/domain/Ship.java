package domain;

import seabattlegui.ShipType;

import java.util.List;

public abstract class Ship {
    protected int length;
    protected ShipType shipType;
    private List<Square> location;
    private boolean sunk = false;

    public boolean isSunk() {
        return sunk;
    }

    public void setSunk(boolean sunk) {
        this.sunk = sunk;
    }

    public void setLocation(List<Square> location) {
        this.location = location;
    }

    public List<Square> getLocation() {
        return location;
    }
}
