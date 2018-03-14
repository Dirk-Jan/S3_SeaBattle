/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seabattlegame;

import domain.Player;
import seabattlegui.ISeaBattleGUI;
import seabattlegui.ShipType;
import seabattlegui.ShotType;

import java.util.ArrayList;
import java.util.List;

/**
 * The Sea Battle game. To be implemented.
 * @author Nico Kuijpers
 */
public class SeaBattleGame implements ISeaBattleGame {

    private List<Player> players = new ArrayList<Player>();

    private Player getPlayerByNumber(int playerNr) {
        for(Player player : players){
            if(player.getPlayerNr() == playerNr){
                return player;
            }
        }
        return null;
    }

    @Override
    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {
        Player player = new Player(players.size());
        players.add(player);
        return player.getPlayerNr();
    }

    @Override
    public boolean placeShipsAutomatically(int playerNr) {
        for(Player player : players){
            if(player.getPlayerNr() == playerNr) {
                player.placeShipsAutomatically();
            }
        }
    }

    @Override
    public boolean placeShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal) {
        Player player = getPlayerByNumber(playerNr);
        if(player != null) {
            return player.placeShip(shipType, bowX, bowY, horizontal);
        }
        return false;
    }

    @Override
    public boolean removeShip(int playerNr, int posX, int posY) {
        Player player = getPlayerByNumber(playerNr);
        if(player != null) {
            return player.removeShip(posX, posY);
        }
        return false;
    }

    @Override
    public boolean removeAllShips(int playerNr) {
        Player player = getPlayerByNumber(playerNr);
        if(player != null) {
            return player.removeAllShips();
        }
        return false;
    }

    @Override
    public boolean notifyWhenReady(int playerNr) {
        Player player = getPlayerByNumber(playerNr);
        if(player != null) {
            player.setReadyToStart(true);
        }
    }

    @Override
    public ShotType fireShotPlayer(int playerNr, int posX, int posY) {
        Player firingPlayer = getPlayerByNumber(playerNr);
        if(firingPlayer != null) {
            firingPlayer.fireShot(posX, posY);

            Player receivingPlayer = getPlayerByNumber(playerNr == 0 ? 1 : 0);
            if(receivingPlayer != null) {
                return receivingPlayer.receiveShot(posX, posY);
            }
        }
        return ShotType.MISSED;
    }

    @Override
    public ShotType fireShotOpponent(int playerNr) {    // Only used in singleplayer, playerNr is the human Player
        Player Player = getPlayerByNumber(playerNr);
        if(Player != null) {
            // TODO use strategy here and fire shot at Player
            return Player.receiveShot(0,0);
        }
        return ShotType.MISSED;
    }

    @Override
    public boolean startNewGame(int playerNr) {
        players = new ArrayList<Player>();
        // TODO Probably need to close and reopen some GUIs too
    }
}
