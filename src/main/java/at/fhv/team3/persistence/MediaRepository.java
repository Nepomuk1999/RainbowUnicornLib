package at.fhv.team3.persistence;

import at.fhv.team3.domain.interfaces.Borrowable;

import java.util.List;

public class MediaRepository extends Repository {
    private static MediaRepository ourInstance = new MediaRepository();

    public static MediaRepository getInstance() {
        return ourInstance;
    }

    private MediaRepository() {
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
