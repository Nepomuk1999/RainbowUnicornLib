package at.fhv.team3.persistence;

import at.fhv.team3.domain.interfaces.Transferable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public abstract class Repository<T extends Transferable> {

    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    protected Transaction transaction = null;

    public Repository() {
    }

    public abstract List<T> getAll();

    public abstract T getById(Integer id);

    protected Integer addMedia(T media) {
        Session session = sessionFactory.openSession();
        Integer id = null;

        try {
            transaction = session.beginTransaction();
            id = (Integer) session.save(media);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }


}
