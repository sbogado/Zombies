package multiplayer.framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import multiplayer.implementation.ConnectionAccepter;

public abstract class MultiplayerInformationHandlerAbstract {

	private static final Logger logger = Logger.getLogger( MultiplayerInformationHandlerAbstract.class.getName() );
	
	private List<ClientHandlerAbstract> clientHandlers;

	
	public MultiplayerInformationHandlerAbstract(){

		this.clientHandlers = new ArrayList<ClientHandlerAbstract>();
		this.openConnectionsAccepter();
	}
	
	protected void openConnectionsAccepter(){
		ConnectionAccepterAbstract connectionAccepter = new ConnectionAccepter(this);
		connectionAccepter.start();
	}
	
	protected void sendInformationToOthers(String data){
		for(ClientHandlerAbstract clientHandler : this.clientHandlers){
			try {
				clientHandler.write(data);
			} catch (IOException e) {
				logger.log(Level.SEVERE,"No se pudo escribir al cliente "+clientHandler.getIdClient()+": ",e);
			}
		}
	}
	
	
	public synchronized void validateData(String obtainedData) {
		this.sendInformationToOthers(obtainedData);
	}
	
	// GETTERS AND SETTERS

	protected void addClientHandler(ClientHandlerAbstract clientHandler) {
		this.clientHandlers.add(clientHandler);
	}	

}
