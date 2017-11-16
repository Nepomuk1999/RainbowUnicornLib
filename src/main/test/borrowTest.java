import at.fhv.team3.application.BorrowController;
import at.fhv.team3.domain.*;
import at.fhv.team3.domain.dto.BookDTO;
import at.fhv.team3.domain.dto.CustomerDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.ValidationResult;
import at.fhv.team3.persistence.BookingRepository;
import at.fhv.team3.persistence.BorrowedItemRepository;
import at.fhv.team3.persistence.CustomerRepository;
import domaindummy.*;
import dtodummy.BookDTODummy;
import dtodummy.CustomerDTODummy;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


@RunWith(JUnit4.class)
public class borrowTest {

    @InjectMocks
    BorrowController borrowController;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    BookingRepository bookingRepository;

    @Mock
    BorrowedItemRepository borrowedItemRepository;


    private Customer customer;
    private Book book;
    private Magazine magazine;
    private Dvd dvd;
    private BookedItem bookedItem1;
    private BookedItem bookedItem2;
    private BookedItem bookedItem3;
    private BookDTO bookDTO;
    private CustomerDTO customerDTO;
    //private BorrowedItem borrowedItem;


    @Before
    public void setUp() throws Exception {
        //inizialise object für tests
        customer = new CustomerDummy(1, "Hans", "Wurst", true, "email@email.com", "+43 5522 48484");
        book = new BookDummy("DasBuch", "S.M.Pam", "Lovo", "32165897", "vol.20", "4H2");
        magazine = new MagazineDummy(1, "Was ist was...", "Nr.1", "Lovo", "www.google.de", "8W3");
        dvd = new DvdDummy(1, "Einhörner im Wald", "J.K Bowl", "www.google.de", "7E3");
        bookedItem1 = new BookedItemDummy(2, null, book, null, null, new Date());
        bookedItem2 = new BookedItemDummy(3, null, null, dvd, null, new Date());
        bookedItem3 = new BookedItemDummy(4, null, null, null, magazine, new Date());
        bookDTO = new BookDTODummy(1, "Das Buch", "Lovo", "Hutter", "45665456", "vol.1", "", "6H5");
        customerDTO = new CustomerDTODummy(2, "Anelise", "Schmidt", true, "a.s@as.as", "+430190812812");
        mock(CustomerRepository.class);

        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void handOutTestSucceed() throws RemoteException {
        //given
        ArrayList<BookedItem> bookedItems = new ArrayList<BookedItem>();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        Customer c = new Customer();
        c.fillFromDTO(customerDTO);
        customers.add(c);
        when(bookingRepository.getAll()).thenReturn(new ArrayList<BookedItem>());
        when(customerRepository.getAll()).thenReturn(customers);
        when(borrowedItemRepository.getAll()).thenReturn(new ArrayList<BorrowedItem>());
        //when
        ValidationResult result = borrowController.handOut(bookDTO, customerDTO);
        //then
        ArrayList<String> messages = result.getErrorMessages();
        System.out.println("-------------------------");
        System.out.println("Test handOutTestSucceed() Error messages:");
        System.out.println("-------------------------");
        for (String str : messages) {
            System.out.println(str);
        }
        System.out.println("-------------------------");
        assertTrue(messages.isEmpty());
    }


    @Ignore
    @Test
    public void handOutTestFail() throws RemoteException {
        //given
        BorrowController borrowController = new BorrowController();
        ArrayList<BookedItem> bookedItems = new ArrayList<BookedItem>();
        bookedItems.add(bookedItem1);
        bookedItems.add(bookedItem2);
        bookedItems.add(bookedItem3);
        when(bookingRepository.getAll()).thenReturn(bookedItems);


        //when

        //then

    }


}
