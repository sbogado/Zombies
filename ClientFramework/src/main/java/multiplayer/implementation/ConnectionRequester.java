package multiplayer.implementation;

import java.net.Socket;

import multiplayer.framework.ClientAbstract;
import multiplayer.framework.ConnectionRequesterAbstract;

public class ConnectionRequester extends ConnectionRequesterAbstract{

	
	public ConnectionRequester(String host, int port) {
		super(host, port);
	}

	@Override
	protected ClientAbstract initClient(Socket socket) {
		return new Client(socket);
	}

}
