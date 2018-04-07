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
    @OnOpen
    public void onWebSocketConnect() {
        System.out.println("[Connected]");
    }
    @OnMessage
    public void onWebSocketText(String message) {
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