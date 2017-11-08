package at.fhv.team3.application;

import at.fhv.team3.domain.Customer;
import at.fhv.team3.domain.interfaces.Borrowable;
import at.fhv.team3.persistence.BorrowedItemRepository;
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

    private CustomerRepository _customerRepository;
    private BorrowedItemRepository _borrowedItemRepository;

    public BorrowController() throws RemoteException{
        _customerRepository = CustomerRepository.getInstance();
        _borrowedItemRepository = BorrowedItemRepository.getInstance();
    }

    //TODO: implement
    public boolean handOut(Borrowable media){
        return false;
    }

    //TODO: implement
    public void handIn(Borrowable media){

    }

    //TODO: implement
    private boolean validateHandIn(){
        return true;
    }

    //TODO: implement
    private boolean validateHandOut(){
        return true;
    }

    private List<Customer> findCustomer(String term){
        List<Customer> allCustomers = _customerRepository.getAll();
        List<Customer> matchingCustomers = new ArrayList<Customer>();
        for(Customer c : allCustomers){
            if(c.containsSearchTerm(term)){
                matchingCustomers.add(c);
            }
        }
        return matchingCustomers;
    }
}
