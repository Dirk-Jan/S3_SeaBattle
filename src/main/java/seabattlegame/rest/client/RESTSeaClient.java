package seabattlegame.rest.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.javafx.scene.layout.region.Margins;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import seabattlegame.websocket.shared.dto.DTOJsonConverter;
import seabattlegame.websocket.shared.dto.OpponentFiresShot;
import seabattlegui.ShipType;
import seabattlegui.ShotType;

import java.io.IOError;
import java.io.IOException;

public class RESTSeaClient {

    class Response {

        private String operation = "n/a";
        private String expression = "n/a";
        private String result = "n/a";

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }


    //TODO: HIGH -> Check localhost port number
    private String standardURL =  "http://localhost:8090/seabattle";

    //TODO: MEDIUM -> Post method instead of get.. WARNING only when server is changed as well
    public int registerPlayer(String name, boolean singlePlayerMode){
        int result = -1;

        String query = String.format(standardURL + "/registerPlayer/%s,%s", name, singlePlayerMode);
        System.out.println("TEST FORMAT: " + query);

        HttpGet httpget = new HttpGet(query);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(httpget);)
        {
            System.out.println("[Status Line] : " + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            final String entString = EntityUtils.toString(entity);

            System.out.println("[Entity] : " + entString);

            result = Integer.parseInt(entString);
        }
        catch(Exception e){
            System.out.println(e);
        }

        return result;
    }

    public boolean placeShipsAutomatically(int playerNr){
        boolean result = false;

        String query = String.format(standardURL + "/placeShipsAutomatically/%s", playerNr);

        HttpGet httpget = new HttpGet(query);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(httpget);)
        {
            System.out.println("[Status Line] : " + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            final String entString = EntityUtils.toString(entity);

            result = Boolean.parseBoolean(entString);
        }
        catch(Exception e){
            System.out.println(e);
        }

        return result;
    }

    public boolean placeShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal){
        boolean result = false;

        String query = String.format(standardURL + "/placeShip/%s,%s,%s,%s,%s", playerNr, shipType, bowX, bowY, horizontal);

        HttpGet httpget = new HttpGet(query);

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse httpResponse = httpClient.execute(httpget);

            System.out.println("[Status Line] : " + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            final String entString = EntityUtils.toString(entity);

            result = Boolean.parseBoolean(entString);
        }
        catch(Exception e){
            System.out.println(e);
        }

        return result;
    }

    public boolean removeShip(int playerNr, int posX, int posY){
        boolean result = false;

        String query = String.format(standardURL + "/removeShip/%s,%s,%s", playerNr, posX, posY);

        HttpGet httpget = new HttpGet(query);

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse httpResponse = httpClient.execute(httpget);

            System.out.println("[Status Line] : " + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            final String entString = EntityUtils.toString(entity);

            result = Boolean.parseBoolean(entString);
        }
        catch(Exception e){
            System.out.println(e);
        }

        return result;
    }

    public boolean removeAllShips(int playerNr){
        boolean result = false;

        String query = String.format(standardURL + "/removeAllShips/%s", playerNr);

        HttpGet httpget = new HttpGet(query);

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse httpResponse = httpClient.execute(httpget);

            System.out.println("[Status Line] : " + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            final String entString = EntityUtils.toString(entity);

            result = Boolean.parseBoolean(entString);
        }
        catch(Exception e){
            System.out.println(e);
        }

        return result;
    }

    public boolean notifyWhenReady(int playerNr){
        boolean result = false;

        String query = String.format(standardURL + "/notifyWhenReady/%s", playerNr);

        HttpGet httpget = new HttpGet(query);

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse httpResponse = httpClient.execute(httpget);

            System.out.println("[Status Line] : " + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            final String entString = EntityUtils.toString(entity);

            result = Boolean.parseBoolean(entString);
        }
        catch(Exception e){
            System.out.println(e);
        }

        return result;
    }

    public ShotType fireShotPlayer(int playerNr, int posX, int posY){
        ShotType result = ShotType.MISSED;

        String query = String.format(standardURL + "/fireShotPlayer/%s,%s,%s", playerNr, posX, posY);

        System.out.println(query);
        HttpGet httpget = new HttpGet(query);

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse httpResponse = httpClient.execute(httpget);

            System.out.println("[Status Line] : " + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            final String entString = EntityUtils.toString(entity);
            System.out.println("[Entity] : " + entString);

            ShotType type = DTOJsonConverter.convertJsonToShotType(entString);

            System.out.println("[Result] : " + type );
            result = type;
        }
        catch(Exception e){
            System.out.println(e);
        }

        return result;
    }

    public ShotType fireShotOpponent(int playerNr){
        ShotType result = ShotType.MISSED;

        String query = String.format(standardURL + "/fireShotOpponent/%s", playerNr);

        HttpGet httpget = new HttpGet(query);

        System.out.println(query);

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse httpResponse = httpClient.execute(httpget);

            System.out.println("[Status Line] : " + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            final String entString = EntityUtils.toString(entity);
            System.out.println("[Entity] : " + entString);

            ShotType type = DTOJsonConverter.convertJsonToShotType(entString);

            System.out.println("[Result] : " + type );
            result = type;
        }
        catch(Exception e){
            System.out.println(e);
        }

        return result;
    }

    public boolean startNewGame(int playerNr){
        boolean result = false;

        String query = String.format(standardURL + "/startNewGame/%s,", playerNr);

        HttpGet httpget = new HttpGet(query);

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse httpResponse = httpClient.execute(httpget);

            System.out.println("[Status Line] : " + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            final String entString = EntityUtils.toString(entity);
            System.out.println("[Entity] : " + entString);
            Gson gson = new Gson();
            RESTLocalCalculator.Response jsonResponse = gson.fromJson(entString,RESTLocalCalculator.Response.class);
            String stringResult = jsonResponse.getResult();
            System.out.println("[Result] : " + stringResult );
            result = Boolean.parseBoolean(stringResult);
        }
        catch(Exception e){
            System.out.println(e);
        }

        return result;
    }

}
