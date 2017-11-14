package at.fhv.team3.rmi;

import at.fhv.team3.application.BookingController;
import at.fhv.team3.application.BorrowController;
import at.fhv.team3.application.CustomerController;
import at.fhv.team3.application.MediaSearchController;
import at.fhv.team3.rmi.interfaces.*;

import java.rmi.RemoteException;

/**
 * Created by Christoph on 06.11.2017.
 */
public class RMIFactory implements RMIFactoryInterface {

    public RMIMediaSearch getSearchController () throws RemoteException {
        return new MediaSearchController();
    }

    public RMIBorrow getBorrowController() throws RemoteException {
        return new BorrowController();
    }

    public RMICustomer getCustomerController() throws RemoteException {
        return new CustomerController();
    }

    public RMIBooking getBookingController() throws RemoteException {
        return new BookingController();
    }
}
