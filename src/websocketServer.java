import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucas on 19.07.2017.
 */
public class websocketServer extends WebSocketServer {

    static final String SERVER_ADRESS = "0.0.0.0";
    static final int SERVER_PORT = 1882;

    private Set<WebSocket> conns;

    public websocketServer() throws UnknownHostException {
        super(new InetSocketAddress(InetAddress.getByName(SERVER_ADRESS), SERVER_PORT));
        conns = new HashSet<>();
        System.out.println("Websocket server started");
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conns.add(conn);
        System.out.println("New connection from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        conns.remove(conn);
        System.out.println("Closed connection to " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Message from client: " + message);
        for (WebSocket sock : conns) {
            sock.send(message);
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        //ex.printStackTrace();
        if (conn != null) {
            conns.remove(conn);
            // do some thing if required
        }
        System.out.println("ERROR from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

}
