package sockets;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class DemoClientUDP {

    public static void main(String[] args) throws Exception {
        // instancio un DatagramSocket
        DatagramSocket socket = new DatagramSocket();
        // buffer con info a enviar
        byte[] bEnviar = "Pablo".getBytes();
        // ip del server
        byte[] ip = {127, 0, 0, 1};
        InetAddress address = InetAddress.getByAddress(ip);
        // paquete de informacion a enviar, ip + port (5432)
        DatagramPacket packet = new DatagramPacket(bEnviar,
                                                    bEnviar.length,
                                                    address,
                                                    5432);
        // envio el paquete
        socket.send(packet);
        // buffer para recibir la respuesta
        byte[] bRecibe = new byte[256];
        packet = new DatagramPacket(bRecibe,
                                    bRecibe.length,
                                    address,
                                    5432);
        // recibo el saludo
        socket.receive(packet);
        // muestro el resultado
        String saludo = new String(packet.getData(),
                                    0,
                                    packet.getLength());
        System.out.println(saludo);
        socket.close();
    }

}
