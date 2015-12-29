package multiplayer.framework;

import java.io.DataOutputStream;
import java.io.IOException;

public class ServerWriter {


	private DataOutputStream salidaDatos;

	public ServerWriter(DataOutputStream salidaDatos) {
		this.salidaDatos = salidaDatos;
	}

	public void writeData(String data) throws IOException {

		salidaDatos.writeChars(data);

	}
}
