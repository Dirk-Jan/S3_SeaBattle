package seabattlegame;

import seabattlegame.rest.client.RESTSeaClient;
import seabattlegame.websocket.client.MessageHandler;
import seabattlegame.websocket.client.WebSocketConnectionToServer;
import seabattlegui.ISeaBattleGUI;
import seabattlegui.ShipType;
import seabattlegui.ShotType;

public class FakeSeaBattleGame implements ISeaBattleGame {
    RESTSeaClient cli = new RESTSeaClient();
    WebSocketConnectionToServer webSocketConnectionToServer = new WebSocketConnectionToServer();
    MessageHandler messageHandler = new MessageHandler();

    public FakeSeaBattleGame() {
        webSocketConnectionToServer.addObserver(messageHandler);
        webSocketConnectionToServer.createConnection();

    }

    @Override
    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {
        messageHandler.setGui(application);
        return cli.registerPlayer(name, singlePlayerMode);
    }

    @Override
    public boolean placeShipsAutomatically(int playerNr) {
        return cli.placeShipsAutomatically(playerNr);
    }

    @Override
    public boolean placeShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal) {
        return cli.placeShip(playerNr, shipType, bowX, bowY, horizontal);
    }

    @Override
    public boolean removeShip(int playerNr, int posX, int posY) {
        return cli.removeShip(playerNr, posX, posY);
    }

    @Override
    public boolean removeAllShips(int playerNr) {
        return cli.removeAllShips(playerNr);
    }

    @Override
    public boolean notifyWhenReady(int playerNr) {
        return cli.notifyWhenReady(playerNr);
    }

    @Override
    public ShotType fireShotPlayer(int playerNr, int posX, int posY) {
        return cli.fireShotPlayer(playerNr, posX, posY);
    }

    @Override
    public ShotType fireShotOpponent(int playerNr) {
        return cli.fireShotOpponent(playerNr);
    }

    @Override
    public boolean startNewGame(int playerNr) {
        return cli.startNewGame(playerNr);
    }
}
