package sockets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class DemoServerMT {

    public static void main(String[] args) throws Exception {
        Socket s = null;
        ServerSocket ss = new ServerSocket(5432);
        while (true) {
            System.out.println("Servidor esperando...");
            /*El método accept bloquea el programa en esa línea
                y solo avanzará cuando algún cliente se haya conectado, retornando el socket a
                través del cual se podrá dialogar con el cliente que se conectó.*/
            s = ss.accept();
            (new Tarea(s)).start();
        }
    }

    static class Tarea extends Thread {

        private Socket s = null;
        private ObjectInputStream ois = null;
        private ObjectOutputStream oos = null;

        public Tarea(Socket socket) {
            this.s = socket;
        }

        @Override
        public void run() {
            try {
                // informacion en la consola
                System.out.println("Se conectaron desde la IP: " + s.getInetAddress());
                // enmascaro la entrada y salida de bytes
                ois = new ObjectInputStream(s.getInputStream());
                oos = new ObjectOutputStream(s.getOutputStream());
                // leo el nombre que envia el cliente
                String nom = (String) ois.readObject();
                // armo el saludo personalizado que le quiero enviar
                String saludo = "Hola Mundo (" + nom + ") _ " + System.currentTimeMillis();

                // envio el saludo al cliente
                oos.writeObject(saludo);
                System.out.println("Saludo enviado...");
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (oos != null) {
                        oos.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                    if (s != null) {
                        s.close();
                    }
                    System.out.println("Conexion cerrada!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
