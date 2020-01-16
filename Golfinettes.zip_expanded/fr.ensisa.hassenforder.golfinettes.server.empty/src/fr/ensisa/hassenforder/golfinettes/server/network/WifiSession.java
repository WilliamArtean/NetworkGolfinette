package fr.ensisa.hassenforder.golfinettes.server.network;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

import fr.ensisa.hassenforder.golfinettes.network.Protocol;
import fr.ensisa.hassenforder.golfinettes.server.NetworkListener;
import fr.ensisa.hassenforder.golfinettes.server.model.Event;
import fr.ensisa.hassenforder.golfinettes.server.model.Golfinette;
import fr.ensisa.hassenforder.golfinettes.server.model.Version;


public class WifiSession extends Thread {

	private Socket connection;
	private NetworkListener listener;
	
	public WifiSession(Socket connection, NetworkListener listener) {
		this.connection = connection;
		this.listener = listener;
		if( listener == null) throw new RuntimeException("listener cannot be null");
	}

	public void close () {
		this.interrupt();
		try {
			if (connection != null)
				connection.close();
		} catch (IOException e) {
		}
		connection = null;
	}

	public boolean operate() {
		try {
			WifiWriter writer = new WifiWriter (connection.getOutputStream());
			WifiReader reader = new WifiReader (connection.getInputStream());
			reader.receive ();
			switch (reader.getType ()) {
			case 0 : return false; // socket closed
			case 1 : break;
			case 2 : break;
			case 3 : break;
			case 4 : break;
			case 5 : break;
			case 6 : break;
			case 7 : break;
			case 8 : break;
			case 9 : break;
			case 10 : break;
			case 11 : break;
			case 13 : 
			{
		    	this.listener.putSoftwareVersion(reader.getVersion());
			}
			case 14 : 
			{
				this.listener.putMapVersion(reader.getVersion());
			}
			case 15 : 
			{
				this.listener.putUsersVersion(reader.getVersion());
			}
			case 666 : break; // to remove, inserted to hide error
			default: return false; // connection jammed
			}
			writer.send ();
			return true;
		} catch (IOException e) {
			return false;
		}
	}


	public void run() {
		while (true) {
			if (! operate())
				break;
		}
		try {
			if (connection != null) connection.close();
		} catch (IOException e) {
		}
	}

}
