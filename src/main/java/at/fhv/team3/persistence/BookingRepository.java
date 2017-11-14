package at.fhv.team3.persistence;

import at.fhv.team3.domain.BookedItem;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 11/14/2017.
 */
public class BookingRepository extends Repository<BookedItem> {


    private static BookingRepository _instance;

    public static BookingRepository getInstance(){
        if(_instance == null){
            _instance = new BookingRepository();
        }
        return _instance;
    }
    public void save(List<BookedItem> bookedItems) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        for (BookedItem bookedItem: bookedItems) {
            session.saveOrUpdate(bookedItem);
        }
        transaction.commit();
    }

    public void save(BookedItem bookedItem) {
        List<BookedItem> bookedItems = new ArrayList<BookedItem>(1);
        bookedItems.add(bookedItem);
        save(bookedItems);
    }

    public List<BookedItem> getAll() {
        Session session = sessionFactory.openSession();
        List bookedItems;
        try {
            transaction = session.beginTransaction();
            bookedItems = session.createNativeQuery("SELECT * FROM bookingItem", BookedItem.class).list();
            transaction.commit();
            return bookedItems;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("BookedItem get all error:" + ex);
        }
        return null;
    }

    public BookedItem getById(Integer id) {
        Session session = sessionFactory.openSession();
        BookedItem bookedItem = null;
        try {
            transaction = session.beginTransaction();
            bookedItem = (BookedItem) session.createNativeQuery
                    ("select * from borrowedItem where bookedId = '" + id + "'", BookedItem.class);
            transaction.commit();
            return bookedItem;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("BorrowedItem get all error:" + ex);
        } finally {
            session.close();
        }
        return null;
    }

    public void delete(BookedItem item) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(item);
        transaction.commit();
    }
}
