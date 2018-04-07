package seabattlegame.rest.client;

public class CalculatorClientExample {

    public static void main(String[] args) {

        //ICalculator c = new MyCalculator();
        //ICalculator c = new RESTNewtonAPICalculator();
//        ICalculator c = new RESTLocalCalculator();
//
//        System.out.println(c.naa(-123));
//        System.out.println(c.abs(1));

        RESTSeaClient cli = new RESTSeaClient();

        int test = cli.registerPlayer("Daphne", false);
        System.out.println("Output Registerplayer: " + test);
        cli.placeShipsAutomatically(1);
    }


}
