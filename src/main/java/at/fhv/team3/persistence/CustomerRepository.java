package at.fhv.team3.persistence;

import at.fhv.team3.domain.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by David on 11/8/2017.
 */
public class CustomerRepository extends Repository<Customer> {

    private static CustomerRepository _instance;

    public static CustomerRepository getInstance(){
        if(_instance == null){
            _instance = new CustomerRepository();
        }
        return _instance;
    }
    public List<Customer> getAll() {
        Session session = sessionFactory.openSession();
        List customers;
        try {
            transaction = session.beginTransaction();
            customers = session.createNativeQuery("SELECT * FROM customer", Customer.class).list();
            transaction.commit();
            return customers;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Customer get all error:" + ex);
        }
        return null;
    }

    public Customer getById(Integer id) {
        Session session = sessionFactory.openSession();
        Customer customer = null;
        try {
            transaction = session.beginTransaction();
            customer = (Customer) session.createNativeQuery
                    ("select * from customer where customerId = '" + id + "'", Customer.class);
            transaction.commit();
            return customer;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Customer get by Id error:" + ex);
        } finally {
            session.close();
        }
        return null;
    }
}
