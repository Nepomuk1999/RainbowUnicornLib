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
        if(media instanceof BookDTO){
            Book book = new Book();
            book.fillFromDTO(media);
            item.setBook(book);
        } else if(media instanceof DvdDTO){
            Dvd dvd = new Dvd();
            dvd.fillFromDTO(media);
            item.setDvd(dvd);
        } else if(media instanceof  MagazineDTO){
            Magazine magazine = new Magazine();
            magazine.fillFromDTO(media);
            item.setMagazine(magazine);
        }
        ValidationResult vr = validateHandOut(media, customer);
        if(!vr.hasErrors()) {
            item.setExtendCount(0);
            List<BookedItem> booked = _bookingRepository.getAll();
            BookedItem booking = null;
            for(BookedItem bi : booked){
                Borrowable b = getBorrowable(bi);
                Borrowable tmp;
                if(item.getBook() != null){
                    tmp = item.getBook();
                } else if(item.getDvd() != null){
                    tmp = item.getDvd();
                } else {
                    tmp = item.getMagazine();
                }
                if(b != null && tmp != null) {
                    if (b.getClass() == tmp.getClass()) {
                        if (b.getId() == tmp.getId() && bi.getCustomer().getCustomerId() == c.getCustomerId()) {
                            booking = bi;
                        }
                    }
                }
            }
            if(booking != null) {
                _bookingRepository.delete(booking);
            }
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
               Borrowable borrowable = getBorrowable(bi);
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
        ValidationResult vr = validateExtend(media);
        if (!vr.hasErrors()) {
            for (BorrowedItem bi : items) {
                Borrowable borrowable = getBorrowable(bi);
                if (borrowable != null) {
                    if (borrowable.getId() == media.getId()) {
                        bi.setBorrowedDate(new Date());
                        bi.setExtendCount(bi.getExtendCount() + 1);
                        _borrowedItemRepository.save(bi);
                    }
                }
            }
        }
        return vr;
    }

    public DTO getCustomerByMedia(DTO media){
        Customer customer = new Customer();
        List<BorrowedItem> items = _borrowedItemRepository.getAll();
        Borrowable b = getBorrowableFromDTO(media);
        for(BorrowedItem bi : items){
            Borrowable tmp = getBorrowable(bi);
            if(b.getClass() == tmp.getClass()){
                if(b.getId() == tmp.getId()){
                    customer = bi.getCustomer();
                }
            }
        }
        return customer.createDataTransferObject();
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
    private ValidationResult validateExtend(DTO media) {
        ValidationResult vr = new ValidationResult();
        boolean booked = false;
        List<BookedItem> bookedItems = _bookingRepository.getAll();
        Borrowable b = getBorrowableFromDTO(media);
        if(b != null){
            b.fillFromDTO(media);
            Borrowable tmp;
            for(BookedItem bi : bookedItems){
                tmp = getBorrowable(bi);
                if(tmp != null){
                    if(tmp.getClass() == b.getClass()){
                        if(tmp.getId() == b.getId()){
                            booked = true;
                        }
                    }
                }
            }
        }
        if(booked){
            vr.add("Media is booked and can not be extended!");
        }
        return vr;
    }

    private Borrowable getBorrowableFromDTO(DTO media){
        Borrowable b = null;
        if (media instanceof BookDTO) {
            b = new Book();
        } else if (media instanceof DvdDTO) {
            b = new Dvd();
        } else {
            b = new Magazine();
        }
        b.fillFromDTO(media);
        return b;
    }
    private boolean borrowedItemExists(List<BorrowedItem> items, DTO dto){
        for (BorrowedItem bi : items) {
            Borrowable borrowable = getBorrowable(bi);
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

    private boolean isBooked(DTO dto){
        Borrowable b;
        if (dto instanceof BookDTO) {
             b = new Book();
        } else if (dto instanceof DvdDTO) {
            b = new Dvd();
        } else {
            b = new Magazine();
        }
        b.fillFromDTO(dto);
        List<BookedItem> items = _bookingRepository.getAll();
        Borrowable tmp;
        for(BookedItem item : items){
            tmp = getBorrowable(item);
            if(b.getClass() == tmp.getClass()){
                if(b.isSameMedia(tmp)){
                    return true;
                }
            }
        }
        return false;
    }

    private Borrowable getBorrowable(BorrowedItem borrowedItem) {
        Borrowable borrowable = null;
        if (borrowedItem.getBook() != null) {
            borrowable = borrowedItem.getBook();
        } else if (borrowedItem.getDvd() != null) {
            borrowable = borrowedItem.getDvd();
        } else if (borrowedItem.getMagazine() != null) {
            borrowable = borrowedItem.getMagazine();
        }
        return borrowable;
    }

    public Borrowable getBorrowable(BookedItem bookedItem) {
        Borrowable borrowable = null;
        if (bookedItem.getBook() != null) {
            borrowable = bookedItem.getBook();
        } else if (bookedItem.getDvd() != null) {
            borrowable = bookedItem.getDvd();
        } else if (bookedItem.getMagazine() != null) {
            borrowable = bookedItem.getMagazine();
        }
        return borrowable;
    }
}
