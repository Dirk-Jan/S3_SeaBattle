package domain;

import seabattlegui.ShipType;

import java.util.List;

public abstract class ship {
    protected int length;
    protected ShipType shipType;
    private List<Square> location;

    public void setLocation(List<Square> location) {
        this.location = location;
    }

    public List<Square> getLocation() {
        return location;
    }
}
