package sockets.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class ObjetoRemotoImpl extends UnicastRemoteObject implements ObjetoRemoto {

    public ObjetoRemotoImpl() throws RemoteException {
        super();
    }

    @Override
    public String obtenerSaludo(String nombre) throws RemoteException {
        return "Hola Mundo RMI - " + nombre;
    }

}
