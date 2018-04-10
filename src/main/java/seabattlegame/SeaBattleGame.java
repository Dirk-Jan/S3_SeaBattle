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
            players.add(new CPUPlayer(1));  // CPU has always playerNr 1, because the human player registers first
        }

        // Set name in the GUIs of the players
        if(player.getPlayerNr() == 0){
//            application.setPlayerName(0, name);
            if(singlePlayerMode){
                application.setOpponentName(0, "CPU");
            }
        }else{
//            application.setPlayerName(1, name);
            application.setOpponentName(1, players.get(0).getName());
            players.get(0).getGui().setOpponentName(0, name);
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
            if(singlePlayerMode){
                return true;
            }

            // Multiplayer, set readystate to true and check if the other player is also ready to rumble
            player.setReadyToStart(true);
            if(players.get(playerNr == 0 ? 1 : 0).isReadyToStart()){
                return true;
            }
        }
        return false;
    }

    @Override
    public ShotType fireShotPlayer(int playerNr, int posX, int posY) {
        return receiveFiredShot(playerNr == 0 ? 1 : 0, posX, posY);
    }

    @Override
    public ShotType fireShotOpponent(int playerNr) {
        Square randomSquare = new SimpleStrategy().fireShot();

        return receiveFiredShot(playerNr, randomSquare.getPosX(), randomSquare.getPosY());
    }

    private ShotType receiveFiredShot(int receivingPlayerNr, int x, int y){
        ShotType shotType = ShotType.MISSED;
        Player receivingPlayer = getPlayerByNumber(receivingPlayerNr);
        if(receivingPlayer != null) {

            shotType = receivingPlayer.receiveShot(x, y);

            SquareState squareState = SquareState.WATER;
            if(shotType == ShotType.HIT){
                squareState = SquareState.SHOTHIT;
            }else if(shotType == ShotType.MISSED){
                squareState = SquareState.SHOTMISSED;
            }else if(shotType == ShotType.SUNK){
                squareState = SquareState.SHIPSUNK;
            }

            ISeaBattleGUI receivingPlayerGui = players.get(receivingPlayerNr).getGui();
            int firingPlayerNr = receivingPlayerNr == 0 ? 1 : 0;
            Player firingPlayer = players.get(firingPlayerNr);
            ISeaBattleGUI firingPlayerGui = players.get(firingPlayerNr).getGui();

            if(squareState == SquareState.SHIPSUNK){
                // Color every square squarestate SHIPSUNK
                for(Square s : receivingPlayer.getShipLocation(x, y)){
                    if(!(receivingPlayer instanceof CPUPlayer)) {
                        receivingPlayerGui.showSquarePlayer(receivingPlayerNr, s.getPosX(), s.getPosY(), squareState);
                    }
                    if(!(firingPlayer instanceof CPUPlayer)) {
                        firingPlayerGui.showSquareOpponent(firingPlayerNr, s.getPosX(), s.getPosY(), squareState);
                    }
                }
            } else {
                // SquareState is MISSED, but ship is present on that location --> means that that ship has already sunken
                // return ShotType.SUNK from the method, the GUI will show a messagebox that the player sunk a ship, even though it was already sunken
                // So that's why we give back a shot missed and check for it here, so the ships don'' get the shot missed color on the GUI
                if(squareState == SquareState.SHOTMISSED && receivingPlayer.shipPresentOnLocation(x, y)){
                    squareState = SquareState.SHIPSUNK;
                }

                if(!(receivingPlayer instanceof CPUPlayer)) {
                    receivingPlayerGui.showSquarePlayer(receivingPlayerNr, x, y, squareState);
                }
                if(!(firingPlayer instanceof CPUPlayer)) {
                    firingPlayerGui.showSquareOpponent(firingPlayerNr, x, y, squareState);
                }
            }

            if(!(receivingPlayer instanceof CPUPlayer)) {
                receivingPlayerGui.opponentFiresShot(receivingPlayerNr, shotType);
            }

        }

        return shotType;
    }

    @Override
    public boolean startNewGame(int playerNr) {
        for(int x=0;x<10;x++){
            for(int y=0;y<10;y++){
                players.get(0).getGui().showSquarePlayer(0, x, y, SquareState.WATER);
                players.get(0).getGui().showSquareOpponent(0, x, y, SquareState.WATER);
            }
        }
//        players.get(0).gui.setPlayerName(0, "PlayerName");
//        players.get(0).gui.setOpponentName(0, "OpponentName");
        players = new ArrayList<Player>();
        return true;
    }
}
