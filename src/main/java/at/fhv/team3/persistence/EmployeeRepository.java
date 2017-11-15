package at.fhv.team3.persistence;

import at.fhv.team3.domain.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by David on 11/16/2017.
 */
public class EmployeeRepository extends Repository<Employee> {

    private static EmployeeRepository _instance;

    public static EmployeeRepository getInstance(){
        if(_instance == null){
            _instance = new EmployeeRepository();
        }
        return _instance;
    }

    public List<Employee> getAll() {
        Session session = sessionFactory.openSession();
        List employees;
        try {
            transaction = session.beginTransaction();
            employees = session.createNativeQuery("SELECT * FROM employee", Employee.class).list();
            transaction.commit();
            return employees;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Employee get all error:" + ex);
        }
        return null;
    }

    public Employee getById(Integer id) {
        return null;
    }

    public void delete(Employee model) {

    }
}
