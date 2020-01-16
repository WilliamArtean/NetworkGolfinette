package fr.ensisa.hassenforder.golfinettes.server.network;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.ensisa.hassenforder.golfinettes.network.Protocol;
import fr.ensisa.hassenforder.golfinettes.server.model.Battery;
import fr.ensisa.hassenforder.golfinettes.server.model.Battery.BatteryMode;
import fr.ensisa.hassenforder.golfinettes.server.model.Event;
import fr.ensisa.hassenforder.golfinettes.server.model.Location;
import fr.ensisa.hassenforder.golfinettes.server.model.Usage;
import fr.ensisa.hassenforder.golfinettes.server.model.Usage.BorrowerEvent;
import fr.ensisa.hassenforder.golfinettes.server.model.Usage.UsageState;
import fr.ensisa.hassenforder.golfinettes.server.model.Version;
import fr.ensisa.hassenforder.network.BasicAbstractReader;

public class WifiReader extends BasicAbstractReader {

	private String versionCode;
	private List<Event> events;
	private Version version;
	private long id;
	private String kind;
	
	public WifiReader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case 0 : break;
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
			int size = this.readInt();
	    	this.versionCode = this.readString();
	    	String content1= this.readString();
	    	byte [] content2 = new byte[size];
	    	for (int i=0;i< content2.length;i++)
	    	{
	    		content2[i] = this.readByte();
	    	}
	    	this.version = new Version(this.versionCode,content1,content2);
		}
		case 14 : 
		{
			int size = this.readInt();
	    	this.versionCode = this.readString();
	    	String content1= this.readString();
	    	byte [] content2 = new byte[size];
	    	for (int i=0;i< content2.length;i++)
	    	{
	    		content2[i] = this.readByte();
	    	}
	    	this.version = new Version(this.versionCode,content1,content2);
		}
		case 15 : 
		{
			int size = this.readInt();
	    	this.versionCode = this.readString();
	    	String content1= this.readString();
	    	byte [] content2 = new byte[size];
	    	for (int i=0;i< content2.length;i++)
	    	{
	    		content2[i] = this.readByte();
	    	}
	    	this.version = new Version(this.versionCode,content1,content2);
		}
		}
	}

	public String getVersionCode() {
		return versionCode;
	}

	public List<Event> getEvents() {
		return events;
	}

	public Version getVersion() {
		return version;
	}

	public long getId() {
		return id;
	}

	public String getKind() {
		return kind;
	}

}
