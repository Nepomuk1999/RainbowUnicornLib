package at.fhv.team3.application;

import at.fhv.team3.domain.*;
import at.fhv.team3.domain.dto.*;
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
    public boolean handOut(DTO media, CustomerDTO customer){
            Date date = new Date();
            BorrowedItem item = new BorrowedItem();
            item.setBorrowedDate(date);
            Customer c = new Customer();
            c.fillFromDTO(customer);
            item.setCustomer(c);
            if(media.getClass() == BookDTO.class){
                Book book = new Book();
                book.fillFromDTO(media);
                item.setBook(book);
            } else if(media.getClass() == DvdDTO.class){
                Dvd dvd = new Dvd();
                dvd.fillFromDTO(media);
                item.setDvd(dvd);
            } else if(media.getClass() == MagazineDTO.class){
                Magazine magazine = new Magazine();
                magazine.fillFromDTO(media);
                item.setMagazine(magazine);
            }
        if(validateHandOut()) {
            _borrowedItemRepository.save(item);
            return true;
        }
        return false;
    }

    //TODO: REVIEW
    public boolean handIn(DTO media){
        List<BorrowedItem> items = _borrowedItemRepository.getAll();
        if (validateHandIn()) {
            for (BorrowedItem bi : items) {
                Borrowable borrowable = null;
                if(bi.getBook() != null){
                    borrowable = bi.getBook();
                } else if(bi.getDvd() != null){
                    borrowable = bi.getDvd();
                } else if(bi.getMagazine() != null){
                    borrowable = bi.getMagazine();
                }
                if(borrowable != null) {
                    if (borrowable.getId() == media.getId()) {
                        _borrowedItemRepository.delete((bi));
                        return true;
                    }
                }
            }
        }
        return false;
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
