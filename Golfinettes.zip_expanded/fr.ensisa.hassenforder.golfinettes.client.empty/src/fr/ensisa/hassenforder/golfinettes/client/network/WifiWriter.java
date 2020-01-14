package fr.ensisa.hassenforder.golfinettes.client.network;

import java.io.OutputStream;
import java.util.List;

import fr.ensisa.hassenforder.golfinettes.client.model.Battery;
import fr.ensisa.hassenforder.golfinettes.client.model.Event;
import fr.ensisa.hassenforder.golfinettes.client.model.Location;
import fr.ensisa.hassenforder.golfinettes.client.model.Usage;
import fr.ensisa.hassenforder.golfinettes.network.Protocol;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;

public class WifiWriter extends BasicAbstractWriter {

    public int i = 0;
    public WifiWriter(OutputStream outputStream) throws IOException {
        super(outputStream);
       
    }

    
    private void writeAsByte(int value) {
        writeByte((byte) (value & 0xFF));
	}
   
    public void writeWifimsg(List<Event> events) {
    	
    	//envoi le nombre d'events au serveur
    	writeAsByte(events.size());
    	
		while(events.size()>i)
		{
		
		writeLong(events.get(i).getId());
		writeLong(events.get(i).getTimestamp().getTime());
		writeShort((short) events.get(i).getLocation().getLatitude());
		writeShort((short) events.get(i).getLocation().getLongitude());
		writeShort((short) events.get(i).getBattery().getTemperature());
		writeAsByte(events.get(i).getBattery().getLoad());
		writeAsByte(events.get(i).getBattery().getLoadingCurrent());
		writeAsByte(events.get(i).getLocation().getTemperature());
		writeAsByte(events.get(i).getLocation().getHumidity());
		i++;
		}
		
	}

}
