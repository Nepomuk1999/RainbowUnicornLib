package at.fhv.team3.application;

import at.fhv.team3.domain.*;
import at.fhv.team3.domain.dto.*;
import at.fhv.team3.domain.interfaces.Borrowable;
import at.fhv.team3.persistence.BookingRepository;
import at.fhv.team3.rmi.interfaces.RMIBooking;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by David on 11/14/2017.
 */
public class BookingController extends UnicastRemoteObject implements RMIBooking{

    private BookingRepository _bookingRepository;

    public BookingController() throws RemoteException{
        _bookingRepository = BookingRepository.getInstance();
    }

    public List<DTO> getAllBookings(){
        List<BookedItem> allBookings = _bookingRepository.getAll();
        List<DTO> dtos = new ArrayList<DTO>();
        for(BookedItem bi : allBookings){
            dtos.add(bi.createDataTransferObject());
        }
        return dtos;
    }

    public List<DTO> getBookingsForMedia(DTO media){
        List<BookedItem> allBookings = _bookingRepository.getAll();
        List<DTO> matching = new ArrayList<DTO>();
        Borrowable b = null;
        if(media.getClass() == BookDTO.class){
            b = new Book();
            b.fillFromDTO(media);
        } else if(media.getClass() == DvdDTO.class){
           b = new Dvd();
           b.fillFromDTO(media);
        } else if(media.getClass() == MagazineDTO.class){
            b = new Magazine();
            b.fillFromDTO(media);
        }
        if(b != null) {
            Borrowable tmp = null;
            for (BookedItem bi : allBookings) {
                if(bi.getBook() != null){
                    tmp = bi.getBook();
                } else if(bi.getDvd() != null){
                    tmp = bi.getDvd();
                } else if(bi.getMagazine() != null){
                    tmp = bi.getMagazine();
                }
                if(tmp.getClass() == b.getClass()){
                    if(tmp.getId() == b.getId()){
                        matching.add(bi.createDataTransferObject());
                    }
                }
            }
        }
        return matching;
    }

    public ValidationResult bookItem(DTO dto, CustomerDTO customerDto){

        BookedItem bookedItem = new BookedItem();
        Customer customer = new Customer();
        customer.fillFromDTO(customerDto);
        bookedItem.setCustomer(customer);
        bookedItem.setDate(new Date());
        if (dto instanceof BookDTO) {
            Book b = new Book();
            b.fillFromDTO(dto);
            bookedItem.setBook(b);
        } else if (dto instanceof DvdDTO) {
            Dvd d = new Dvd();
            d.fillFromDTO(dto);
            bookedItem.setDvd(d);
        } else {
            Magazine m = new Magazine();
            m.fillFromDTO(dto);
            bookedItem.setMagazine(m);
        }
        if(!validateBooking(bookedItem, customer).hasErrors()) {
            _bookingRepository.save(bookedItem);
        }
        return new ValidationResult();
    }


    // TODO
    private ValidationResult validateBooking(BookedItem bookedItem, Customer customer){
        ValidationResult validationResult = new ValidationResult();
        List<BookedItem> bookedItems = _bookingRepository.getAll();
        if (bookedItem.getBook() != null) {
            for (BookedItem booked: bookedItems) {
                if (booked.getMedia().equals(bookedItem) && booked.getCustomer().equals(bookedItem.getCustomer())) {
                    validationResult.add("The Customer already has this book booked");
                }
            }
        } else if (bookedItem.getDvd() != null) {
            for (BookedItem booked: bookedItems) {
                if (booked.getMedia().equals(bookedItem) && booked.getCustomer().equals(bookedItem.getCustomer())) {
                    validationResult.add("The Customer already has this dvd booked");
                }
            }
        } else if (bookedItem.getMagazine() != null) {
            for (BookedItem booked: bookedItems) {
                if (booked.getMedia().equals(bookedItem) && booked.getCustomer().equals(bookedItem.getCustomer())) {
                    validationResult.add("The Customer already has this magazine booked");
                }
            }
        } else {
            validationResult.add("There was no media submitted with the request.");
        }
        return validationResult;
    }
}
