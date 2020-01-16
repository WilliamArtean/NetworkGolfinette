package fr.ensisa.hassenforder.golfinettes.admin.network;

import java.io.OutputStream;

import fr.ensisa.hassenforder.golfinettes.admin.model.Version;
import fr.ensisa.hassenforder.golfinettes.network.Protocol;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;

public class AdminWriter extends BasicAbstractWriter {

    public AdminWriter(OutputStream outputStream) {
        super(outputStream);
    }
    
    public void sendSoftwareUpdate(Version version)
    {
    	String numV = version.getVersion();
    	String content1 = version.getFileContent1();
    	byte content2 [] = version.getFileContent2();
    	this.writeInt(13);
    	this.writeInt(content2.length);
    	this.writeString(numV);
    	this.writeString(content1);
    	for (int i=0;i< content2.length;i++)
    	{
    		this.writeByte(content2[i]);
    	}
    	this.send();
    }
    
    public void sendMapUpdate(Version version)
    {
    	String numV = version.getVersion();
    	String content1 = version.getFileContent1();
    	byte content2 [] = version.getFileContent2();
    	this.writeInt(14);
    	this.writeInt(content2.length);
    	this.writeString(numV);
    	this.writeString(content1);
    	for (int i=0;i< content2.length;i++)
    	{
    		this.writeByte(content2[i]);
    	}
    	this.send();
    }
    
    public void sendUsersUpdate(Version version)
    {
    	String numV = version.getVersion();
    	String content1 = version.getFileContent1();
    	byte content2 [] = version.getFileContent2();
    	this.writeInt(15);
    	this.writeInt(content2.length);
    	this.writeString(numV);
    	this.writeString(content1);
    	for (int i=0;i< content2.length;i++)
    	{
    		this.writeByte(content2[i]);
    	}
    	this.send();
    }

}
