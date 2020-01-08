package fr.ensisa.hassenforder.golfinettes.admin.network;

import java.io.OutputStream;

import fr.ensisa.hassenforder.golfinettes.admin.model.Version;
import fr.ensisa.hassenforder.golfinettes.network.Protocol;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;

public class AdminWriter extends BasicAbstractWriter {

    public AdminWriter(OutputStream outputStream) {
        super(outputStream);
    }

}
