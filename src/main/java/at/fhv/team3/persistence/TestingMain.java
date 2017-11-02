package at.fhv.team3.persistence;

import at.fhv.team3.domain.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 * Created by Christoph on 02.11.2017.
 */
public class TestingMain {

    private static SessionFactory factory;
    public static void main(String[] args) {

        factory = HibernateUtil.getSessionFactory();

        TestingMain testingMain = new TestingMain();

        testingMain.addBook("Das Buch", "Wolfgang Hohlbein", "Ueberreuter", "978-3800053254");

    }

    public Integer addBook(String title, String author, String publisher, String isbn){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer bookId = null;

        try {
            tx = session.beginTransaction();
            Book book = new Book();
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
