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

    private BorrowedItemRepository _borrowedItemRepository;

    public BorrowController() throws RemoteException{
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

}
