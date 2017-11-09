package at.fhv.team3.persistence;

import at.fhv.team3.domain.Dvd;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DvdRepository extends Repository<Dvd> {
    private static DvdRepository ourInstance;

    public static DvdRepository getInstance() {
        if(ourInstance == null){
            ourInstance = new DvdRepository();
        }
        return ourInstance;
    }

    private DvdRepository() {
    }

    public void save(List<Dvd> dvds) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        for (Dvd dvd: dvds) {
            session.saveOrUpdate(dvd);
        }
        transaction.commit();
    }

    public void save(Dvd dvd) {
        List<Dvd> dvds = new ArrayList<Dvd>(1);
        dvds.add(dvd);
        save(dvds);
    }

    public List<Dvd> getAll() {
        Session session = sessionFactory.openSession();
        List<Dvd> dvd = new LinkedList<Dvd>();
        try {
            transaction = session.beginTransaction();
            dvd = session.createNativeQuery("SELECT * FROM dvd", Dvd.class).list();
            transaction.commit();
            return dvd;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Dvd get all error:" + ex);
        }
        return null;
    }

    public Dvd getById(Integer id) {
        Session session = sessionFactory.openSession();
        Dvd dvd = null;
        try {
            transaction = session.beginTransaction();
            dvd = (Dvd) session.createNativeQuery("select * from dvd where dvdId = '" + id + "'", Dvd.class);
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
