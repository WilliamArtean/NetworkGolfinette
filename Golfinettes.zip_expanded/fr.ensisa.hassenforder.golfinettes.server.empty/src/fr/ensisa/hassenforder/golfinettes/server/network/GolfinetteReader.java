package fr.ensisa.hassenforder.golfinettes.server.network;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.sun.jmx.snmp.Timestamp;

import fr.ensisa.hassenforder.golfinettes.network.Protocol;
import fr.ensisa.hassenforder.golfinettes.server.model.Battery;
import fr.ensisa.hassenforder.golfinettes.server.model.Battery.BatteryMode;
import fr.ensisa.hassenforder.golfinettes.server.model.Event;
import fr.ensisa.hassenforder.golfinettes.server.model.Location;
import fr.ensisa.hassenforder.golfinettes.server.model.Usage;
import fr.ensisa.hassenforder.golfinettes.server.model.Usage.BorrowerEvent;
import fr.ensisa.hassenforder.golfinettes.server.model.Usage.UsageState;
import fr.ensisa.hassenforder.network.BasicAbstractReader;

public class GolfinetteReader extends BasicAbstractReader {

	private Event event;
	String kind = "Sigfox";
	
	public GolfinetteReader(byte [] data) {
		super (new ByteArrayInputStream(data));
	}

	private int readAsByte() {
        return (int) (readByte() & 0xFF);
	}

	public Event readSigFoxStd () throws IOException {
		int i = this.readInt();
		int usAlarme = readInt();

		short longitude = readShort();
		short latitude = readShort();
		short btrTemp = readShort();
		int btrload = readByte();
		int btrCurrent = readByte();
		int btrDischarge = readByte();
		int btrMode = readByte();
		int ambTemp = readByte();
		int ambHumidity = readByte();
		boolean free = readBoolean();
		long date = readLong();
		Event e = new Event(ambHumidity, date, kind);
		//on sait pas encore
		e.withUsage(new Usage(1, Usage.BorrowerEvent.BORROW, Usage.UsageState.MOVING_NORMAL, 1, usAlarme));
		e.withLocation(new Location(latitude, longitude, ambTemp, ambHumidity));
		e.withBattery(new Battery(btrTemp, btrload,btrCurrent ,btrDischarge , Battery.BatteryMode.values()[btrMode]));
		System.out.println(e.toString());
		return e;
	}

	public void receive() throws IOException {
		type = readInt ();
		switch (type) {
		case 0 : break;
		case Protocol.SIGFOX_STD: 		
			event = readSigFoxStd();
			System.out.println(event.toString());
			break;
		case Protocol._ALM_BATR:
			event = readSigFoxBatr();
			break;
		case Protocol._ALM_CLIM:
			event = readSigFoxClim();
			break;
		case Protocol._ALM_EXVITESSE:
			event = readSigFoxExVitesse();
			break;
		case Protocol._ALM_DISTMAX:
			event = readSigFoxMaxDistance();
			break;
		}
	}

	private Event readSigFoxMaxDistance() {
		// TODO Auto-generated method stub	
		int id = this.readInt();
		int usageAlarme = this.readInt();
		return null;
	}

	private Event readSigFoxExVitesse() {
		// TODO Auto-generated method stub
		int id = this.readInt();
		int usageAlarme = this.readInt();
		short longitude = this.readShort();
		short latitude = this.readShort();
		int temperature = this.readAsByte();
		int humidity = this.readAsByte();
		long date = this.readLong();
		Event e = new Event(id, date, kind);
		e.withLocation(new Location(latitude, longitude, temperature, humidity));
		
		
		return e;
	}

	private Event readSigFoxClim() {
		// TODO Auto-generated method stub
		int id = this.readInt();
		int usageAlarme = this.readInt();
		short longitude = this.readShort();
		short latitude = this.readShort();
		int temperature = this.readAsByte();
		int humidity = this.readAsByte();
		long date = this.readLong();
		
		Event e = new Event(id, date, kind);
		e.withLocation(new Location(latitude, longitude, temperature, humidity));
		return e;
	}

	private Event readSigFoxBatr() {
		// TODO Auto-generated method stub
		
		int id = this.readInt();
		int usageAlarme= this.readInt();
		short longitude = this.readShort();
		short latitude = this.readShort();
		int tempambiante = this.readAsByte();
		int humidity = this.readInt();
		short tmpb = this.readShort();
		int load = this.readAsByte();
		int current = this.readAsByte();
		int discharge = this.readAsByte();
		long date = this.readLong();
		
		Event e = new Event(id, date, this.kind);
		Battery bat = new Battery(tmpb, load, current, discharge, Battery.BatteryMode.UNPLUGGED);
		e.withBattery(bat);
		e.withLocation(new Location(latitude, longitude, tempambiante, humidity));
		
		return e;
		
	}

	public Event getEvent() {
		return event;
	}

}

