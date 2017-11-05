package at.fhv.team3.persistence;

import at.fhv.team3.domain.Magazine;
import org.hibernate.HibernateException;
import org.hibernate.Session;

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

    public List<Magazine> getAll() {
        Session session = sessionFactory.openSession();
        List<Magazine> magazines = new LinkedList<Magazine>();
        try {
            transaction = session.beginTransaction();
            magazines = session.createNativeQuery("select * from magazine").getResultList();
            transaction.commit();
            return magazines;
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

    public Magazine getById(Integer id) {
        Session session = sessionFactory.openSession();
        Magazine magazine = null;
        try {
            transaction = session.beginTransaction();
            magazine = (Magazine) session.createNativeQuery
                    ("select * from magazine where magazineId = '" + id + "'");
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
