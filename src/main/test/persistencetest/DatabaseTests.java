package persistenceTest;

import at.fhv.team3.domain.Book;
import at.fhv.team3.domain.Dvd;
import at.fhv.team3.domain.Magazine;
import at.fhv.team3.persistence.BookRepository;
import at.fhv.team3.persistence.DvdRepository;
import at.fhv.team3.persistence.MagazineRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DatabaseTests {

    private BookRepository bookRepository;
    private MagazineRepository magazineRepository;
    private DvdRepository dvdRepository;

    //TODO: FIX TESTS
    public DatabaseTests() {
        bookRepository = BookRepository.getInstance();
        magazineRepository = MagazineRepository.getInstance();
        dvdRepository = DvdRepository.getInstance();

    }

    @Test
    public void TestConnectionBook() {
        List<Book> books = bookRepository.getAll();
        Assert.assertFalse(books.size() == 0);
    }

    @Test
    public void TestConnectionDvd() {
        List<Dvd> dvds = dvdRepository.getAll();
        Assert.assertFalse(dvds.size() == 0);
    }

    @Test
    public void TestConnectionMagazine() {
        List<Magazine> magList = magazineRepository.getAll();
        Assert.assertFalse(magList.size() == 0);
    }

}
