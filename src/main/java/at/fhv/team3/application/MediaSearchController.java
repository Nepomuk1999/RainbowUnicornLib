package at.fhv.team3.application;

import at.fhv.team3.domain.*;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.interfaces.Searchable;
import at.fhv.team3.persistence.BookRepository;
import at.fhv.team3.persistence.DvdRepository;
import at.fhv.team3.persistence.MagazinRepository;
import at.fhv.team3.rmi.interfaces.RMIMediaSearch;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by David on 10/31/2017.
 */
public class MediaSearchController  extends UnicastRemoteObject implements RMIMediaSearch {

    private BookRepository bookRepository;
    private MagazinRepository magazinRepository;
    private DvdRepository dvdRepository;

    public MediaSearchController() throws RemoteException {
        bookRepository = BookRepository.getInstance();
        magazinRepository = MagazinRepository.getInstance();
        dvdRepository = DvdRepository.getInstance();
    }

    private ArrayList<Searchable> searchMedias(String searchTerm){
        List<Book> books = bookRepository.getAll();
        List<Dvd> dvds = dvdRepository.getAll();
        List<Magazine> magazines = magazinRepository.getAll();

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
        HashMap<MediaType, ArrayList<DTO>> map = new HashMap<MediaType, ArrayList<DTO>>();
        ArrayList<Searchable> searchResult = searchMedias(searchTerm);
        ArrayList<DTO> dtos = new ArrayList<DTO>();
        for(Searchable s : searchResult){
            dtos.add(s.createDataTransferObject());
        }

        return dtos;
    }

}
