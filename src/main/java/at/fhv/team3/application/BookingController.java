package at.fhv.team3.application;

import at.fhv.team3.domain.dto.CustomerDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.persistence.BookingRepository;
import at.fhv.team3.rmi.interfaces.RMIBooking;

/**
 * Created by David on 11/14/2017.
 */
public class BookingController implements RMIBooking{

    private BookingRepository _bookingRepository;

    public BookingController(){
        _bookingRepository = BookingRepository.getInstance();
    }

    public void bookItem(DTO item, CustomerDTO customer){

    }

    private boolean validateBooking(){
        return true;
    }
}
