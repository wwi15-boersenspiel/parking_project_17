import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by lucas on 14.07.2017.
 */
public class socketConnectionManager extends Thread{

    static ArrayList<socketClient> clients = new ArrayList<socketClient>();

    @Override
    public void run() {
        listenForClients();
    }

    public static void listenForClients() {
        while (true) {
            try {
                System.out.println("Wait for clients");
                Socket client = socketServer.server.accept();
                System.out.println("Client with Address " + client.getInetAddress().toString() + " connected");
                socketClient socketClient = new socketClient(client);
                socketClient.start();
                clients.add(socketClient);
            } catch (Exception e) {
                System.out.print(e);
            }
        }
    }
}
