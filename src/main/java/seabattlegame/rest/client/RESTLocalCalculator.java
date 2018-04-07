package seabattlegame.rest.client;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
 * The RESTNewtonAPICalculator implements the ICalculator interface. It uses a REST Service that provides a client with the
 * absolute value for an integer number
 */
public class RESTLocalCalculator implements ICalculator {

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

    @Override
    public int abs(int value) {
        int result = Integer.MIN_VALUE;

        // Build the query for the
        //
        //  https://github.com/aunyks/newton-api
        //
        final String query = "http://localhost:8090/abs/" + Integer.toString(value);

        System.out.println("[Query] : " + query);

        // Perform the query
        HttpGet httpGet = new HttpGet(query);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpGet);)
        {
            System.out.println("[Status Line] : " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            System.out.println("[Entity] : " + entityString);
            Gson gson = new Gson();
            Response jsonResponse = gson.fromJson(entityString,Response.class);
            String stringResult = jsonResponse.getResult();
            System.out.println("[Result] : " + stringResult );
           result = Integer.parseInt(stringResult);
        } catch (IOException e) {
            // Evil, pure evil this solution: ....
            System.out.println("IOException : " + e.toString());
        }

        return result;
    }

    @Override
    public int naa(int value) {
        int result = Integer.MIN_VALUE;

        final String query = "http://localhost:8090/abs/naa/" + Integer.toString(value);

        System.out.println("[Query] : " + query);

        // Perform the query
        HttpGet httpGet = new HttpGet(query);

//        try (CloseableHttpClient httpClient = HttpClients.createDefault();
//             CloseableHttpResponse response = httpClient.execute(httpGet);)
//        {
//            System.out.println("[Status Line] : " + response.getStatusLine());
//            HttpEntity entity = response.getEntity();
//            final String entityString = EntityUtils.toString(entity);
//            System.out.println("[Entity] : " + entityString);
//            Gson gson = new Gson();
//            Response jsonResponse = gson.fromJson(entityString,Response.class);
//            String stringResult = jsonResponse.getResult();
//            System.out.println("[Result] : " + stringResult );
//            result = Integer.parseInt(stringResult);
//        } catch (IOException e) {
//            // Evil, pure evil this solution: ....
//            System.out.println("IOException : " + e.toString());
//        }

        return result;
    }
}
