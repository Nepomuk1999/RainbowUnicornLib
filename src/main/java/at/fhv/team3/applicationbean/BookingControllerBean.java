package at.fhv.team3.applicationbean;

import at.fhv.team3.application.BookingController;
import at.fhv.team3.applicationbean.interfaces.RemoteBookingBeanFace;
import at.fhv.team3.domain.dto.CustomerDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.ValidationResult;

import javax.ejb.Stateless;
import java.rmi.RemoteException;
import java.util.List;

@Stateless(mappedName = "BookingEJB")
public class BookingControllerBean implements RemoteBookingBeanFace {

    private BookingController _bookingController;

    public BookingControllerBean(){
        try {
            _bookingController = new BookingController();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DTO> getAllBookings() {
        return _bookingController.getAllBookings();
    }

    @Override
    public List<DTO> getBookingsForMedia(DTO media) {
        return _bookingController.getBookingsForMedia(media);
    }

    @Override
    public ValidationResult bookItem(DTO dto, CustomerDTO customerDto) {
        return _bookingController.bookItem(dto, customerDto);
    }

}
