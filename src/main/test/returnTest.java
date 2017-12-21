import at.fhv.team3.application.BorrowController;
import at.fhv.team3.domain.Book;
import at.fhv.team3.domain.BorrowedItem;
import at.fhv.team3.domain.Customer;
import at.fhv.team3.domain.dto.BookDTO;
import at.fhv.team3.domain.dto.ValidationResult;
import at.fhv.team3.persistence.BookingRepository;
import at.fhv.team3.persistence.BorrowedItemRepository;
import at.fhv.team3.persistence.CustomerRepository;
import domaindummy.BookDummy;
import domaindummy.BorrowedItemDummy;
import domaindummy.CustomerDummy;
import dtodummy.BookDTODummy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class returnTest {

    @InjectMocks
    BorrowController borrowController;

    @Mock
    BorrowedItemRepository borrowedItemRepository;

    private Book book;
    private BookDTO bookDTO;
    private BorrowedItem borrowedItem;
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new CustomerDummy(1, "Hans", "Wurst", true, "email@email.com", "+43 5522 48484");
        book = new Book("DasBuch", "S.M.Pam", "Lovo", "32165897", "vol.20", "4H2");
        book.setBookId(1);
        bookDTO = new BookDTODummy(1, "DasBuch", "Lovo", "S.M.Pam", "32165897", "vol.20", "", "4H2");
        borrowedItem = new BorrowedItemDummy(1, new Date(System.currentTimeMillis() - 1000000), 1, null, customer, book, null, null);

        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void handInTestSucceed() {
        //Given
        ArrayList<BorrowedItem> items = new ArrayList<BorrowedItem>();
        items.add(borrowedItem);
        //When
        when(borrowedItemRepository.getAll()).thenReturn(items);
        ValidationResult vr = borrowController.handIn(bookDTO);
        //Then
        ArrayList<String> messages = vr.getErrorMessages();
        System.out.println("-------------------------");
        System.out.println("Test handOutTestSucceed() Error messages:");
        System.out.println("-------------------------");
        for (String str : messages) {
            System.out.println(str);
        }
        System.out.println("-------------------------");
        assertTrue(messages.isEmpty());
    }


    @Test
    public void handInTestFail() {
        //Given
        ArrayList<BorrowedItem> items = new ArrayList<BorrowedItem>();
        //When
        when(borrowedItemRepository.getAll()).thenReturn(items);
        ValidationResult vr = borrowController.handIn(bookDTO);
        //Then
        ArrayList<String> messages = vr.getErrorMessages();
        System.out.println("-------------------------");
        System.out.println("Test handOutTestSucceed() Error messages:");
        System.out.println("-------------------------");
        for (String str : messages) {
            System.out.println(str);
            assertTrue(str.equals("Media is not borrowed!"));
        }
        System.out.println("-------------------------");

    }

}
