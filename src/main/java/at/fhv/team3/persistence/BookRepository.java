package at.fhv.team3.persistence;

import at.fhv.team3.domain.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BookRepository extends Repository<Book> {
    private static BookRepository ourInstance = new BookRepository();

    public static BookRepository getInstance() {
        return ourInstance;
    }

    private BookRepository() {
    }

    public List<Book> getAll() {
        Session session = sessionFactory.openSession();
        List books = new LinkedList<Book>();
        try {
            transaction = session.beginTransaction();
            books = session.createNativeQuery("select * from book").list();
            transaction.commit();
            return books;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Book get all error:" + ex);
        } finally {
            session.close();
        }
        return null;
    }

    public Book getById(Integer id) {
        Session session = sessionFactory.openSession();
        Book book = null;
        try {
            transaction = session.beginTransaction();
            book = (Book) session.createNativeQuery("select * from book where bookId = '" + id + "'");
            transaction.commit();
            return book;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Book get all error:" + ex);
        } finally {
            session.close();
        }
        return null;
    }

}
