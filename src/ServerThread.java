import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerThread extends Thread{
    private DatagramSocket soquette;
    public ServerThread(DatagramSocket soquette){
        this.soquette = soquette;
    }
    @Override
    public void run() {
        while (true) {
            byte msg_recu [ ] = new byte[64];
            DatagramPacket paquet_recu = new DatagramPacket(msg_recu, msg_recu.length);
            try {
                soquette.receive(paquet_recu);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Le message recu par client "+paquet_recu.getSocketAddress()+ " est: " +new String(msg_recu));
            System.out.println("Le message a envoye au client "+paquet_recu.getSocketAddress()+ " est: " +new String(msg_recu).toUpperCase());
            String str = new String(msg_recu);
            byte msg_envoi [ ] = str.toUpperCase().getBytes();
            try {
                soquette.send(new DatagramPacket (msg_envoi, msg_envoi.length, paquet_recu.getAddress(), paquet_recu.getPort()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
