package at.fhv.team3.persistence;

import at.fhv.team3.domain.interfaces.Borrowable;

import java.util.List;

public class DvdRepository extends Repository {
    private static DvdRepository ourInstance = new DvdRepository();

    public static DvdRepository getInstance() {
        return ourInstance;
    }

    private DvdRepository() {
    }

    public List getAll() {
        return null;
    }

    public Borrowable getById(Integer id) {
        return null;
    }

    protected Integer add(Borrowable media) {
        return null;
    }
}
