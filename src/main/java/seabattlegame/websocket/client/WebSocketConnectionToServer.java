package seabattlegame.websocket.client;

import org.eclipse.jetty.util.component.LifeCycle;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.Observable;

public class WebSocketConnectionToServer extends Observable {

    public WebSocketConnectionToServer() {

    }

    private Session session;

    public void createConnection(){
        URI uri = URI.create("ws://localhost:8094/seabattlegame/");
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            try {
                // Attempt Connect
                session = container.connectToServer(EventClientSocket.class, uri);
                // Send a message
//                session.getBasicRemote().sendText("Hello");
                // Close session
//                    Thread.sleep(10000);
//                    session.close();
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

    public void closeConnection(){
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
