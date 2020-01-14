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
		SIGFOX_STD, SIGFOX_ALM_ZI, SIGFOX_ALM_EXITZI, SIGFOX_ALM_SPEED, SIGFOX_ALM_MAXDIST, SIGFOX_ALM_CLM, SIGFOX_ALM_BATT, SIGFOX_ALM_MAT, SIGFOX_ALM_LOG, SIGFOX_ALM_CLIENT, SIGFOX_ALM_FORBIDDENBORROW
	}

	private Decision decisionTaker (Event last) {
		switch (last.getUsage().getAlarm()) {
		case 0:
			return Decision.SIGFOX_STD;
		case 1:
			return Decision.SIGFOX_ALM_ZI;
		case 2:
			return Decision.SIGFOX_ALM_EXITZI;
		case 3:
			return Decision.SIGFOX_ALM_SPEED;
		case 4:
			return Decision.SIGFOX_ALM_MAXDIST;
		case 5:
			return Decision.SIGFOX_ALM_CLM;
		case 6:
			return Decision.SIGFOX_ALM_BATT;
		case 7:
			return Decision.SIGFOX_ALM_MAT;
		case 8:
			return Decision.SIGFOX_ALM_LOG;
		case 9:
			return Decision.SIGFOX_ALM_CLIENT;
		case 10:
			return Decision.SIGFOX_ALM_FORBIDDENBORROW;
		}
		return Decision.SIGFOX_STD;
	}

	@Override
	public void doSigFox(Event lastEvent) {
//        try {
            GolfinetteWriter w = new GolfinetteWriter("localhost", Protocol.GOLFINETTES_SIGFOX_PORT);
            switch (decisionTaker(lastEvent)) {
            case SIGFOX_STD		: w.createSigFoxStd (lastEvent); break;
            case SIGFOX_ALM_ZI		: w.createSigFoxAlmZI(lastEvent); break;
            case SIGFOX_ALM_EXITZI		: w.createSigFoxAlmExitZI(lastEvent); break;
            case SIGFOX_ALM_SPEED		: w.createSigFoxAlmSpeed(lastEvent); break;
            case SIGFOX_ALM_MAXDIST		: w.createSigFoxAlmMaxDist(lastEvent); break;
            case SIGFOX_ALM_CLM		: w.createSigFoxAlmClm(lastEvent); break;
            case SIGFOX_ALM_BATT		: w.createSigFoxAlmBattery(lastEvent); break;
            case SIGFOX_ALM_MAT		: w.createSigFoxAlmMaterial(lastEvent); break;
            case SIGFOX_ALM_LOG		: w.createSigFoxAlmLogiciel(lastEvent); break;
            case SIGFOX_ALM_CLIENT		: w.createSigFoxAlmClient(lastEvent); break;
            case SIGFOX_ALM_FORBIDDENBORROW		: w.createSigFoxAlmForbiddenBorrow(lastEvent); break;
            }
            w.send();
//        } catch (IOException e) {
//        }
	}

 }
