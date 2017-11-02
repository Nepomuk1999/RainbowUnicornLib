package at.fhv.team3.persistence;

import at.fhv.team3.domain.interfaces.Borrowable;

import java.util.List;

public class MagazinRepository extends Repository {
    private static MagazinRepository ourInstance = new MagazinRepository();

    public static MagazinRepository getInstance() {
        return ourInstance;
    }

    private MagazinRepository() {
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
