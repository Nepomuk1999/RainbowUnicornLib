package at.fhv.team3.application;

import at.fhv.team3.domain.*;
import at.fhv.team3.domain.dto.*;
import at.fhv.team3.persistence.BookingRepository;
import at.fhv.team3.rmi.interfaces.RMIBooking;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

/**
 * Created by David on 11/14/2017.
 */
public class BookingController extends UnicastRemoteObject implements RMIBooking{

    private BookingRepository _bookingRepository;

    public BookingController() throws RemoteException{
        _bookingRepository = BookingRepository.getInstance();
    }

    public void bookItem(DTO dto, CustomerDTO customerDto){
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
        if(validateBooking(bookedItem)) {
            _bookingRepository.save(bookedItem);
        }
    }

    private boolean validateBooking(BookedItem bookedItem){
        return true;
    }
}
