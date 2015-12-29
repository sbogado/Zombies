package multiplayer.framework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import multiplayer.implementation.ClientHandler;

public abstract class ConnectionAccepterAbstract extends Thread{
	
	final int PUERTO = 9000;
	
	private static final Logger logger = Logger.getLogger( ConnectionAccepterAbstract.class.getName() );
	
	private ServerSocket serverSocket;
	
	private MultiplayerInformationHandlerAbstract multiplayerInformationHandler;
	
	public ConnectionAccepterAbstract(MultiplayerInformationHandlerAbstract multiplayerInformationHandler) {
		try {
			this.serverSocket = new ServerSocket(PUERTO);
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Error abriendo server socket: ",e);
		}
		
		this.multiplayerInformationHandler = multiplayerInformationHandler;
	}

	@Override
	public void run() {
		
		int numeroActualDeConeccion = 1;
		
		while(true){
			try {
				Socket socket = this.serverSocket.accept();
				ClientHandlerAbstract clientHandler = new ClientHandler(socket,this.multiplayerInformationHandler,numeroActualDeConeccion);
				multiplayerInformationHandler.addClientHandler(clientHandler);
				
				numeroActualDeConeccion = numeroActualDeConeccion + 1;
				
				clientHandler.start();
			} catch (IOException e) {
				logger.log(Level.SEVERE,"Error aceptando conneccion: ",e);
			}
		}
		
	}


}
