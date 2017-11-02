package at.fhv.team3.persistence;

import at.fhv.team3.domain.Book;
import at.fhv.team3.domain.Dvd;
import at.fhv.team3.domain.Media;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.LinkedList;
import java.util.List;

public class DvdRepository extends Repository<Dvd> {
    private static DvdRepository ourInstance = new DvdRepository();

    public static DvdRepository getInstance() {
        return ourInstance;
    }

    private DvdRepository() {
    }

    public List getAll() {
        Session session = sessionFactory.openSession();
        List<Dvd> dvd = new LinkedList<Dvd>();
        try {
            transaction = session.beginTransaction();
            dvd = session.createQuery("from Dvd").list();
            transaction.commit();
            return dvd;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Dvd get all error:" + ex);
        } finally {
            session.close();
        }
        return null;
    }

    public Dvd getById(Integer id) {
        Session session = sessionFactory.openSession();
        Dvd dvd = null;
        try {
            transaction = session.beginTransaction();
            dvd = (Dvd) session.createQuery("from Dvd d where d.id = id");
            transaction.commit();
            return dvd;
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
