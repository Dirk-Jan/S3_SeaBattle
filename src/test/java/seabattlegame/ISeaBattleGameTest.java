package seabattlegame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seabattlegame.exceptions.DuplicateNameException;
import seabattlegui.ISeaBattleGUI;
import seabattlegui.SeaBattleApplication;
import seabattlegui.ShipType;

import static org.junit.Assert.*;

public class ISeaBattleGameTest {

    private ISeaBattleGame game;
    private ISeaBattleGUI gui;

    @BeforeClass
    public void initializeVariables() {
        gui = new SeaBattleApplication();
    }

    @Before
    public void setUp() {
        game = new SeaBattleGame();
    }

    //region registerPlayer
    @Test
    public void registerPlayerEmptyName() {

        int playerNumber = game.registerPlayer("", gui, true);

        Assert.assertEquals("playerNumber should be -1", -1, playerNumber);
    }

    @Test
    public void registerPlayerNullName() {
        int playerNumber = game.registerPlayer(null, gui, true);

        Assert.assertEquals("playerNumber should be -1", -1, playerNumber);
    }

    @Test
    public void registerPlayerNullGui() {
        int playerNumber = game.registerPlayer("DJ", null, true);

        Assert.assertEquals("playerNumber should be -1", -1, playerNumber);
    }

    //region singleplayer
    @Test
    public void registerPlayerSinglePlayer() {
        // vars
        String name = "Dirk-Jan";
        boolean singleplayerMode = true;

        // manipulate
        int playerNumber = game.registerPlayer(name, gui, singleplayerMode);

        // check
        Assert.assertEquals("Returned playernumber should be 0", 0, playerNumber);
    }

    @Test
    public void registerPlayerSinglePlayerSecondPlayer() {

        int playerNumber1 = game.registerPlayer("DJ", gui, true);
        int playerNumber2 = game.registerPlayer("DJ2", gui, true);

        Assert.assertEquals("First playerNumber should be 0", 0, playerNumber1);
        Assert.assertEquals("Second playerNumber should be -1", -1, playerNumber2);
    }
    //endregion
    //region multiplayer
    @Test
    public void registerPlayerMultiPlayerTwoPlayers() {

        int playerNumber1 = game.registerPlayer("DJ", gui, false);
        int playerNumber2 = game.registerPlayer("DJ2", gui, false);

        Assert.assertEquals("First playerNumber should be 0", 0, playerNumber1);
        Assert.assertEquals("Second playerNumber should be 1", 1, playerNumber2);
    }

    @Test
    public void registerPlayerMultiPlayerThreePlayers() {

        int playerNumber1 = game.registerPlayer("DJ1", gui, false);
        int playerNumber2 = game.registerPlayer("DJ2", gui, false);
        int playerNumber3 = game.registerPlayer("DJ3", gui, false);

        Assert.assertEquals("First playerNumber should be 0", 0, playerNumber1);
        Assert.assertEquals("Second playerNumber should be 1", 1, playerNumber2);
        Assert.assertEquals("Third playerNumber should be -1", -1, playerNumber3);
    }

    @Test
    public void registerPlayerMultiPlayerDuplicateName() {

        int playerNumber1 = game.registerPlayer("DJ1", gui, false);
        int playerNumber2 = game.registerPlayer("DJ1", gui, false);

        Assert.assertEquals("First playerNumber should be 0", 0, playerNumber1);
        Assert.assertEquals("Second playerNumber should be -1", -1, playerNumber2);
    }
    //endregion
    //endregion

    //region placeShipsAutomatically
    @Test
    public void placeShipsAutomatically() {
        int playerNumber = game.registerPlayer("DJ1", gui, true);

        boolean allShipsPlacedSuccessfully = game.placeShipsAutomatically(playerNumber);

        Assert.assertEquals("allShipsPlacedSuccessfully should be true", true, allShipsPlacedSuccessfully);
    }

    @Test
    public void placeShipsAutomaticallyForUnregisteredPlayer() {

        boolean allShipsPlacedSuccessfully = game.placeShipsAutomatically(0);

        Assert.assertEquals("AllShipsPlacedSuccessfully should be false", false, allShipsPlacedSuccessfully);
    }

    @Test
    public void placeShipsAutomaticallyForPlayerNumberNegative() {

        boolean allShipsPlaced = game.placeShipsAutomatically(-1);

        Assert.assertEquals("AllShipsPlaced should be false", false, allShipsPlaced);
    }
    //endregion

    //region placeShip
    @Test
    public void placeShip() {
        int playerNumber = game.registerPlayer("DJ", gui, true);

        boolean shipPlaced = game.placeShip(playerNumber, ShipType.AIRCRAFTCARRIER, 0, 0, true);

        Assert.assertEquals("shipPlaced should be true", true, shipPlaced);
    }

    @Test
    public void placeShipUnregisteredPlayer() {

        boolean shipPlaced = game.placeShip(0, ShipType.AIRCRAFTCARRIER, 0 ,0, true);

        Assert.assertEquals("shipPlaced should be false", false, shipPlaced);
    }

    @Test
    public void placeShipNegativeCoordinates() {
        int playerNumber = game.registerPlayer("DJ", gui, true);

        boolean shipPlaced = game.placeShip(playerNumber, ShipType.AIRCRAFTCARRIER, -1, -1, true);

        Assert.assertEquals("shipPlaced should be false",  false, shipPlaced);
    }
    //endregion

    private int createSamplePlayer() {
        return game.registerPlayer("DJ", gui, true);
    }

    //region removeShip
    @Test
    public void removeShip() {
        int playerNumber = createSamplePlayer();
        boolean shipPlaced = game.placeShip(playerNumber, ShipType.AIRCRAFTCARRIER, 0,0,true);

        boolean shipRemoved = game.removeShip(playerNumber, 0, 0);

        Assert.assertEquals("shipPlaced should be true", true, shipPlaced);
        Assert.assertEquals("shipRemoved should be true", true, shipRemoved);
    }

    @Test
    public void removeShipWrongCoordinates() {
        int playerNumber = createSamplePlayer();
        boolean shipPlaced = game.placeShip(playerNumber, ShipType.AIRCRAFTCARRIER, 0,0,true);

        boolean shipRemoved = game.removeShip(playerNumber, 4, 4);

        Assert.assertEquals("shipPlaced should be true", true, shipPlaced);
        Assert.assertEquals("shipRemoved should be true", false, shipRemoved);
    }

    @Test
    public void removeShipNegativeCoordinates() {
        int playerNumber = createSamplePlayer();
        boolean shipPlaced = game.placeShip(playerNumber, ShipType.AIRCRAFTCARRIER, 0,0,true);

        boolean shipRemoved = game.removeShip(playerNumber, -1, -1);

        Assert.assertEquals("shipPlaced should be true", true, shipPlaced);
        Assert.assertEquals("shipRemoved should be true", false, shipRemoved);
    }
    //endregion

    //region removeAllShips
    @Test
    public void removeAllShips() {
        int playerNumber = createSamplePlayer();

        boolean shipsRemoved = game.removeAllShips(playerNumber);

        Assert.assertTrue("shipsRemoved should be true", shipsRemoved);
    }

    @Test
    public void removeAllShipsUnregisteredPlayer() {

        boolean shipsRemoved = game.removeAllShips(0);

        Assert.assertFalse("shipsRemoved should be false", shipsRemoved);
    }
    //endregion

    @Test
    public void notifyWhenReady() {
    }

    @Test
    public void fireShotPlayer() {
    }

    @Test
    public void fireShotOpponent() {
    }

    @Test
    public void startNewGame() {
    }
}