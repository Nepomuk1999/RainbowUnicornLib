package at.fhv.team3.application;

import at.fhv.team3.domain.Book;
import at.fhv.team3.domain.Dvd;
import at.fhv.team3.domain.Magazine;
import at.fhv.team3.domain.MediaType;
import at.fhv.team3.domain.dto.BookDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.interfaces.Searchable;
import at.fhv.team3.persistence.BookRepository;
import at.fhv.team3.persistence.DvdRepository;
import at.fhv.team3.persistence.MagazineRepository;
import at.fhv.team3.rmi.interfaces.RMIMediaSearch;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by David on 10/31/2017.
 */
public class MediaSearchController  extends UnicastRemoteObject implements RMIMediaSearch {

    private BookRepository bookRepository;
    private MagazineRepository magazineRepository;
    private DvdRepository dvdRepository;

    public MediaSearchController() throws RemoteException {
        bookRepository = BookRepository.getInstance();
        magazineRepository = MagazineRepository.getInstance();
        dvdRepository = DvdRepository.getInstance();
    }

    public List<DTO> getAllBookDTOs(){
        List<Book> all = bookRepository.getAll();
        List<DTO> dtos = new ArrayList<DTO>();
        for(Book b : all){
            dtos.add(b.createDataTransferObject());
        }
        return dtos;
    }

    public List<DTO> getAllDvdDTOs(){
        List<Dvd> all = dvdRepository.getAll();
        List<DTO> dtos = new ArrayList<DTO>();
        for(Dvd d : all){
            dtos.add(d.createDataTransferObject());
        }
        return dtos;
    }

    public List<DTO> getAllMagazineDTOs(){
        List<Magazine> all = magazineRepository.getAll();
        List<DTO> dtos = new ArrayList<DTO>();
        for(Magazine m : all){
            dtos.add(m.createDataTransferObject());
        }
        return dtos;
    }

    public ArrayList<ArrayList<Searchable>> searchMedias(String searchTerm){
        List<Book> books = bookRepository.getAll();
        List<Dvd> dvds = dvdRepository.getAll();
        List<Magazine> magazines = magazineRepository.getAll();

        ArrayList<ArrayList<Searchable>> allMedias = new ArrayList<ArrayList<Searchable>>();

        ArrayList<Searchable> bs = new ArrayList<Searchable>();

        ArrayList<Searchable> ds = new ArrayList<Searchable>();

        ArrayList<Searchable> ms = new ArrayList<Searchable>();

        for(Book b : books){
            if(b.containsSearchTerm(searchTerm)){
                bs.add(b);
            }
        }

        for(Dvd d : dvds){
            if(d.containsSearchTerm(searchTerm)){
                ds.add(d);
            }
        }

        for(Magazine m : magazines){
            if(m.containsSearchTerm(searchTerm)){
                ms.add(m);
            }
        }
        allMedias.add(bs);
        allMedias.add(ds);
        allMedias.add(ms);

        return allMedias;
    }

    public ArrayList<ArrayList<DTO>> search(String searchTerm){
        HashMap<MediaType, ArrayList<DTO>> map = new HashMap<MediaType, ArrayList<DTO>>();
        ArrayList<ArrayList<Searchable>> searchResult = searchMedias(searchTerm);
        ArrayList<ArrayList<DTO>> dtos = new ArrayList<ArrayList<DTO>>();
        for(ArrayList<Searchable> al : searchResult) {
            ArrayList<DTO> list = new ArrayList<DTO>();
            for (Searchable s : al) {
                list.add(s.createDataTransferObject());
            }
            dtos.add(list);
        }

        return dtos;
    }

}
