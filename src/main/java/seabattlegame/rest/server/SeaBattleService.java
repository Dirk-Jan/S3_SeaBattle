package seabattlegame.rest.server;

import seabattlegame.ISeaBattleGame;
import seabattlegame.SeaBattleGame;
import seabattlegui.ISeaBattleGUI;
import seabattlegui.SeaBattleGUIWebSocketServer;
import seabattlegui.ShipType;
import seabattlegui.ShotType;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/seabattle")
public class SeaBattleService {
    private static ISeaBattleGame game = new SeaBattleGame();

    @GET
    @Path("/registerPlayer/{name},{singlePlayerMode}")
    public Response registerPlayer(@PathParam("name") String name, @PathParam("singlePlayerMode") boolean singlePlayerMode){
        ISeaBattleGUI gui = new SeaBattleGUIWebSocketServer(0);
        int playerNr = game.registerPlayer(name, gui, singlePlayerMode);       //TODO this GUI should be a websocket
        return Response.status(200).entity(playerNr).build();
    }

    @GET
    @Path("/placeShipsAutomatically/{playerNr}")
    public Response placeShipsAutomatically(@PathParam("playerNr") int playerNr){
        boolean success = game.placeShipsAutomatically(playerNr);
        return Response.status(200).entity(success).build();
    }

    @GET
    @Path("/placeShip/{playerNr},{shipType},{bowX},{bowY},{horizontal}")
    public Response placeShip(@PathParam("playerNr") int playerNr,
                              @PathParam("shipType") ShipType shipType,
                              @PathParam("bowX") int bowX,
                              @PathParam("bowY") int bowY,
                              @PathParam("horizontal") boolean horizontal){
        boolean success = game.placeShip(playerNr, shipType, bowX, bowY, horizontal);
        return Response.status(200).entity(success).build();
    }

    @GET
    @Path("/removeShip/{playerNr},{posX},{posY}")
    public Response removeShip(@PathParam("playerNr") int playerNr, @PathParam("posX") int posX, @PathParam("posY") int posY){
        boolean success = game.removeShip(playerNr, posX, posY);
        return Response.status(200).entity(success).build();
    }

    @GET
    @Path("/removeAllShips/{playerNr}")
    public Response removeAllShips(@PathParam("playerNr") int playerNr){
        boolean success = game.removeAllShips(playerNr);
        return Response.status(200).entity(success).build();
    }

    @GET
    @Path("/notifyWhenReady/{playerNr}")
    public Response notifyWhenReady(@PathParam("playerNr") int playerNr){
        boolean ready = game.notifyWhenReady(playerNr);
        return Response.status(200).entity(ready).build();
    }

    @GET
    @Path("/fireShotPlayer/{playerNr},{posX},{posY}")
    public Response fireShotPlayer(@PathParam("playerNr") int playerNr, @PathParam("posX") int posX, @PathParam("posY") int posY){
        ShotType shotType = game.fireShotPlayer(playerNr, posX, posY);
        return Response.status(200).entity(shotType).build();
    }

    @GET
    @Path("/fireShotOpponent/{playerNr}")
    public Response fireShotOpponent(@PathParam("playerNr") int playerNr){
        ShotType shotType = game.fireShotOpponent(playerNr);
        return Response.status(200).entity(shotType).build();
    }

    @GET
    @Path("/startNewGame/{playerNr}")
    public Response startNewGame(@PathParam("playerNr") int playerNr){
        boolean success = game.startNewGame(playerNr);
        return Response.status(200).entity(success).build();
    }
}
