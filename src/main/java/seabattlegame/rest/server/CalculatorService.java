package seabattlegame.rest.server;

import com.google.gson.Gson;
import seabattlegame.rest.shared.CSAbsResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/abs")
public class CalculatorService {
    @GET
    @Path("/{value}")
    public Response getMsg(@PathParam("value") String msg) {
        CSAbsResponse response = new CSAbsResponse();
        response.setOperation("abs");
        response.setExpression(msg);
        try {
            int value = Integer.parseInt(msg);
            int result = (value<0)? -value:value;
            response.setResult(Integer.toString(result));
        } catch (NumberFormatException nfe) {
            response.setResult("invalid value");
        }
        Gson gson = new Gson();
        String output = gson.toJson(response);
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/naa/{value}")
    public Response getNaaMsg(@PathParam("value") String msg) {
        CSAbsResponse response = new CSAbsResponse();
        response.setOperation("naa");
        response.setExpression(msg);
        try {
            int value = Integer.parseInt(msg);
            int result = value - 10;
            response.setResult(Integer.toString(result));
        } catch (NumberFormatException nfe) {
            response.setResult("invalid value");
        }

        String output = new Gson().toJson(response);
        return Response.status(200).entity(output).build();
    }
}
