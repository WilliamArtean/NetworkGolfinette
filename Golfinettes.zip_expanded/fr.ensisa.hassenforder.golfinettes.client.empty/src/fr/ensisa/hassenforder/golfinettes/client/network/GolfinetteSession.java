package fr.ensisa.hassenforder.golfinettes.client.network;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import fr.ensisa.hassenforder.golfinettes.client.model.Event;
import fr.ensisa.hassenforder.golfinettes.client.model.Usage.BorrowerEvent;
import fr.ensisa.hassenforder.golfinettes.client.model.Usage.UsageState;
import fr.ensisa.hassenforder.golfinettes.client.model.Version;
import fr.ensisa.hassenforder.golfinettes.network.Protocol;

public class GolfinetteSession implements ISession {

    private Socket wifi;

    public GolfinetteSession() {
    }

    @Override
    synchronized public boolean close() {
        try {
            if (wifi != null) {
                wifi.close();
            }
            wifi = null;
        } catch (IOException e) {
        }
        return true;
    }

    @Override
    synchronized public boolean open() {
        this.close();
        try {
            wifi = new Socket("localhost", Protocol.GOLFINETTES_WIFI_PORT);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

	@Override
	public Version doSoftwareUpdate(String versionCode) {
		return null;
	}

	@Override
	public Version doMapUpdate(String versionCode) {
		return null;
	}

	@Override
	public Version doUsersUpdate(String versionCode) {
		return null;
	}

	@Override
	public boolean doWifi(List<Event> events) {
		return false;
	}

	private enum Decision {
		SIGFOX_STD,SYGFOX_ALM_ZIIN, SYGFOX_ALM_ZIOUT, SYGFOX_ALM_EXVITESSE, SYGFOX_ALM_DISTMAX, SYGFOX_ALM_CLIM, SYGFOX_ALM_BATR, SYGFOX_ALM_MAT, SYGFOX_ALM_SOFT, SYGFOX_ALM_MALCL, SYGFOX_ALM_UNAUTH
	}

	private Decision decisionTaker (Event last) {
		switch (last.getUsage().getAlarm()) {
		case 0:
			return Decision.SIGFOX_STD;
		case 1:
			return Decision.SYGFOX_ALM_ZIIN;
		case 2:
			return Decision.SYGFOX_ALM_EXVITESSE;
		case 3:
			return Decision.SYGFOX_ALM_ZIOUT;
		case 4:
			return Decision.SYGFOX_ALM_DISTMAX;
		case 5:
			return Decision.SYGFOX_ALM_CLIM;
		case 6:
			return Decision.SYGFOX_ALM_BATR;
		case 7:
			return Decision.SYGFOX_ALM_MAT;
		case 8:
			return Decision.SYGFOX_ALM_SOFT;
		case 9:
			return Decision.SYGFOX_ALM_MALCL;
		case 10:
			return Decision.SYGFOX_ALM_UNAUTH;
		}
		return Decision.SIGFOX_STD;
	}

	@Override
	public void doSigFox(Event lastEvent) {
//        try {
            GolfinetteWriter w = new GolfinetteWriter("localhost", Protocol.GOLFINETTES_SIGFOX_PORT);
            switch (decisionTaker(lastEvent)) {
            case SIGFOX_STD		: w.createSigFoxStd (lastEvent); break;
            case SYGFOX_ALM_ZIIN		: w.createSigFoxAlmZI(lastEvent); break;
            case SYGFOX_ALM_ZIOUT		: w.createSigFoxAlmExitZI(lastEvent); break;
            case SYGFOX_ALM_EXVITESSE		: w.createSigFoxAlmSpeed(lastEvent); break;
            case SYGFOX_ALM_DISTMAX		: w.createSigFoxAlmMaxDist(lastEvent); break;
            case SYGFOX_ALM_CLIM		: w.createSigFoxAlmClm(lastEvent); break;
            case SYGFOX_ALM_BATR		: w.createSigFoxAlmBattery(lastEvent); break;
            case SYGFOX_ALM_MAT		: w.createSigFoxAlmMaterial(lastEvent); break;
            case SYGFOX_ALM_SOFT		: w.createSigFoxAlmLogiciel(lastEvent); break;
            case SYGFOX_ALM_MALCL		: w.createSigFoxAlmClient(lastEvent); break;
            case SYGFOX_ALM_UNAUTH		: w.createSigFoxAlmForbiddenBorrow(lastEvent); break;
            }
            w.send();
//        } catch (IOException e) {
//        }
	}

 }
