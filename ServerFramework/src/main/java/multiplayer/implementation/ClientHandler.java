package multiplayer.implementation;

import java.net.Socket;

import multiplayer.framework.ClientHandlerAbstract;
import multiplayer.framework.MultiplayerInformationHandlerAbstract;

public class ClientHandler extends ClientHandlerAbstract {

	public ClientHandler(Socket socket,MultiplayerInformationHandlerAbstract multiplayerInformationHandler, int numeroActualDeConeccion) {
		super(socket, multiplayerInformationHandler,numeroActualDeConeccion);

	}

}
