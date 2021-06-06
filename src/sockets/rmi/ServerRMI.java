package sockets.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class ServerRMI {

    public static void main(String[] args) throws Exception {
        ObjetoRemotoImpl obj = new ObjetoRemotoImpl();
        Registry registry = LocateRegistry.getRegistry(1099);
        registry.rebind("OBJRemoto", obj);
    }

}
