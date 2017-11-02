package at.fhv.team3.application;

import at.fhv.team3.domain.*;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.interfaces.Searchable;
import at.fhv.team3.rmi.interfaces.RMIMediaSearch;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by David on 10/31/2017.
 */
@Service
public class MediaSearchController  extends UnicastRemoteObject implements RMIMediaSearch {


    public MediaSearchController() throws RemoteException {
    }

    private ArrayList<Searchable> searchMedias(String searchTerm){
        //TODO: new ArrayList mit persistence Aufruf ersetzen
        ArrayList<Book> books = new ArrayList<Book>();
        ArrayList<Dvd> dvds = new ArrayList<Dvd>();
        ArrayList<Magazine> magazines = new ArrayList<Magazine>();

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

    //TODO: refactor to hashMap<Enum:MediaType, LinkedList<Media>
    public ArrayList<DTO> search(String searchTerm){
        HashMap<MediaType, ArrayList<DTO>> map = new HashMap<MediaType, ArrayList<DTO>>();
        ArrayList<Searchable> searchResult = searchMedias(searchTerm);
        ArrayList<DTO> dtos = new ArrayList<DTO>();
        for(Searchable s : searchResult){
            //TODO: Implementierung von createDataTransferObject in allen Domänenobjekten.
            dtos.add(s.createDataTransferObject());
        }

        return dtos;
    }

}
