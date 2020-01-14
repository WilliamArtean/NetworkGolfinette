package fr.ensisa.hassenforder.golfinettes.client.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import fr.ensisa.hassenforder.golfinettes.client.model.Event;
import fr.ensisa.hassenforder.golfinettes.client.model.Usage.BorrowerEvent;
import fr.ensisa.hassenforder.golfinettes.network.Protocol;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;

public class GolfinetteWriter extends BasicAbstractWriter {

	private String host;
	private int port;
	
	public GolfinetteWriter(String host, int port) {
        super(null);
        this.host = host;
        this.port = port;
    }

	private void writeAsByte(int value) {
        writeByte((byte) (value & 0xFF));
	}

	public void createSigFoxStd(Event lastEvent) {
		writeInt((int) lastEvent.getId());
		writeInt(lastEvent.getUsage().getAlarm());
		writeShort((short) lastEvent.getLocation().getLongitude());
		writeShort((short) lastEvent.getLocation().getLatitude());
		writeShort((short) lastEvent.getBattery().getTemperature());
		writeAsByte(lastEvent.getBattery().getLoad());
		writeAsByte(lastEvent.getBattery().getLoadingCurrent());
		writeAsByte(lastEvent.getLocation().getTemperature());
		writeAsByte(lastEvent.getLocation().getHumidity());
		if (lastEvent.getUsage().getEvent().equals(BorrowerEvent.FREE)) {
			writeBoolean(true);
		} else {
			writeBoolean(false);
		}
		writeLong(lastEvent.getTimestamp().getTime());
	}
	
	public void createSigFoxAlmZI(Event lastEvent) {
		writeInt((int) lastEvent.getId());
		writeInt(lastEvent.getUsage().getAlarm());
		writeShort((short) lastEvent.getLocation().getLongitude());
		writeShort((short) lastEvent.getLocation().getLatitude());
		writeAsByte(0);
		writeLong(lastEvent.getTimestamp().getTime());
	}
	public void createSigFoxAlmExitZI(Event lastEvent) {
		writeInt((int) lastEvent.getId());
		writeInt(lastEvent.getUsage().getAlarm());
		writeShort((short) lastEvent.getLocation().getLongitude());
		writeShort((short) lastEvent.getLocation().getLatitude());
		writeAsByte(0);
		writeLong(lastEvent.getTimestamp().getTime());
	}
	public void createSigFoxAlmSpeed(Event lastEvent) {
		writeInt((int) lastEvent.getId());
		writeInt(lastEvent.getUsage().getAlarm());
		writeShort((short) lastEvent.getLocation().getLongitude());
		writeShort((short) lastEvent.getLocation().getLatitude());
		writeAsByte(0);
		writeLong(lastEvent.getTimestamp().getTime());
	}
	public void createSigFoxAlmMaxDist(Event lastEvent) {
		writeInt((int) lastEvent.getId());
		writeInt(lastEvent.getUsage().getAlarm());
		writeShort((short) lastEvent.getLocation().getLongitude());
		writeShort((short) lastEvent.getLocation().getLatitude());
		writeAsByte(0);
		writeLong(lastEvent.getTimestamp().getTime());
	}
	public void createSigFoxAlmClm(Event lastEvent) {
		writeInt((int) lastEvent.getId());
		writeInt(lastEvent.getUsage().getAlarm());
		writeShort((short) lastEvent.getLocation().getLongitude());
		writeShort((short) lastEvent.getLocation().getLatitude());
		writeAsByte(lastEvent.getLocation().getTemperature());
		writeAsByte(lastEvent.getLocation().getHumidity());
		writeLong(lastEvent.getTimestamp().getTime());
	}
	public void createSigFoxAlmBattery(Event lastEvent) {
		writeInt((int) lastEvent.getId());
		writeInt(lastEvent.getUsage().getAlarm());
		writeShort((short) lastEvent.getLocation().getLongitude());
		writeShort((short) lastEvent.getLocation().getLatitude());
		writeShort((short) lastEvent.getBattery().getTemperature());
		writeAsByte(lastEvent.getBattery().getLoad());
		writeAsByte(lastEvent.getBattery().getDischargeCurrent());
		writeLong(lastEvent.getTimestamp().getTime());
	}
	public void createSigFoxAlmMaterial(Event lastEvent) {
		writeInt((int) lastEvent.getId());
		writeInt(lastEvent.getUsage().getAlarm());
		writeShort((short) lastEvent.getLocation().getLongitude());
		writeShort((short) lastEvent.getLocation().getLatitude());
		writeShort((short) lastEvent.getBattery().getTemperature());
		writeAsByte(lastEvent.getBattery().getLoad());
		writeAsByte(lastEvent.getBattery().getDischargeCurrent());
		writeLong(lastEvent.getTimestamp().getTime());
	}
	public void createSigFoxAlmLogiciel(Event lastEvent) {
		writeInt((int) lastEvent.getId());
		writeInt(lastEvent.getUsage().getAlarm());
		writeShort((short) lastEvent.getLocation().getLongitude());
		writeShort((short) lastEvent.getLocation().getLatitude());
		writeShort((short) lastEvent.getBattery().getTemperature());
		writeAsByte(lastEvent.getBattery().getLoad());
		writeAsByte(lastEvent.getBattery().getDischargeCurrent());
		writeLong(lastEvent.getTimestamp().getTime());
	}
	public void createSigFoxAlmClient(Event lastEvent) {
		writeInt((int) lastEvent.getId());
		writeInt((int) lastEvent.getUsage().getAlarm());
		writeShort((short) lastEvent.getLocation().getLongitude());
		writeShort((short) lastEvent.getLocation().getLatitude());
		writeShort((short) lastEvent.getBattery().getTemperature());
		writeAsByte(lastEvent.getBattery().getLoad());
		writeAsByte(lastEvent.getBattery().getDischargeCurrent());
		writeLong(lastEvent.getTimestamp().getTime());
	}
	public void createSigFoxAlmForbiddenBorrow(Event lastEvent) {
		writeInt((int) lastEvent.getId());
		writeInt((int) lastEvent.getUsage().getAlarm());
		writeShort((short) lastEvent.getLocation().getLongitude());
		writeShort((short) lastEvent.getLocation().getLatitude());
		writeLong(lastEvent.getTimestamp().getTime());
	}

	@Override
	public void send() {
        
    	DatagramSocket socket = null;
        try {
        	byte[] message = baos.toByteArray();
        	InetAddress target = InetAddress.getByName(host);
        	DatagramPacket packet = new DatagramPacket(message, message.length, target, port);
        	System.out.println("SIGFOX packet sent with : "+(message.length)+" bytes all inclusive");
        	System.out.println("SIGFOX packet sent with : "+(message.length-20)+" bytes without type, id and timestamp");
        	socket = new DatagramSocket();
        	socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
			if (socket != null) socket.close();
		}
	}

}
