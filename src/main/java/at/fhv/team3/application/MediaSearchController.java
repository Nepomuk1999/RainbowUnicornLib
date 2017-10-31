package at.fhv.team3.application;

import at.fhv.team3.domain.interfaces.Searchable;
import at.fhv.team3.domain.dto.DTO;

import java.util.ArrayList;

/**
 * Created by David on 10/31/2017.
 */
public class MediaSearchController {


    public ArrayList<Searchable> searchMedias(String searchTerm){
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

    public ArrayList<DTO> search(String searchTerm){
        ArrayList<Searchable> searchResult = searchMedias(searchTerm);
        ArrayList<DTO> dtos = new ArrayList<DTO>();
        for(Searchable s : searchResult){
            //TODO: Implementierung von createDataTransferObject in allen Domänenobjekten.
            dtos.add(s.createDataTransferObject());
        }
        return dtos;
    }
}
