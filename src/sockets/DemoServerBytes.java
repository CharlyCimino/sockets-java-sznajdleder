package sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class DemoServerBytes {

    private static final int BUFFER_LENGTH = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = null;
        BufferedWriter bw = null;
        Socket s = null;
        ServerSocket ss = new ServerSocket(5432);

        while (true) {
            try {
                System.out.println("Servidor esperando...");
                /*El método accept bloquea el programa en esa línea
                y solo avanzará cuando algún cliente se haya conectado, retornando el socket a
                través del cual se podrá dialogar con el cliente que se conectó.*/
                s = ss.accept();
                // informacion en la consola
                System.out.println("Se conectaron desde la IP: " + s.getInetAddress());
                // enmascaro la entrada y salida de bytes
                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                bw = new BufferedWriter(new PrintWriter(s.getOutputStream()));

                char[] bEnviar;
                char[] bRecive = new char[BUFFER_LENGTH];
                StringBuilder sb = new StringBuilder();
                // leo el nombre que envia el cliente
                int n;
                /*El método read que invocamos sobre el objeto br intenta leer tantos bytes como el
                tamaño del buffer y retorna la cantidad de bytes efectivamente leídos. Por este motivo,
                iteramos mientras la cantidad de bytes leídos sea igual a la del tamaño del buffer, ya
                que cuando la cantidad leída es menor es porque se leyó el remanente y este no alcanzó
                para llenar el buffer.*/
                while ((n = br.read(bRecive)) == BUFFER_LENGTH) {
                    sb.append(bRecive);
                }
                sb.append(bRecive, 0, n);

                // armo el saludo personalizado que le quiero enviar
                String saludo = "Hola Mundo (" + sb + ") _ " + System.currentTimeMillis();

                // envio el saludo al cliente
                bEnviar = saludo.toCharArray();
                bw.write(bEnviar);
                bw.flush();
                System.out.println("Saludo enviado...");

            } finally {
                if (bw != null) {
                    bw.close();
                }
                if (br != null) {
                    br.close();
                }
                if (s != null) {
                    s.close();
                }
                System.out.println("Conexion cerrada!");
            }
        }
    }
}
