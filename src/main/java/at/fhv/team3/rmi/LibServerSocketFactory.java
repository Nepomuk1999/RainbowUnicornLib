package at.fhv.team3.rmi;

import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.server.RMIServerSocketFactory;

/**
 * Created by Christoph on 05.11.2017.
 */
public class LibServerSocketFactory implements RMIServerSocketFactory {

    public LibServerSocketFactory() { }

    public ServerSocket createServerSocket(int port) throws IOException
    {
        return new ServerSocket(port);
    }

    public boolean equals(Object obj) {
        return (getClass() == obj.getClass());
    }

}