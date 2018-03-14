/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seabattlegame;

import domain.player;
import domain.ship;
import seabattlegui.ISeaBattleGUI;
import seabattlegui.SeaBattleApplication;
import seabattlegui.ShipType;
import seabattlegui.ShotType;

import java.util.ArrayList;
import java.util.List;

/**
 * The Sea Battle game. To be implemented.
 * @author Nico Kuijpers
 */
public class SeaBattleGame implements ISeaBattleGame {

    private List<player> players = new ArrayList<player>();

    private player getPlayerByNumber(int playerNr) {
        for(player player : players){
            if(player.getPlayerNr() == playerNr){
                return player;
            }
        }
        return null;
    }

    @Override
    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {
        player player = new player(players.size());
        players.add(player);
        return player.getPlayerNr();
    }

    @Override
    public boolean placeShipsAutomatically(int playerNr) {
        for(player player : players){
            if(player.getPlayerNr() == playerNr) {
                player.placeShipsAutomatically();
            }
        }
    }

    @Override
    public boolean placeShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal) {
        player player = getPlayerByNumber(playerNr);
        if(player != null) {
            return player.placeShip(shipType, bowX, bowY, horizontal);
        }
        return false;
    }

    @Override
    public boolean removeShip(int playerNr, int posX, int posY) {
        player player = getPlayerByNumber(playerNr);
        if(player != null) {
            return player.removeShip(posX, posY);
        }
        return false;
    }

    @Override
    public boolean removeAllShips(int playerNr) {
        player player = getPlayerByNumber(playerNr);
        if(player != null) {
            return player.removeAllShips();
        }
        return false;
    }

    @Override
    public boolean notifyWhenReady(int playerNr) {
        player player = getPlayerByNumber(playerNr);
        if(player != null) {
            player.setReadyToStart(true);
        }
    }

    @Override
    public ShotType fireShotPlayer(int playerNr, int posX, int posY) {
        player firingPlayer = getPlayerByNumber(playerNr);
        if(firingPlayer != null) {
            firingPlayer.fireShot(posX, posY);

            player receivingPlayer = getPlayerByNumber(playerNr == 0 ? 1 : 0);
            if(receivingPlayer != null) {
                return receivingPlayer.receiveShot(posX, posY);
            }
        }
        return ShotType.MISSED;
    }

    @Override
    public ShotType fireShotOpponent(int playerNr) {    // Only used in singleplayer, playerNr is the human player
        player player = getPlayerByNumber(playerNr);
        if(player != null) {
            // TODO use strategy here and fire shot at player
            return player.receiveShot(0,0);
        }
        return ShotType.MISSED;
    }

    @Override
    public boolean startNewGame(int playerNr) {
        players = new ArrayList<player>();
        // TODO Probably need to close and reopen some GUIs too
    }
}
