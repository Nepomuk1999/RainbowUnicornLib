package at.fhv.team3.persistence;

import at.fhv.team3.domain.BorrowedItem;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by David on 11/8/2017.
 */
public class BorrowedItemRepository extends Repository<BorrowedItem>{

    private static BorrowedItemRepository _instance;

    public static BorrowedItemRepository getInstance(){
        if(_instance == null){
            _instance = new BorrowedItemRepository();
        }
        return _instance;
    }

    public List<BorrowedItem> getAll() {
        Session session = sessionFactory.openSession();
        List borrowedItems;
        try {
            transaction = session.beginTransaction();
            borrowedItems = session.createNativeQuery("SELECT * FROM borrowedItem", BorrowedItem.class).list();
            transaction.commit();
            return borrowedItems;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("BorrowedItem get all error:" + ex);
        }
        return null;
    }

    public BorrowedItem getById(Integer id) {
        Session session = sessionFactory.openSession();
        BorrowedItem borrowedItem = null;
        try {
            transaction = session.beginTransaction();
            borrowedItem = (BorrowedItem) session.createNativeQuery
                    ("select * from borrowedItem where borrowedId = '" + id + "'", BorrowedItem.class);
            transaction.commit();
            return borrowedItem;
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
}
