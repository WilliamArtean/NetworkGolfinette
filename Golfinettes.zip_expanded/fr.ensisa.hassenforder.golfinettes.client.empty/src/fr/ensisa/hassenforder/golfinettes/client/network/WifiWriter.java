package fr.ensisa.hassenforder.golfinettes.client.network;

import java.io.IOException;
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

    
    
   
    public void writeWifimsg(List<Event> events) {
    	
    	//envoi le nombre d'events au serveur
    	writeInt(events.size());
    	
		while(events.size()>i)
		{
		
		writeLong(events.get(i).getId());
		writeLong(events.get(i).getTimestamp().getTime());
		writeFloat(events.get(i).getLocation().getLatitude());
		writeFloat(events.get(i).getLocation().getLongitude());
		writeInt(events.get(i).getLocation().getTemperature());
		writeInt(events.get(i).getLocation().getHumidity());
		writeInt(events.get(i).getBattery().getTemperature());
		writeInt(events.get(i).getBattery().getLoad());
		writeInt(events.get(i).getBattery().getLoadingCurrent());
		writeInt(events.get(i).getBattery().getDischargeCurrent());
		writeInt(events.get(i).getLocation().getTemperature());
		writeInt(events.get(i).getLocation().getHumidity());
		i++;
		}
		
	}
}
