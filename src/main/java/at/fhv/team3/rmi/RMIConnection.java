package at.fhv.team3.rmi;

import at.fhv.team3.application.MediaSearchController;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.rmi.interfaces.RMIMediaSearch;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


/**
 * Created by David on 11/2/2017.
 */
public class RMIConnection {

    public RMIConnection(){
        try {
            LocateRegistry.createRegistry(1099);
            //TODO: Verify
            RMIMediaSearch rms = new MediaSearchController();


            try {
                Naming.rebind("rmi://localhost/RMIMediaSearch", rms);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
