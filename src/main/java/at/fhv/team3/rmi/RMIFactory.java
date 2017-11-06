package at.fhv.team3.rmi;

import at.fhv.team3.application.MediaSearchController;
import at.fhv.team3.rmi.interfaces.RMIFactoryInterface;
import at.fhv.team3.rmi.interfaces.RMIMediaSearch;

import java.rmi.RemoteException;

/**
 * Created by Christoph on 06.11.2017.
 */
public class RMIFactory implements RMIFactoryInterface {

    public RMIFactory() {

    }

    public RMIMediaSearch getSearchController () throws RemoteException {
        return new MediaSearchController();
    }
}
