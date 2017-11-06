package at.fhv.team3.application;

import at.fhv.team3.rmi.RMIFactory;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Christoph on 05.11.2017.
 */
public class ServerSearchBind {
    public static void main(String args[]) {

        try {
            /*
             * Create remote object and export it to use
             * custom socket factories.
             */

            /*
             * Create a registry and bind stub in registry.
             */
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.rebind("Search", new RMIFactory().getSearchController());
            System.out.println("Search bound in registry");

        } catch (Exception e) {
            System.out.println("MediaSearchController exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}