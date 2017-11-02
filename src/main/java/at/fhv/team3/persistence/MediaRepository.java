package at.fhv.team3.persistence;

import at.fhv.team3.domain.Media;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.LinkedList;
import java.util.List;

public class MediaRepository extends Repository<Media> {
    private static MediaRepository ourInstance = new MediaRepository();

    public static MediaRepository getInstance() {
        return ourInstance;
    }

    private MediaRepository() {
    }

    public List<Media> getAll() {
        Session session = sessionFactory.openSession();
        List<Media> media = new LinkedList<Media>();
        try {
            transaction = session.beginTransaction();
            media = session.createQuery("from Media").list();
            transaction.commit();
            return media;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Media get all error:" + ex);
        } finally {
            session.close();
        }
        return null;
    }

    public Media getById(Integer id) {
        Session session = sessionFactory.openSession();
        Media media = null;
        try {
            transaction = session.beginTransaction();
            media = (Media) session.createQuery("from Media m where m.id = id");
            transaction.commit();
            return media;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Media get all error:" + ex);
        } finally {
            session.close();
        }
        return null;
    }

}
