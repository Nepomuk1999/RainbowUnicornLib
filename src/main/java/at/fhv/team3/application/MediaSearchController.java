package at.fhv.team3.application;

import at.fhv.team3.domain.*;
import at.fhv.team3.domain.dto.BookDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.DvdDTO;
import at.fhv.team3.domain.dto.MagazineDTO;
import at.fhv.team3.domain.interfaces.Borrowable;
import at.fhv.team3.domain.interfaces.Searchable;
import at.fhv.team3.persistence.BookRepository;
import at.fhv.team3.persistence.BorrowedItemRepository;
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
    private BorrowedItemRepository _borrowedItemRepository;

    public MediaSearchController() throws RemoteException {
        bookRepository = BookRepository.getInstance();
        magazineRepository = MagazineRepository.getInstance();
        dvdRepository = DvdRepository.getInstance();
        _borrowedItemRepository = BorrowedItemRepository.getInstance();
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

    //TODO: Verify
    public ArrayList<BookDTO> getBooksByISBN(String isbn){
        List<Book> allBooks = bookRepository.getAll();
        ArrayList<BookDTO> matchingBooks = new ArrayList<BookDTO>();
        for(Book b : allBooks){
            if(b.getIsbn().equals(isbn)){
                BookDTO dto = (BookDTO) b.createDataTransferObject();
                dto.setAvailable(getAvailability(b));
                matchingBooks.add(dto);
            }
        }
        return matchingBooks;
    }

    public ArrayList<DvdDTO> getDvdByTitle(String title){
        List<Dvd> allDvds = dvdRepository.getAll();
        ArrayList<DvdDTO> matchingDvds = new ArrayList<DvdDTO>();
        for(Dvd d : allDvds){
            if(d.getTitle().equals(title)){
                DvdDTO dto = (DvdDTO) d.createDataTransferObject();
                dto.setAvailable(getAvailability(d));
                matchingDvds.add(dto);
            }
        }
        return matchingDvds;
    }

    public ArrayList<MagazineDTO> getMagazinesByTitleAndEdition(String title, String edition){
        List<Magazine> allMagazines = magazineRepository.getAll();
        ArrayList<MagazineDTO> matchingMagazines = new ArrayList<MagazineDTO>();
        for(Magazine m : allMagazines){
            if(m.getTitle().equals(title) && m.getEdition().equals(edition)){
                MagazineDTO dto = (MagazineDTO) m.createDataTransferObject();
                dto.setAvailable(getAvailability(m));
            }
        }
        return matchingMagazines;
    }

    private boolean getAvailability(Borrowable b){
        List<BorrowedItem> borrowedItems = _borrowedItemRepository.getAll();
        for(BorrowedItem item : borrowedItems){
            if(b.getClass() == Book.class){
                if(item.getBook() != null){
                    if(b.getId() == item.getBook().getId()){
                        return true;
                    }
                }
            } else if(b.getClass() == Dvd.class){
                if(item.getDvd() != null){
                    if(b.getId() == item.getDvd().getId()){
                        return true;
                    }
                }
            } else {
                if(item.getMagazine() != null){
                    if(b.getId() == item.getMagazine().getId()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
