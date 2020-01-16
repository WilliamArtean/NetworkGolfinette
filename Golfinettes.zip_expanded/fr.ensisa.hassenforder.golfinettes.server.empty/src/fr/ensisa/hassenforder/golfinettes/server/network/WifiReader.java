package fr.ensisa.hassenforder.golfinettes.server.network;

import java.io.InputStream;
import java.text.SimpleDateFormat;
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

	private static final int MESSAGE_0 = 0;
	private String versionCode;
	private List<Event> events;
	private Version version;
	private long id;
	private String kind ="wifi";
	
	public WifiReader(InputStream inputStream) {
		super (inputStream);
		this.events = new ArrayList<>();
	}
	
	public Event readData()
	{
		int id = (int)readLong();
		//System.out.println("reader id" + id);
		int borrower = readInt();
		int usageEvent = readInt();
		int usageState = readInt();
		int detail = readInt();
		int usAlarme = readInt();
		short longitude = readShort();
//		System.out.println("longitude :  " + longitude);
		short latitude = readShort();
//		System.out.println("latitude : " + latitude);
		short btrTemp = readShort();
//		System.out.println("baterie temperature : " + btrTemp);
		int btrload = readByte();
//		System.out.println("batterie load : " + btrload);
		int btrCurrent = readByte();
//		System.out.println("btrCurrent : " + btrCurrent);
		int btrDischarge = readByte();
//		System.out.println("btrDischarge : " + btrDischarge);
		int btrMode = readByte();
//		System.out.println("btrMode : " + btrMode);
		int ambTemp = readByte();
//		System.out.println("ambTemp : " + ambTemp);
		int ambHumidity = readByte();
//		System.out.println("ambHumidity : " + ambHumidity);
		boolean free = readBoolean();
//		System.out.println(" freeMode : " + free);
		long date = readLong();
//		System.out.println(" date :  " +  new SimpleDateFormat("hh:mm:ss").format(date));
		Usage usage = new Usage(borrower,Usage.BorrowerEvent.values()[usageEvent],
								Usage.UsageState.values()[usageState],
								detail,
								usAlarme);
		
		Event e = new Event(ambHumidity, date, kind);
		e.withUsage(usage);
		e.withLocation(new Location(latitude, longitude, ambTemp, ambHumidity));
		e.withBattery(new Battery(btrTemp, btrload,btrCurrent ,btrDischarge , Battery.BatteryMode.values()[btrMode]));
		return e;
	}

	public void receive() {
		System.out.println("receiver");
		int size = readInt();
		System.out.println("receiver size" + size);
		int i = 0;
		while ( i <= size && size <15) 
		{
			type = readInt();
//			type = 15 ;
//			System.out.println("type moda" + type);
			switch (type) {
			case Protocol.WIFI_STD :
				int id = (int)readLong();
				//System.out.println("reader id" + id);
				
				
				int borrower = readInt();
				int usageEvent = readInt();
				int usageState = readInt();
				int detail = readInt();
				int usAlarme = readInt();
				
				
				
				
//				System.out.println("sysUsAlarme : " + usAlarme);
				short longitude = readShort();
//				System.out.println("longitude :  " + longitude);
				short latitude = readShort();
//				System.out.println("latitude : " + latitude);
				short btrTemp = readShort();
//				System.out.println("baterie temperature : " + btrTemp);
				int btrload = readByte();
//				System.out.println("batterie load : " + btrload);
				int btrCurrent = readByte();
//				System.out.println("btrCurrent : " + btrCurrent);
				int btrDischarge = readByte();
//				System.out.println("btrDischarge : " + btrDischarge);
				int btrMode = readByte();
//				System.out.println("btrMode : " + btrMode);
				int ambTemp = readByte();
//				System.out.println("ambTemp : " + ambTemp);
				int ambHumidity = readByte();
//				System.out.println("ambHumidity : " + ambHumidity);
				boolean free = readBoolean();
//				System.out.println(" freeMode : " + free);
				long date = readLong();
//				System.out.println(" date :  " +  new SimpleDateFormat("hh:mm:ss").format(date));
				Usage usage = new Usage(borrower,Usage.BorrowerEvent.values()[usageEvent],
										Usage.UsageState.values()[usageState],
										detail,
										usAlarme);
				
				Event e = new Event(ambHumidity, date, kind);
				e.withUsage(usage);
				e.withLocation(new Location(latitude, longitude, ambTemp, ambHumidity));
				e.withBattery(new Battery(btrTemp, btrload,btrCurrent ,btrDischarge , Battery.BatteryMode.values()[btrMode]));
				System.out.println("i read "+ i + "event : " + e.toString());
				this.events.add(e);
	
				
			}
			i++;
			
//			System.out.println("toute la liste quoi");
//			for(Event e : events)
//			{
//				System.out.println(e.toString());
//			}
//			System.out.println("fin -----------------");
			
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
