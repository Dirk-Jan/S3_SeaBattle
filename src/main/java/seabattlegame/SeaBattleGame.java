/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seabattlegame;

import domain.CPUPlayer;
import domain.Player;
import domain.Square;
import seabattleai.SimpleStrategy;
import seabattlegui.ISeaBattleGUI;
import seabattlegui.ShipType;
import seabattlegui.ShotType;
import seabattlegui.SquareState;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * The Sea Battle game. To be implemented.
 * @author Nico Kuijpers
 */
public class SeaBattleGame implements ISeaBattleGame {

    private List<Player> players = new ArrayList<Player>();
    private boolean singlePlayerMode = true;

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
        this.singlePlayerMode = singlePlayerMode;
        Player player = new Player(players.size(), application);
        players.add(player);

        if(singlePlayerMode){
            players.add(new CPUPlayer(1));
        }

        return player.getPlayerNr();
    }

    @Override
    public boolean placeShipsAutomatically(int playerNr) {
        for(Player player : players){
            if(player.getPlayerNr() == playerNr) {
                player.placeShipsAutomatically();
                return true;
            }
        }
        return false;
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
//            player.setReadyToStart(true);//TODO notifywhenready: players set readysate should return a boolean. True when all ships are placed and false if not.
//            fireShotPlayer(playerNr, 0,0);

//            if(singlePlayerMode){
//                //registerPlayer("CPU", new )
//                return true;
//            } else {
//                // Check if the other player is also ready
//                if(players.get(playerNr == 0 ? 1 : 0).isReadyToStart()) {
//                    return true;
//                }
//            }
            return true;
        }
        return false;
        // Check of alle schepen zijn geplaatst
        // Check of elke speler klaar is
        // Beide ja, true terug
    }

    @Override
    public ShotType fireShotPlayer(int playerNr, int posX, int posY) {
        ShotType shotType = ShotType.MISSED;
        Player firingPlayer = getPlayerByNumber(playerNr);
        if(firingPlayer != null) {
            firingPlayer.fireShot(posX, posY);

            Player receivingPlayer = getPlayerByNumber(playerNr == 0 ? 1 : 0);
            if(receivingPlayer != null) {
                shotType = receivingPlayer.receiveShot(posX, posY);
            }
        }

        SquareState squareState = SquareState.WATER;
        if(shotType == ShotType.HIT){
            squareState = SquareState.SHOTHIT;
        }else if(shotType == ShotType.MISSED){
            squareState = SquareState.SHOTMISSED;
        }else if(shotType == ShotType.SUNK){
            squareState = SquareState.SHIPSUNK;
        }

        firingPlayer.gui.showSquareOpponent(playerNr, posX, posY, squareState);

        return shotType;

        // Check if player hits enemy and get shottype back
        // Paint squares

    }

    @Override
    public ShotType fireShotOpponent(int playerNr) {    // Only used in singleplayer, playerNr is the human Player
        ShotType shotType = ShotType.MISSED;
        Player Player = getPlayerByNumber(playerNr);
        if(Player != null) {
            Square randomSquare = new SimpleStrategy().fireShot();

            shotType = Player.receiveShot(randomSquare.getPosX(), randomSquare.getPosY());

            SquareState squareState = SquareState.WATER;
            if(shotType == ShotType.HIT){
                squareState = SquareState.SHOTHIT;
            }else if(shotType == ShotType.MISSED){
                squareState = SquareState.SHOTMISSED;
            }else if(shotType == ShotType.SUNK){
                squareState = SquareState.SHIPSUNK;
            }

            if(squareState == SquareState.SHIPSUNK){
                // Color every square squarestate SHIPSUNK
                for(Square s : Player.getShipLocation(randomSquare.getPosX(), randomSquare.getPosY())){
                    players.get(0).gui.showSquarePlayer(playerNr, s.getPosX(), s.getPosY(), squareState);
                }
            } else {
                players.get(0).gui.showSquarePlayer(playerNr, randomSquare.getPosX(), randomSquare.getPosY(), squareState);
            }

            players.get(0).gui.opponentFiresShot(0, shotType);

        }


        return shotType;
    }

    @Override
    public boolean startNewGame(int playerNr) {
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                players.get(0).gui.showSquarePlayer(0, x, y, SquareState.WATER);
                players.get(0).gui.showSquareOpponent(0, x, y, SquareState.WATER);
            }
        }
        players.get(0).gui.setPlayerName(0, "PlayerName");
        players.get(0).gui.setOpponentName(0, "OpponentName");
        players = new ArrayList<Player>();
        return true;
    }
}
