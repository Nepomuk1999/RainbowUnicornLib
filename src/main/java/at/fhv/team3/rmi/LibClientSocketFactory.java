package at.fhv.team3.rmi;

import java.io.*;
import java.net.*;
import java.rmi.server.*;

/**
 * Created by Christoph on 05.11.2017.
 */
public class LibClientSocketFactory implements RMIClientSocketFactory, Serializable {

    public LibClientSocketFactory() { }

    public Socket createSocket(String host, int port)
            throws IOException
    {
        return new Socket(host, port);
    }

    public boolean equals(Object obj) {
        return (getClass() == obj.getClass());
    }
}