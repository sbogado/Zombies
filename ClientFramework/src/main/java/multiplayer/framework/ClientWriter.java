package multiplayer.framework;

import java.io.DataOutputStream;
import java.io.IOException;

public class ClientWriter{

	private DataOutputStream salidaDatos;
	
	public ClientWriter(DataOutputStream salidaDatos){
		this.salidaDatos = salidaDatos;
	}

	
	public void writeData(String data) throws IOException{
			salidaDatos.writeChars(data);
	}

}
