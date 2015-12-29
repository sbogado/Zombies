package multiplayer.framework;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import multiplayer.implementation.Client;

public abstract class ConnectionRequesterAbstract {

	private static final Logger logger = Logger.getLogger( ConnectionRequesterAbstract.class.getName() );

	
	private String host;

	private int port;
	
	public ConnectionRequesterAbstract(String host,int port){
		this.host = host;
		this.port = port;
	}
	

	public ClientAbstract connect() {
		
		ClientAbstract client = null;
		
		try {
			logger.log(Level.INFO,"iniciando coneccion a "+this.host+" al puerto "+this.port);
			Socket socket = new Socket(this.host,this.port);	
			client = initClient(socket);

			 System.out.println("cliente iniciado");
		} catch (UnknownHostException e) {
			logger.log(Level.SEVERE,"Unknown Host: ",e);
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Error al inicializar el socket: ",e);
		} 
		
		return  client;
	}


	protected abstract ClientAbstract initClient(Socket socket);

}
