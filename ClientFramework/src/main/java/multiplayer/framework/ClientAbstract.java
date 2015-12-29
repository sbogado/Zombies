package multiplayer.framework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ClientAbstract {
	
	private static final Logger logger = Logger.getLogger( ClientAbstract.class.getName() );
	
	private int clientId;
	
	private ClientReader reader;
	
	private ClientWriter writer;

	private Socket socket;

	public ClientAbstract(Socket socket){
		this.socket = socket;
		
        try {
            this.reader = new ClientReader(new DataInputStream(socket.getInputStream()));
            this.writer = new ClientWriter( new DataOutputStream(socket.getOutputStream()));
            
            
        } catch (IOException e) {
        	logger.log(Level.SEVERE,"Error al crear los stream de entrada y salida: ",e);
        }
        
        try {
			this.clientId = this.reader.requestClientId();
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE,"Error al dar formato al id del cliente: ",e);
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Error al leer el id del cliente: ",e);
		}

	}
	
	public void write(String mensaje) throws IOException{
		this.writer.writeData(mensaje);
	}
	
	public String read() throws IOException {
		return this.reader.read();
	}

	
	public void closeConnection(){
		try {
			this.socket.close();
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Error al cerrar la coneccion: ",e);
		}
	}
	
	public int getClientId() {
		return clientId;
	}
}
