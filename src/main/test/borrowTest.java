import at.fhv.team3.application.BorrowController;
import at.fhv.team3.domain.*;
import at.fhv.team3.domain.dto.CustomerDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.persistence.BookingRepository;
import at.fhv.team3.persistence.BorrowedItemRepository;
import at.fhv.team3.persistence.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        //initialize customer
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setEmail("Unicorn@lib.uk");
        customer.setFirstName("Lanzelot");
        customer.setLastName("Einhorn");
        customer.setPhoneNumber("66666666666");
        customer.setSubscription(true);
        //initialize Media
        book = new Book();
        book.setAuthor("Einhornerich");
        book.setBookId(1);
        book.setEdition("alpha");
        book.setIsbn("gotdigarnixa");
        book.setPictureURL("http://www.kotzendes-einhorn.de/blog/wp-content/uploads/2014/10/kotzendeseinhorn.gif");
        book.setShelfPos("vandiandern");
        book.setTitle("Von kotzenden Einhörnern");

        magazine = new Magazine();
        magazine.setEdition("vol.1");
        magazine.setMagazineId(1);
        magazine.setPictureURL("http://www.kotzendes-einhorn.de/blog/wp-content/uploads/2014/10/kotzendeseinhorn.gif");
        magazine.setPublisher("UnitedUnicorns");
        magazine.setShelfPos("dsfg");
        magazine.setTitle("die schönsten Einhörner der Welt");

        dvd.setDvdId(1);
        dvd.setPictureURL("http://www.kotzendes-einhorn.de/blog/wp-content/uploads/2014/10/kotzendeseinhorn.gif)");
        dvd.setRegisseur("Einhorn Spielberg");
        dvd.setShelfPos("d<sgv");
        dvd.setTitle("Der Einhorn Report");


        //initialise BookedItems
        bookedItem1 = new BookedItem();
        bookedItem1.setBook(book);
        bookedItem1.setBookingId(1);
        bookedItem1.setCustomer(customer);

        bookedItem2 = new BookedItem();
        bookedItem2.setMagazine(magazine);
        bookedItem2.setBookingId(1);
        bookedItem2.setCustomer(customer);

        bookedItem3 = new BookedItem();
        bookedItem3.setDvd(dvd);
        bookedItem3.setBookingId(1);
        bookedItem3.setCustomer(customer);

        //initialise DTOs


    }


    @Test
    public void handOutTest() {
        //given


        BookedItem itemMock1 = new BookedItem();
        //when

        //then

    }


}
