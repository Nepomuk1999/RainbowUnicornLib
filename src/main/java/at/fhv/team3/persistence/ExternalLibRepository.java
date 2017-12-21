package at.fhv.team3.persistence;

import at.fhv.team3.domain.ExternalLib;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ExternalLibRepository extends Repository<ExternalLib> {

    private static ExternalLibRepository _instance;

    public static ExternalLibRepository getInstance() {
        if (_instance == null) {
            _instance = new ExternalLibRepository();
        }
        return _instance;
    }

    public void save(List<ExternalLib> externalLibs) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        for (ExternalLib externalLib : externalLibs) {
            session.saveOrUpdate(externalLib);
        }
        transaction.commit();
    }

    public void save(ExternalLib externalLib) {
        List<ExternalLib> customers = new ArrayList<ExternalLib>(1);
        customers.add(externalLib);
        save(customers);
    }

    public List<ExternalLib> getAll() {
        Session session = sessionFactory.openSession();
        List externalLibs;
        try {
            transaction = session.beginTransaction();
            externalLibs = session.createNativeQuery("SELECT * FROM externalLib", ExternalLib.class).list();
            transaction.commit();
            return externalLibs;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("ExternalLib get all error:" + ex);
        }
        return null;
    }

    public ExternalLib getById(Integer id) {
        Session session = sessionFactory.openSession();
        ExternalLib externalLib = null;
        try {
            transaction = session.beginTransaction();
            externalLib = (ExternalLib) session.createNativeQuery
                    ("select * from externalLib where libId = '" + id + "'", ExternalLib.class);
            transaction.commit();
            return externalLib;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("ExternalLib get by Id error:" + ex);
        } finally {
            session.close();
        }
        return null;
    }

    public void delete(ExternalLib model) {

    }
}
