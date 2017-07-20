import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by lucas on 11.07.2017.
 */
public class socketClient extends Thread {

    private Socket client;


    public socketClient(Socket client) {
        this.client = client;

    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        System.out.println("Client started");
        read();
    }

    private void read() {
        try {
            System.out.println("Start reading messages");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            String fromServer = null;
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Client: " + fromServer);
                API.processCall(fromServer, this);
            }

            /*while (true) {
                int input = in.read();
                if (input != -1) {
                    System.out.println("Client: " + input);
                    API.processCall("data");
                } else {
                    System.out.println("Client disconnected");
                    client.close();
                    break;
                }
            }*/

        } catch (Exception e) {
            System.out.print("Fehler read: " + e);

            client = null;
        }

    }

    public void write(String data) {
        try {
            System.out.println("To Server: " + data);
            PrintWriter out =
                    new PrintWriter(client.getOutputStream(), true);
            out.println(data);
           /* InetAddress addr = InetAddress.getByName("0.0.0.0");
            Socket writeClient = new Socket(addr, 3002);
            DataOutputStream outSecond = new DataOutputStream(writeClient.getOutputStream());
            outSecond.writeChars(data);
            outSecond.close();*/

        } catch (Exception e) {
            System.out.print("Fehler write: " + e);

            client = null;
        }
    }

}
