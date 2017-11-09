package at.fhv.team3.application;

import at.fhv.team3.domain.*;
import at.fhv.team3.domain.dto.CustomerDTO;
import at.fhv.team3.domain.interfaces.Borrowable;
import at.fhv.team3.persistence.*;
import at.fhv.team3.rmi.interfaces.RMIBorrow;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by David on 11/8/2017.
 */
public class BorrowController extends UnicastRemoteObject implements RMIBorrow {

    private BorrowedItemRepository _borrowedItemRepository;

    public BorrowController() throws RemoteException{
        _borrowedItemRepository = BorrowedItemRepository.getInstance();
    }

    //TODO: REVEIW
    public void handOut(Borrowable media, CustomerDTO customer){
            Date date = new Date();
            BorrowedItem item = new BorrowedItem();
            item.setBorrowedDate(date);
            Customer c = new Customer();
            c.fillFromDTO(customer);
            item.setCustomer(c);
            item.setMedia(media);
        if(validateHandOut()) {
            _borrowedItemRepository.save(item);
        }
    }

    //TODO: REVIEW ( getClass möglich? )
    public void handIn(Borrowable media){
        List<BorrowedItem> items = _borrowedItemRepository.getAll();
        if (validateHandIn()) {
            for (BorrowedItem bi : items) {
                if (bi.getMedia().getId() == media.getId() && bi.getMedia().getClass() == media.getClass()) {
                    _borrowedItemRepository.delete((bi));
                }
            }
        }
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
