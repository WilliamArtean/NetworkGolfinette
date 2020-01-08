package fr.ensisa.hassenforder.golfinettes.client.network;

import java.io.InputStream;

import fr.ensisa.hassenforder.golfinettes.client.model.Version;
import fr.ensisa.hassenforder.golfinettes.network.Protocol;
import fr.ensisa.hassenforder.network.BasicAbstractReader;

public class WifiReader extends BasicAbstractReader {

    private Version version;

    public WifiReader(InputStream inputStream) {
        super(inputStream);
    }

	public void receive() {
        type = readInt();
        version = null;
        switch (type) {
        }
    }

	public Version getVersion() {
		return version;
	}

}
