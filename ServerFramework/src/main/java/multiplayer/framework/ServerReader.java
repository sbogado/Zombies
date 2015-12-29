package multiplayer.framework;

import java.io.DataInputStream;
import java.io.IOException;

public class ServerReader {

	private DataInputStream entradaDatos;

	public ServerReader(DataInputStream salidaDatos) {
		this.entradaDatos = salidaDatos;
	}

	public String read() throws IOException {
		String data = "";

		if (entradaDatos.available() > 0) {
			data = entradaDatos.readUTF();
		}

		return data;
	}

	public Integer requestClientId() throws NumberFormatException, IOException {
		return Integer.parseInt(entradaDatos.readUTF());

	}

}
