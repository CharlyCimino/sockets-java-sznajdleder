package sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class DemoClientBytes {

    private static final int BUFFER_LENGTH = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = null;
        BufferedWriter bw = null;
        Socket s = null;
        try {
            // instancio el server con la IP y el PORT
            s = new Socket("127.0.0.1", 5432);
            bw = new BufferedWriter(new PrintWriter(s.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            char[] bEnvia = "Pablo".toCharArray();
            char[] bRecibe = new char[BUFFER_LENGTH];
            bw.write(bEnvia);
            bw.flush();
            StringBuffer sb = new StringBuffer();
            int n;
            while ((n = br.read(bRecibe)) == BUFFER_LENGTH) {
                sb.append(bRecibe);
            }
            sb.append(bRecibe, 0, n);
            System.out.println(sb);
        } finally {
            if (br != null) {
                br.close();
            }
            if (bw != null) {
                bw.close();
            }
            if (s != null) {
                s.close();
            }
        }
    }

}
