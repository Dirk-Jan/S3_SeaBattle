package seabattlegame;

import seabattlegui.ISeaBattleGUI;
import seabattlegui.ShipType;
import seabattlegui.ShotType;

public class FakeSeaBattleGame implements ISeaBattleGame {
    @Override
    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {
        return 0;
    }

    @Override
    public boolean placeShipsAutomatically(int playerNr) {
        return false;
    }

    @Override
    public boolean placeShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal) {
        return false;
    }

    @Override
    public boolean removeShip(int playerNr, int posX, int posY) {
        return false;
    }

    @Override
    public boolean removeAllShips(int playerNr) {
        return false;
    }

    @Override
    public boolean notifyWhenReady(int playerNr) {
        return false;
    }

    @Override
    public ShotType fireShotPlayer(int playerNr, int posX, int posY) {
        return null;
    }

    @Override
    public ShotType fireShotOpponent(int playerNr) {
        return null;
    }

    @Override
    public boolean startNewGame(int playerNr) {
        return false;
    }
}
