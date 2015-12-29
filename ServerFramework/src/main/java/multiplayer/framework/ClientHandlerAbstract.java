package multiplayer.framework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class ClientHandlerAbstract extends Thread{

	private static final Logger logger = Logger.getLogger( ClientHandlerAbstract.class.getName() );
	
	private Socket socket;
	
	private ServerReader reader;
	
	private ServerWriter writer;

	private MultiplayerInformationHandlerAbstract multiplayerInformationHandler;

	private int idClient;
	
	public ClientHandlerAbstract(Socket socket, MultiplayerInformationHandlerAbstract multiplayerInformationHandler, int numeroActualDeConeccion) {
		
		this.socket = socket;
		this.multiplayerInformationHandler = multiplayerInformationHandler;
		this.setIdClient(numeroActualDeConeccion);
        try {
        	reader = new ServerReader(new DataInputStream(socket.getInputStream()));
        	writer =  new ServerWriter(new DataOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
        	logger.log(Level.SEVERE,"Error al crear los stream de entrada y salida: ",e);

        }
        
        try {
			this.writer.writeData(Integer.toString(this.idClient));
		} catch (IOException e) {
			logger.log(Level.SEVERE,"No se pudo enviar el id de cliente: ",e);
		}
	}

	@Override
	public void run() {

		while(this.socket.isConnected()) {
			this.doAction();
		}
	}
	
	public void write(String mensaje) throws IOException{
		this.writer.writeData(mensaje);
	}
	
	public String read() throws IOException {
		return this.reader.read();
	}
	
	public void doAction(){
		String data;
		try {
			data = this.read();
			if(data != null){
				this.multiplayerInformationHandler.validateData(data);
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE,"No se pudo leer desde el servidor: ",e);
		}

	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	

}

