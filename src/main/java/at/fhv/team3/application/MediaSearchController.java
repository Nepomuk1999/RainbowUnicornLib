package at.fhv.team3.application;

import at.fhv.team3.domain.Searchable;

import java.util.ArrayList;

/**
 * Created by David on 10/31/2017.
 */
public class MediaSearchController {


    public ArrayList<Searchable> search(String searchTerm){
        //TODO: new ArrayList mit persistence Aufruf ersetzen
        ArrayList<Searchable> books = new ArrayList<Searchable>();
        ArrayList<Searchable> dvds = new ArrayList<Searchable>();
        ArrayList<Searchable> magazines = new ArrayList<Searchable>();


        ArrayList<Searchable> allMedias = new ArrayList<Searchable>();

        allMedias.addAll(books);
        allMedias.addAll(dvds);
        allMedias.addAll(magazines);

        ArrayList<Searchable> searchResult = new ArrayList<Searchable>();
        for(Searchable s : allMedias){
            if(s.containsSearchTerm(searchTerm)){
                searchResult.add(s);
            }
        }
        return searchResult;
    }
}
