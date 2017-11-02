package at.fhv.team3.persistence;

import at.fhv.team3.domain.Book;
import at.fhv.team3.domain.interfaces.Borrowable;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public abstract class Repository<T extends Borrowable> {

    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    protected Transaction transaction = null;

    public Repository() {
    }

    public abstract List<T> getAll();

    public abstract Borrowable getById(Integer id);

    protected abstract Integer add(T media);


}
