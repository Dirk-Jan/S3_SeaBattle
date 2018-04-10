package domain;

import seabattlegui.ISeaBattleGUI;
import seabattlegui.ShotType;

public class CPUPlayer extends Player{

    public CPUPlayer(int playerNr) {
        super(playerNr, null, "CPU");
        placeShipsAutomatically();
    }

}
