package at.fhv.team3.domain;

/**
 * Created by David on 10/31/2017.
 */
public interface Searchable extends Transferable{

    public boolean containsSearchTerm(String searchTerm);
}
