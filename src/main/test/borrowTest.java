import at.fhv.team3.application.BorrowController;
import at.fhv.team3.domain.*;
import at.fhv.team3.domain.dto.CustomerDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.persistence.BookingRepository;
import at.fhv.team3.persistence.BorrowedItemRepository;
import at.fhv.team3.persistence.CustomerRepository;
import domaindummy.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.HashMap;


@RunWith(JUnit4.class)
public class borrowTest {

    @Mock
    BorrowController borrowController;

    @InjectMocks
    CustomerRepository customerRepository;

    @InjectMocks
    BookingRepository bookingRepository;

    @InjectMocks
    BorrowedItemRepository borrowedItemRepository;

    private Customer customer;
    private Book book;
    private Magazine magazine;
    private Dvd dvd;
    private BookedItem bookedItem1;
    private BookedItem bookedItem2;
    private BookedItem bookedItem3;


    DTO media;
    CustomerDTO customerDTO;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        //inizialise object fr tests
        customer = new CustomerDummy(1, "Hans", "Wurst", true, "email@email.com", "+43 5522 48484");
        book = new BookDummy("DasBuch", "S.M.Pam", "Lovo", "32165897", "vol.20", "4H2");
        magazine = new MagazineDummy(1, "Was ist was...", "Nr.1", "Lovo", "www.google.de", "8W3");
        dvd = new DvdDummy(1, "Einhörner im Wald", "J.K Bowl", "www.google.de", "7E3");
        bookedItem1 = new BookedItemDummy(2, customer, book, null, null, new Date());
        bookedItem2 = new BookedItemDummy(3, customer, null, dvd, null, new Date());
        bookedItem3 = new BookedItemDummy(4, customer, null, null, magazine, new Date());
    }


    @Test
    public void handOutTest() {
        //given

        //when

        //then

    }


}
