package at.fhv.team3.persistence;

import at.fhv.team3.domain.Book;
import at.fhv.team3.domain.Media;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class MediaRepository extends Repository<Media> {
    private static MediaRepository ourInstance;

    public static MediaRepository getInstance() {
        if(ourInstance == null){
            ourInstance = new MediaRepository();
        }
        return ourInstance;
    }

    private MediaRepository() {
    }

    public List<Media> getAll() {
        Session session = sessionFactory.openSession();
        List<Media> media = new LinkedList<Media>();
        try {
            transaction = session.beginTransaction();
            media = session.createNativeQuery("select * from media").list();
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
            media = (Media) session.createNativeQuery("select * from media where mediaId = '" + id + "'");
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
