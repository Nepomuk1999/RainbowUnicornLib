package at.fhv.team3.application;

import at.fhv.team3.domain.Customer;
import at.fhv.team3.domain.interfaces.Borrowable;
import at.fhv.team3.persistence.CustomerRepository;
import at.fhv.team3.rmi.interfaces.RMIBorrow;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 11/8/2017.
 */
public class BorrowController   extends UnicastRemoteObject implements RMIBorrow {

    public BorrowController() throws RemoteException{

    }

    public boolean handOut(Borrowable media){
        return false;
    }

    public void handIn(Borrowable media){

    }

    private List<Customer> findCustomer(String term){
        List<Customer> allCustomers = CustomerRepository.getInstance().getAll();
        List<Customer> matchingCustomers = new ArrayList<Customer>();
        for(Customer c : allCustomers){
            if(c.containsSearchTerm(term)){
                matchingCustomers.add(c);
            }
        }
        return matchingCustomers;
    }
}
