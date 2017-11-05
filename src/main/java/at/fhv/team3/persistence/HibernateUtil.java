package at.fhv.team3.persistence; /**
 * Created by Christoph on 31.10.2017.
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration conf = new Configuration();
            conf.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
            return conf.configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession(){ return sessionFactory.openSession();}

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}
