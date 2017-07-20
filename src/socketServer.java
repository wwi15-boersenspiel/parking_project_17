import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucas on 14.07.2017.
 */
public class socketServer {

    static final String SERVER_ADRESS = "0.0.0.0";
    static final int SERVER_PORT = 1881;

    static ServerSocket server = null;

    public static void startServer() {
        try {
            server = new ServerSocket(SERVER_PORT, 50, InetAddress.getByName(SERVER_ADRESS));
            System.out.println("Server startet on " + SERVER_ADRESS + ":" + SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopServer() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
