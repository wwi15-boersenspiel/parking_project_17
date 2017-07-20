import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by lucas on 11.07.2017.
 */


public class Main {

    public static void main(String[] args) {
        socketServer.startServer();
        new socketConnectionManager().start();
        try {
            new httpServer().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            new websocketServer().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Gets executed when program stops
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
               socketServer.stopServer();
            }
        }));
    }

}
