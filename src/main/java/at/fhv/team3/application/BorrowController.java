package at.fhv.team3.application;

import at.fhv.team3.domain.*;
import at.fhv.team3.domain.dto.*;
import at.fhv.team3.domain.interfaces.Borrowable;
import at.fhv.team3.persistence.*;
import at.fhv.team3.rmi.interfaces.RMIBorrow;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

/**
 * Created by David on 11/8/2017.
 */
public class BorrowController extends UnicastRemoteObject implements RMIBorrow {

    private BorrowedItemRepository _borrowedItemRepository;
    private CustomerRepository _customerRepository;
    private BookingRepository _bookingRepository;

    public BorrowController() throws RemoteException{
        _borrowedItemRepository = BorrowedItemRepository.getInstance();
        _customerRepository = CustomerRepository.getInstance();
        _bookingRepository = BookingRepository.getInstance();
    }

    //TODO: REVEIW
    public ValidationResult handOut(DTO media, CustomerDTO customer){
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
        ValidationResult vr = validateHandOut(media, customer);
        if(!vr.hasErrors()) {
            _borrowedItemRepository.save(item);

        }
        return vr;
    }



    //TODO: REVIEW
    public ValidationResult handIn(DTO media){
        List<BorrowedItem> items = _borrowedItemRepository.getAll();
        ValidationResult vr = validateHandIn(media);
        if (!vr.hasErrors()) {
            for (BorrowedItem bi : items) {
               Borrowable borrowable = getMediaFromBorrowedItem(bi);
                if(borrowable != null) {
                    if (borrowable.getId() == media.getId()) {
                        _borrowedItemRepository.delete((bi));
                    }
                }
            }
        }
        return vr;
    }

    public ValidationResult extend(DTO media) {
        List<BorrowedItem> items = _borrowedItemRepository.getAll();
        ValidationResult vr = validateExtend();
        if (!vr.hasErrors()) {
            for (BorrowedItem bi : items) {
                Borrowable borrowable = getMediaFromBorrowedItem(bi);
                if (borrowable != null) {
                    if (borrowable.getId() == media.getId()) {
                        bi.setBorrowedDate(new Date());
                        _borrowedItemRepository.save(bi);
                    }
                }
            }
        }
        return vr;
    }

    //TODO: REVIEW
    private ValidationResult validateHandIn(DTO dto){
        ValidationResult vr = new ValidationResult();
        List<BorrowedItem> items = _borrowedItemRepository.getAll();
        if(!borrowedItemExists(items, dto)){
            vr.add("Media is not borrowed!");
        }
        return vr;
    }

    //TODO: REVIEW
    private ValidationResult validateHandOut(DTO dto, CustomerDTO customer){
        ValidationResult vr = new ValidationResult();
        List<BorrowedItem> items = _borrowedItemRepository.getAll();
        if(borrowedItemExists(items, dto)){
            vr.add("Media is already borrowed!");
        }

        List<Customer> customers = _customerRepository.getAll();
        Customer c = new Customer();
        c.fillFromDTO(customer);
        if(!customerExists(customers, c)){
            vr.add("Customer does not exist!");
        }

        if(!c.getSubscription()){
            vr.add("Customers subscription expired!");
        }

        return vr;
    }

    //TODO: implement
    private ValidationResult validateExtend() { return new ValidationResult(); }

    private boolean borrowedItemExists(List<BorrowedItem> items, DTO dto){
        for (BorrowedItem bi : items) {
            Borrowable borrowable = getMediaFromBorrowedItem(bi);
            if(borrowable != null) {
                if (borrowable.getId() == dto.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean customerExists(List<Customer> customers, Customer customer){
        for(Customer c : customers){
            if(customer.equals(c)){
                return true;
            }
        }
        return false;
    }

    private Borrowable getMediaFromBorrowedItem(BorrowedItem bi){
        Borrowable borrowable = null;
        if(bi.getBook() != null){
            borrowable = bi.getBook();
        } else if(bi.getDvd() != null){
            borrowable = bi.getDvd();
        } else if(bi.getMagazine() != null){
            borrowable = bi.getMagazine();
        }
        return borrowable;
    }
}
