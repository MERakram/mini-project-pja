import java.io.IOException;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket sa = new DatagramSocket(1111);
        System.out.println("Serveur pret!");
        while(true){
            Thread thread = new ServerThread(sa);
            thread.start();
        }
    }
}
