package at.fhv.team3.rmi.interfaces;

import at.fhv.team3.domain.Customer;
import at.fhv.team3.domain.interfaces.Borrowable;

import java.rmi.Remote;

/**
 * Created by David on 11/8/2017.
 */
public interface RMIBorrow  extends Remote {

    public void handOut(Borrowable media, Customer customer);
    public void handIn(Borrowable media);
}
