package seabattlegui;

import com.google.gson.Gson;
import seabattlegame.websocket.server.EventServerSocket;
import seabattlegame.websocket.shared.dto.OpponentFiresShot;
import seabattlegame.websocket.shared.dto.SetName;
import seabattlegame.websocket.shared.dto.ShowSquare;

public class SeaBattleGuiCommunicationToClient implements ISeaBattleGUI{

    public SeaBattleGuiCommunicationToClient() {
    }

    @Override
    public void setPlayerName(int playerNr, String name) {
        SetName setName = new SetName(playerNr, name, true);
        String message = new Gson().toJson(setName);    // TODO use converter
        EventServerSocket.broadcastMessage(message);
    }

    @Override
    public void setOpponentName(int playerNr, String name) {
        SetName setName = new SetName(playerNr, name, false);
        String message = new Gson().toJson(setName);
        EventServerSocket.broadcastMessage(message);
    }

    @Override
    public void opponentFiresShot(int playerNr, ShotType shotType) {
        OpponentFiresShot opponentFiresShot = new OpponentFiresShot(playerNr, shotType);
        String message = new Gson().toJson(opponentFiresShot);
        EventServerSocket.broadcastMessage(message);
    }

    @Override
    public void showSquarePlayer(int playerNr, int posX, int posY, SquareState squareState) {
        ShowSquare showSquare = new ShowSquare(playerNr, posX, posY, squareState, true);
        String message = new Gson().toJson(showSquare);
        EventServerSocket.broadcastMessage(message);
    }

    @Override
    public void showSquareOpponent(int playerNr, int posX, int posY, SquareState squareState) {
        ShowSquare showSquare = new ShowSquare(playerNr, posX, posY, squareState, false);
        String message = new Gson().toJson(showSquare);
        EventServerSocket.broadcastMessage(message);
    }
}
