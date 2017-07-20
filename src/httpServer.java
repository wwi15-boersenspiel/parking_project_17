import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Created by lucas on 19.07.2017.
 */
public class httpServer extends Thread implements HttpHandler {

    static final String SERVER_ADRESS = "0.0.0.0";
    static final int SERVER_PORT = 80;
    private static HttpServer server;

    public httpServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(InetAddress.getByName(SERVER_ADRESS), SERVER_PORT), 0);
        server.createContext("/", this);

    }

    public void run() {
        server.start();
        System.out.println("Webserver started");
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "<h1>PARKING PROJEKT BEST PROJECT EVER</h1>";
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
