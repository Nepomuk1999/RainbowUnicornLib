package at.fhv.team3.persistence;

import at.fhv.team3.domain.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.LinkedList;
import java.util.List;

public class BookRepository extends Repository<Book> {
    private static BookRepository ourInstance = new BookRepository();

    public static BookRepository getInstance() {
        return ourInstance;
    }

    private BookRepository() {
    }

    public List<Book> getAll() {
        Session session = sessionFactory.openSession();
        List<Book> books = new LinkedList<Book>();
        try {
            transaction = session.beginTransaction();
            books = session.createQuery("from Book").list();
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
            book = (Book) session.createQuery("from Book b where Book.id = id");
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


    protected Integer add(Book media) {
        Session session = sessionFactory.openSession();
        Integer bookId = null;

        try {
            transaction = session.beginTransaction();
            bookId = (Integer) session.save(media);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return bookId;
    }

}
