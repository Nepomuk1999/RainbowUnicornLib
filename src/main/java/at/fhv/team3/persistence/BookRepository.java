package at.fhv.team3.persistence;

import at.fhv.team3.domain.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


public class BookRepository extends Repository<Book> {
    private static BookRepository ourInstance;

    public static BookRepository getInstance() {
        if(ourInstance == null){
            ourInstance = new BookRepository();
        }
        return ourInstance;
    }

    private BookRepository() { }

    public void save(List<Book> books) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        for (Book book: books) {
            session.saveOrUpdate(book);
        }
        transaction.commit();
    }

    public void save(Book book) {
        List<Book> books = new ArrayList<Book>(1);
        books.add(book);
        save(books);
    }

    public List<Book> getAll() {
        Session session = sessionFactory.openSession();
        List books;
        try {
            transaction = session.beginTransaction();
            books = session.createNativeQuery("SELECT * FROM book", Book.class).list();
            transaction.commit();
            return books;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Book get all error:" + ex);
        }
        return null;
    }

    public Book getById(Integer id) {
        Session session = sessionFactory.openSession();
        Book book = null;
        try {
            transaction = session.beginTransaction();
            book = (Book) session.createNativeQuery
                    ("select * from book where bookId = '" + id + "'", Book.class);
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

    public void delete(Book model) {

    }

    /*
    public List<Book> getByIsbn(Integer id) {
        Session session = sessionFactory.openSession();
        Book book = null;
        try {
            transaction = session.beginTransaction();
            book = (Book) session.createNativeQuery
                    ("select * from book where bookId = '" + id + "'", Book.class);
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
    */

}
