package seabattleserver;

import seabattlegame.rest.server.RESTServer;
import seabattlegame.websocket.server.WebsocketServer;

public class server {
    // REST server
    // Websocket server
    public static void main(String[] args){

        RESTServer restServer = new RESTServer();
        WebsocketServer websocketServer = new WebsocketServer();


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                restServer.run();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                websocketServer.run();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
