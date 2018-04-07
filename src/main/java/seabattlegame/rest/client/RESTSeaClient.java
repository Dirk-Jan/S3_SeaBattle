package seabattlegame.rest.client;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOError;
import java.io.IOException;

public class RESTSeaClient {

//    class Response{
//        private String result = "na";
//
//    }

    //TODO: HIGH -> Check localhost port number
    private String standardURL =  "http://localhost:8090/seabattle";

    //TODO: MEDIUM -> Post method instead of get.. WARNING only when server is changed as well
    public int registerPlayer(String name, boolean singlePlayerMode){
        int result = -1;

        String query = String.format(standardURL + "/registerPlayer/%s,%s", name, singlePlayerMode);

        HttpGet httpget = new HttpGet(query);

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse httpResponse = httpClient.execute(httpget);

            System.out.println("[Status Line] : " + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            final String entityString = EntityUtils.toString(entity);
            System.out.println("[Entity] : " + entityString);
            Gson gson = new Gson();
            RESTLocalCalculator.Response jsonResponse = gson.fromJson(entityString,RESTLocalCalculator.Response.class);
            String stringResult = jsonResponse.getResult();
            System.out.println("[Result] : " + stringResult );
            result = Integer.parseInt(stringResult);
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

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse httpResponse = httpClient.execute(httpget);

            System.out.println("[Status Line] : " + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            final String entityString = EntityUtils.toString(entity);
            System.out.println("[Entity] : " + entityString);
            Gson gson = new Gson();
            RESTLocalCalculator.Response jsonResponse = gson.fromJson(entityString,RESTLocalCalculator.Response.class);
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
