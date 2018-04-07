package seabattlegame.websocket.client;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Observable;

// https://github.com/jetty-project/embedded-jetty-websocket-examples/tree/master/javax.websocket-example/src/main/java/org/eclipse/jetty/demo

/**
 * https://github.com/jetty-project/embedded-jetty-websocket-examples/blob/
 * master/javax.websocket-example/src/main/java/org/eclipse/jetty/
 * demo/EventServerSocket.java
 *
 * @author Nico Kuijpers, copied from github, adpated by Marcel Koonen
 */
@ClientEndpoint
public class EventClientSocket extends Observable{

    private static EventClientSocket instance = null;

    /**
     * Get singleton instance of this class.
     * Ensure that only one instance of this class is created.
     * @return instance of client web socket
     */
    public static EventClientSocket getInstance() {
        if (instance == null) {
            System.out.println("[WebSocket Client create singleton instance]");
            instance = new EventClientSocket();
        }
        return instance;
    }

    public EventClientSocket() {
//        System.out.println("Instance of EventCLientSocket created");
    }

    @OnOpen
    public void onWebSocketConnect() {
        System.out.println("[Connected]");
    }
    @OnMessage
    public void onWebSocketText(String message) {
//        System.out.println("Message received from server: " + message);
        setChanged();
        notifyObservers(message);
    }
    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        System.out.println("[Closed]: " + reason);
    }
    @OnError
    public void onWebSocketError(Throwable cause) {
        System.out.println("[ERROR]: " + cause.getMessage());
    }
}