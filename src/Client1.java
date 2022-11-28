import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Client1 {
    public static void main(String[] test) throws SocketException {
        Scanner sc = new Scanner(System.in);
        DatagramSocket sa = new DatagramSocket();
        Thread envoy = new Thread(new Runnable() {
            String msg;
            @Override
            public void run() {
                while (true) {
                    msg = sc.nextLine();
                    byte[] msg_envoy = msg.getBytes();
                    try {
                        if(msg.equals(".")){
                            System.out.println("Client deconnecte");
                            System.exit(0);
                        }
                        DatagramPacket ss =  new DatagramPacket(msg_envoy, msg_envoy.length, InetAddress.getByName("127.0.0.1"), 1111);
                        sa.send(ss);
                        System.out.println("Le message envoye au serveur est: "+new String(msg_envoy));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        envoy.start();

        Thread reservoir1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    byte[] msg1_recu = new byte[64];
                    DatagramPacket parquet_rec = new DatagramPacket(msg1_recu, msg1_recu.length);
                    try {
                        sa.receive(parquet_rec);
                        if(new String(msg1_recu).equals(".")){
                            System.out.println("Client deconnecte");
                            System.exit(0);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Le message recu par le serveur est: "+new String(msg1_recu));
                }
            }
        });
        reservoir1.start();
    }
}