package at.fhv.team3.persistence;

import at.fhv.team3.domain.Book;
import at.fhv.team3.domain.Magazine;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MagazineRepository extends Repository<Magazine> {
    private static MagazineRepository ourInstance;

    public static MagazineRepository getInstance() {
        if(ourInstance == null){
            ourInstance = new MagazineRepository();
        }
        return ourInstance;
    }

    private MagazineRepository() { }

    public void save(List<Magazine> magazines) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        for (Magazine magazine: magazines) {
            session.saveOrUpdate(magazine);
        }
        transaction.commit();
    }

    public void save(Magazine magazine) {
        List<Magazine> customers = new ArrayList<Magazine>(1);
        customers.add(magazine);
        save(customers);
    }
    public List<Magazine> getAll() {
        Session session = sessionFactory.openSession();
        List<Magazine> magazines = new LinkedList<Magazine>();
        try {
            transaction = session.beginTransaction();
            magazines = session.createNativeQuery("SELECT * FROM magazine", Magazine.class).list();
            transaction.commit();
            return magazines;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Magazin get all error:" + ex);
        }
        return null;
    }

    public Magazine getById(Integer id) {
        Session session = sessionFactory.openSession();
        Magazine magazine = null;
        try {
            transaction = session.beginTransaction();
            magazine = (Magazine) session.createNativeQuery
                    ("select * from magazine where magazineId = '" + id + "'", Magazine.class);
            transaction.commit();
            return magazine;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Magazin get all error:" + ex);
        } finally {
            session.close();
        }
        return null;
    }

}
