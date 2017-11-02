package at.fhv.team3.persistence;

import at.fhv.team3.domain.Book;
import at.fhv.team3.domain.Media;
import at.fhv.team3.domain.MediaType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;


/**
 * Created by Christoph on 02.11.2017.
 */
public class TestingMain {

    private static SessionFactory factory;
    public static void main(String[] args) {

        factory = HibernateUtil.getSessionFactory();

        TestingMain testingMain = new TestingMain();
        Date date = new Date();

        Media media = new Media(MediaType.BOOK, date);

        testingMain.addMedia(media);

        testingMain.addBook("Das Buch", "Wolfgang Hohlbein", "Ueberreuter", "978-3800053254", "E7S3P1", media);


        factory.close();
    }

    public Integer addMedia(Media media) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer mediaId = null;

        try {
            tx = session.beginTransaction();
            mediaId = (Integer) session.save(media);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return mediaId;
    }

    public Integer addBook(String title, String author, String publisher, String isbn, String shelfPos, Media media){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer bookId = null;

        try {
            tx = session.beginTransaction();
            Book book = new Book(title, author, publisher, isbn, shelfPos, media);
            bookId = (Integer) session.save(book);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return bookId;
    }

}
