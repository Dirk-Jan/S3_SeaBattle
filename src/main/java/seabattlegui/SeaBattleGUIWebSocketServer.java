package seabattlegui;

import com.google.gson.Gson;
import seabattlegame.websocket.server.EventServerSocket;
import seabattlegame.websocket.shared.dto.OpponentFiresShot;
import seabattlegame.websocket.shared.dto.SetName;
import seabattlegame.websocket.shared.dto.ShowSquare;

public class SeaBattleGUIWebSocketServer implements ISeaBattleGUI{
    private int playerNr;


    public SeaBattleGUIWebSocketServer(int playerNr) {
        this.playerNr = playerNr;
    }

    @Override
    public void setPlayerName(int playerNr, String name) {
        SetName setName = new SetName(playerNr, name);
        String message = new Gson().toJson(setName);
        EventServerSocket.broadcastMessage(message);
    }

    @Override
    public void setOpponentName(int playerNr, String name) {
        SetName setName = new SetName(playerNr, name);
        String message = new Gson().toJson(setName);
        EventServerSocket.broadcastMessage(message);
    }

    @Override
    public void opponentFiresShot(int playerNr, ShotType shotType) {
        OpponentFiresShot opponentFiresShot = new OpponentFiresShot(playerNr, shotType);
        String message = new Gson().toJson(opponentFiresShot);
        EventServerSocket.sendMessageToPlayer(playerNr, message);
    }

    @Override
    public void showSquarePlayer(int playerNr, int posX, int posY, SquareState squareState) {
        ShowSquare showSquare = new ShowSquare(playerNr, posX, posY, squareState);
        String message = new Gson().toJson(showSquare);
        EventServerSocket.broadcastMessage(message);
    }

    @Override
    public void showSquareOpponent(int playerNr, int posX, int posY, SquareState squareState) {
        ShowSquare showSquare = new ShowSquare(playerNr, posX, posY, squareState);
        String message = new Gson().toJson(showSquare);
        EventServerSocket.broadcastMessage(message);
    }
}
