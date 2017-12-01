package at.fhv.team3.applicationbean;

import at.fhv.team3.application.MediaSearchController;
import at.fhv.team3.applicationbean.interfaces.RemoteSearchBeanFace;
import at.fhv.team3.domain.Book;
import at.fhv.team3.domain.BorrowedItem;
import at.fhv.team3.domain.Dvd;
import at.fhv.team3.domain.Magazine;
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

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class MediaSearchControllerBean implements RemoteSearchBeanFace {

    private MediaSearchController mediaSearchController = MediaSearchController.getInstance();


    public List<DTO> getAllBookDTOs() {
        return mediaSearchController.getAllBookDTOs();
    }

    public List<DTO> getAllDvdDTOs() {
        return mediaSearchController.getAllDvdDTOs();
    }

    public List<DTO> getAllMagazineDTOs() {
        return mediaSearchController.getAllMagazineDTOs();
    }

    public ArrayList<ArrayList<Searchable>> searchMedias(String searchTerm) {
        return mediaSearchController.searchMedias(searchTerm);
    }

    public ArrayList<ArrayList<DTO>> search(String searchTerm) {
        return mediaSearchController.search(searchTerm);
    }

    public ArrayList<BookDTO> getBooksByISBN(String isbn) {
        return mediaSearchController.getBooksByISBN(isbn);
    }

    public ArrayList<DvdDTO> getDvdByTitle(String title) {
        return mediaSearchController.getDvdByTitle(title);
    }

    public ArrayList<MagazineDTO> getMagazinesByTitleAndEdition(String title, String edition) {
        return mediaSearchController.getMagazinesByTitleAndEdition(title, edition);
    }
}
