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
    private static MediaSearchController currentController;

    public MediaSearchController() throws RemoteException {
        bookRepository = BookRepository.getInstance();
        magazineRepository = MagazineRepository.getInstance();
        dvdRepository = DvdRepository.getInstance();
        _borrowedItemRepository = BorrowedItemRepository.getInstance();
        currentController = this;
    }

    public static MediaSearchController getInstance() {
        if (currentController == null) {
            try {
                return new MediaSearchController();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return currentController;
    }

    //Alle Bücher aus der Datenbank laden (Liste von BookDTOs)
    public List<DTO> getAllBookDTOs(){
        List<Book> all = bookRepository.getAll();
        List<DTO> dtos = new ArrayList<DTO>();
        for(Book b : all){
            dtos.add(b.createDataTransferObject());
        }
        return dtos;
    }

    //Alle Dvds aus der Datenbank laden (Liste von DvdDTOs)
    public List<DTO> getAllDvdDTOs(){
        List<Dvd> all = dvdRepository.getAll();
        List<DTO> dtos = new ArrayList<DTO>();
        for(Dvd d : all){
            dtos.add(d.createDataTransferObject());
        }
        return dtos;
    }

    //Alle Magazine aus der Datenbank laden (Liste von MagazinDTOs)
    public List<DTO> getAllMagazineDTOs(){
        List<Magazine> all = magazineRepository.getAll();
        List<DTO> dtos = new ArrayList<DTO>();
        for(Magazine m : all){
            dtos.add(m.createDataTransferObject());
        }
        return dtos;
    }

    //Alle Medien aus der Datenbank mit den Suchstring vergleichen und matches nach Medientyp sortiert zurückgeben (ArrayList von ArrayLists mit Durchsuchbaren(Searchable) Objekten)
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

    //Suchmethode
    public ArrayList<ArrayList<DTO>> search(String searchTerm){
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

    //Bücher nach der ISBN finden(ArrayList von BookDTOs)
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

    //Dvds nach Titel finden (ArrayList von DvdDTOs)
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

    //Magazine nach Komination aus Titel und Edition suchen (ArrayList von MagazinDTOs)
    public ArrayList<MagazineDTO> getMagazinesByTitleAndEdition(String title, String edition){
        List<Magazine> allMagazines = magazineRepository.getAll();
        ArrayList<MagazineDTO> matchingMagazines = new ArrayList<MagazineDTO>();
        System.out.println("Looking for title: " + title + " Edition: " + edition);
        for(Magazine m : allMagazines){
            System.out.println(m.toString());
            if(m.getTitle().equals(title) && m.getEdition().equals(edition)){
                System.out.println("magazine found");
                MagazineDTO dto = (MagazineDTO) m.createDataTransferObject();
                dto.setAvailable(getAvailability(m));
                matchingMagazines.add(dto);
            }
        }
        return matchingMagazines;
    }

    //Festellen ob ein Medium verfügbar oder nicht verfügbar ist (boolean)
    private boolean getAvailability(Borrowable b){
        List<BorrowedItem> borrowedItems = _borrowedItemRepository.getAll();
        for(BorrowedItem item : borrowedItems){
            if(b.getClass() == Book.class){
                if(item.getBook() != null){
                    if(b.getId() == item.getBook().getId()){
                        return false;
                    }
                }
            } else if(b.getClass() == Dvd.class){
                if(item.getDvd() != null){
                    if(b.getId() == item.getDvd().getId()){
                        return false;
                    }
                }
            } else {
                if(item.getMagazine() != null){
                    if(b.getId() == item.getMagazine().getId()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
