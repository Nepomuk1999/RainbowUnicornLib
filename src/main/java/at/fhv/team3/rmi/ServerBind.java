package at.fhv.team3.rmi;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Christoph on 05.11.2017.
 */
public class ServerBind {
    public static void init() {

        try {
            /*
             * Create remote object and export it to use
             * custom socket factories.
             */

            /*
             * Create a registry and bind stub in registry.
             */
            RMIFactory factory = new RMIFactory();
            System.out.println("Creating Registry...");
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry(1099);
            System.out.println("Binding MediaSearchController...");
            registry.rebind("Search", factory.getSearchController());
            System.out.println("Search bound in registry");
            System.out.println("Binding BorrowController...");
            registry.rebind("Borrow", factory.getBorrowController());
            System.out.println("Borrow bound in registry");
            System.out.println("Binding CustomerController...");
            registry.rebind("Customer", factory.getCustomerController());
            System.out.println("Customer bound in registry");
            System.out.println("Binding BookingController...");
            registry.rebind("Booking", factory.getBookingController());
            System.out.println("Booking bound in registry");
            System.out.println("Binding Ldap Controller...");
            registry.rebind("Ldap", factory.getLdapController());
            System.out.println("Binding Message Consumer...");
            registry.rebind("MessageConsumer", factory.getMessageConsumer());
            System.out.println("Binding complete");
        } catch (AccessException e) {
            System.out.println("Could not access RMI");
        } catch (RemoteException e) {
            System.out.println("Remote Exception during binding");
        }
    }
}