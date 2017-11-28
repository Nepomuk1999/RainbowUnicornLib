package at.fhv.team3.applicationbean;

import at.fhv.team3.application.BookingController;
import at.fhv.team3.applicationbean.interfaces.RemoteBookingBeanFace;
import at.fhv.team3.domain.dto.CustomerDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.ValidationResult;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class BookingControllerBean implements RemoteBookingBeanFace {

    private BookingController controller = BookingController.getInstance();


    public List<DTO> getAllBookings() {
        return controller.getAllBookings();
    }

    public List<DTO> getBookingsForMedia(DTO media) {
        return controller.getBookingsForMedia(media);
    }

    public ValidationResult bookItem(DTO dto, CustomerDTO customerDto) {
        return controller.bookItem(dto, customerDto);
    }
}
