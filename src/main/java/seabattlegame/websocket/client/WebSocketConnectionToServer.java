package seabattlegame.websocket.client;

import org.eclipse.jetty.util.component.LifeCycle;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.Observable;

public class WebSocketConnectionToServer {

    public WebSocketConnectionToServer() {

    }

    private Session session;
    private boolean stopSocket = false;

    public void startClientSocket(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                createConnection();
            }
        }).start();
    }

    private void createConnection(){
        URI uri = URI.create("ws://localhost:8095/seabattlegame/");
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            try {
                // Attempt Connect
                session = container.connectToServer(EventClientSocket.getInstance(), uri);
                // Send a message
                session.getBasicRemote().sendText("Hello there my lovely servert <3");
                // Close session
//                    Thread.sleep(10000);
//                    session.close();
                while(!stopSocket){}
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

    public void closeConnection(){
        stopSocket = true;
    }
}
