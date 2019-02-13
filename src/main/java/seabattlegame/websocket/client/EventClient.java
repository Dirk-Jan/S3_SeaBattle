package seabattlegame.websocket.client;

import org.eclipse.jetty.util.component.LifeCycle;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;

// https://github.com/jetty-project/embedded-jetty-websocket-examples/tree/master/javax.websocket-example/src/main/java/org/eclipse/jetty/demo

/**
 * https://github.com/jetty-project/embedded-jetty-websocket-examples/
 * blob/master/javax.websocket-example/src/main/java/org/eclipse/jetty/
 * demo/EventClient.java
 *
 * @author Nico Kuijpers, copied from github, adapted by Marcel Koonen
 */
public class EventClient {
    public static void main(String[] args) {
        URI uri = URI.create("ws://localhost:8095/wstest/");
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            try {
                // Attempt Connect
                Session session = container.connectToServer(EventClientSocket.class, uri);
                // Send a message
                session.getBasicRemote().sendText("Hello");
                // Close session
                Thread.sleep(10000);
                session.close();
            } finally {
                // Force lifecycle stop when done with container.
                // This is to free up threads and resources that the
                // JSR-356 container allocates. But unfortunately
                // the JSR-356 spec does not handle lifecycles (yet)
                if (container instanceof LifeCycle) {
                    ((LifeCycle) container).stop();
                }
            }
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }
}
