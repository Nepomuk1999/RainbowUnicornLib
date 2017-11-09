package at.fhv.team3.application;

import at.fhv.team3.domain.BorrowedItem;
import at.fhv.team3.domain.Customer;
import at.fhv.team3.domain.interfaces.Borrowable;
import at.fhv.team3.persistence.BorrowedItemRepository;
import at.fhv.team3.rmi.interfaces.RMIBorrow;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

/**
 * Created by David on 11/8/2017.
 */
public class BorrowController extends UnicastRemoteObject implements RMIBorrow {

    private BorrowedItemRepository _borrowedItemRepository;

    public BorrowController() throws RemoteException{
        _borrowedItemRepository = BorrowedItemRepository.getInstance();
    }

    //TODO: REVEIW
    public void handOut(Borrowable media, Customer customer){
        Date date = new Date();
        BorrowedItem item = new BorrowedItem();
        item.setBorrowedDate(date);
        item.setCustomer(customer);
        item.setMedia(media);
        _borrowedItemRepository.save(item);
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
