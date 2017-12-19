package at.fhv.team3.persistence;

import at.fhv.team3.domain.interfaces.Transferable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public abstract class Repository<T extends Transferable> implements Serializable {

    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    protected Transaction transaction = null;

    public Repository() { }

    //Liste von allen Einträgen einer Tablle zurückgeben (Liste von Domänenobjekten)
    public abstract List<T> getAll();

    //Ein Domänenobjekt per ID (Primary Key) aus der Datenbank laden (Domänenobjekt)
    public abstract T getById(Integer id);

    //Einen Eintrag aus einer Tabelle in der Datenbank löschen
    public abstract void delete(T model);

    //Einen Eintrag in die Datenbank einfügen
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
