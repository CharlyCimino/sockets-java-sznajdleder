package sockets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class DemoClient {

    public static void main(String[] args) throws Exception {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Socket s = null;
        try {
            // instancio el server con la IP y el PORT
            s = new Socket("127.0.0.1", 5432);
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());
            // envio un nombre      
            oos.writeObject("Pablo");
            // recibo la respuesta (el saludo personalizado)        
            String ret = (String) ois.readObject();
            // muestro la respuesta que envio el server     
            System.out.println(ret);
        } finally {
            if (ois != null) {
                ois.close();
            }
            if (oos != null) {
                oos.close();
            }
            if (s != null) {
                s.close();
            }
        }
    }

}
