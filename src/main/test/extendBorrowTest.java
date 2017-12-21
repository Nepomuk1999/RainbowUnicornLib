import at.fhv.team3.application.BorrowController;
import at.fhv.team3.domain.*;
import at.fhv.team3.domain.dto.BookDTO;
import at.fhv.team3.domain.dto.BorrowedItemDTO;
import at.fhv.team3.domain.dto.CustomerDTO;
import at.fhv.team3.domain.dto.ValidationResult;
import at.fhv.team3.persistence.BookingRepository;
import at.fhv.team3.persistence.BorrowedItemRepository;
import domaindummy.*;
import dtodummy.BookDTODummy;
import dtodummy.BorrowedItemDTODummy;
import dtodummy.CustomerDTODummy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class extendBorrowTest {

    @InjectMocks
    BorrowController borrowController;

    @Mock
    BorrowedItemRepository borrowedItemRepository;

    @Mock
    BookingRepository bookingRepository;


    private Customer customer;
    private Book book;


    private BookDTO bookDTO;
    private ArrayList<BorrowedItem> borrowedItems;
    private BorrowedItem borrowedItem;


    @Before
    public void setUp() throws Exception {
        BorrowController borrowController = new BorrowController();
        //inizialise object für tests
        customer = new CustomerDummy(1, "Hans", "Wurst", true, "email@email.com", "+43 5522 48484");

        book = new Book("DasBuch", "S.M.Pam", "Lovo", "32165897", "vol.20", "4H2");
        book.setBookId(1);

        bookDTO = new BookDTODummy(1, "DasBuch", "Lovo", "S.M.Pam", "32165897", "vol.20", "", "4H2");


        //Configurate Mock for borrowedItems
        borrowedItems = new ArrayList<BorrowedItem>();
        borrowedItem = new BorrowedItem();
        borrowedItem.setBook(book);

        Date date = new Date(System.currentTimeMillis() - 1000000);
        borrowedItem.setBorrowedDate(date);
        borrowedItem.setExtendCount(0);
        borrowedItem.setCustomer(customer);
        borrowedItem.setBorrowedId(1);

        borrowedItems.add(borrowedItem);

        when(borrowedItemRepository.getAll()).thenReturn(borrowedItems);

        //Configurate Mock for bookedItems
        ArrayList<BookedItem> items = new ArrayList<BookedItem>();
        when(bookingRepository.getAll()).thenReturn(items);

    }

    @Test
    public void extendSuccssesfull() throws RemoteException {
        ValidationResult result = borrowController.extend(bookDTO);
        ArrayList<String> messages = result.getErrorMessages();
        System.out.println("-------------------------");
        System.out.println("Test extendSuccssesfull() Error messages:");
        System.out.println("-------------------------");
        for (String str : messages) {
            System.out.println(str);
        }
        System.out.println("-------------------------");
        assertTrue(messages.isEmpty());
    }

    @Test
    public void extendFail() throws RemoteException {
        borrowedItem.setExtendCount(3);
        ValidationResult result = borrowController.extend(bookDTO);
        ArrayList<String> messages = result.getErrorMessages();
        System.out.println("-------------------------");
        System.out.println("Test extendFail() Error messages:");
        System.out.println("-------------------------");
        for (String str : messages) {
            System.out.println(str);
            assertTrue(str.equals("The loan cannot be extended any more!"));
        }
        System.out.println("-------------------------");
    }
}
