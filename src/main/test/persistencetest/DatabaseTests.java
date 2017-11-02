package persistencetest;

import at.fhv.team3.domain.Book;
import at.fhv.team3.domain.Dvd;
import at.fhv.team3.domain.Magazine;
import at.fhv.team3.domain.Media;
import at.fhv.team3.persistence.BookRepository;
import at.fhv.team3.persistence.DvdRepository;
import at.fhv.team3.persistence.MagazinRepository;
import at.fhv.team3.persistence.MediaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DatabaseTests {

    private BookRepository bookRepository;
    private MagazinRepository magazinRepository;
    private DvdRepository dvdRepository;
    private MediaRepository mediaRepository;

    //TODO: FIX TESTS
    public DatabaseTests() {
        bookRepository = BookRepository.getInstance();
        magazinRepository = MagazinRepository.getInstance();
        dvdRepository = DvdRepository.getInstance();
        mediaRepository = MediaRepository.getInstance();
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
    public void TestConnectionMagazin() {
        List<Magazine> magList = magazinRepository.getAll();
        Assert.assertFalse(magList.size() == 0);
    }

    @Test
    public void TestConnectionMedia() {
        List<Media> mediaList = mediaRepository.getAll();
        Assert.assertFalse(mediaList.size() == 0);
    }
}
