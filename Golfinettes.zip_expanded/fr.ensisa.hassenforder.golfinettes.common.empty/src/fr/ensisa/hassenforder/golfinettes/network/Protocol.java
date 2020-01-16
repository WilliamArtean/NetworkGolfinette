
package fr.ensisa.hassenforder.golfinettes.network;

public class Protocol {

    public static final int GOLFINETTES_SIGFOX_PORT = 6666;
    public static final int GOLFINETTES_WIFI_PORT	= 7777;

    // left to help you (or not)
	public static final int SIGFOX_STD				= 0x01;
	public static final int WIFI_STD				= 0x02;
	
	// wifi
	 public static final int REQUEST_CONNECTION = 0;
	 public static final int SENDING_ID = 0x0111;
	 public static final int SENDING_PASSWORD = 0x0112;
	 
	 
	 
	 public static final int REPLY_OK = 0x0210;
	 public static final int REPLY_KO = 0x0220;
	 public static final int REPLY_ID = 0;
	 public static final int REPLY_PASSWORD = 0x0240;
	 
	 public static final int SYGFOX_ALM_ZIIN = 1;
	 public static final int SYGFOX_ALM_ZIOUT = 2;
	 public static final int SYGFOX_ALM_EXVITESSE = 3;
	 public static final int SYGFOX_ALM_DISTMAX = 4;
	 public static final int SYGFOX_ALM_CLIM= 5;
	 public static final int SYGFOX_ALM_BATR=6;
	 public static final int SYGFOX_ALM_MAT=7;
	 public static final int SYGFOX_ALM_SOFT=8;
	 public static final int SYGFOX_ALM_MALCL=9;
	 public static final int SYGFOX_ALM_UNAUTH=10;
	 
	 public static final int SYGFOX_EMP=11;
	 
	 
	 
	 
	 
}

