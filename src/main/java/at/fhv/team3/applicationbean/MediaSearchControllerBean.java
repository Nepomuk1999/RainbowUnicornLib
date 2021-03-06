package at.fhv.team3.applicationbean;

import at.fhv.team3.application.MediaSearchController;
import at.fhv.team3.applicationbean.interfaces.RemoteSearchBeanFace;
import at.fhv.team3.domain.dto.BookDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.DvdDTO;
import at.fhv.team3.domain.dto.MagazineDTO;
import at.fhv.team3.domain.interfaces.Searchable;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

@Stateless(mappedName = "SearchEJB")
public class MediaSearchControllerBean implements RemoteSearchBeanFace {

    private MediaSearchController _mediaSearchController;

    public MediaSearchControllerBean(){
        try {
            _mediaSearchController = new MediaSearchController();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DTO> getAllBookDTOs() {
        return _mediaSearchController.getAllBookDTOs();
    }

    @Override
    public List<DTO> getAllDvdDTOs() {
        return _mediaSearchController.getAllDvdDTOs();
    }

    @Override
    public List<DTO> getAllMagazineDTOs() {
        return _mediaSearchController.getAllMagazineDTOs();
    }

    @Override
    public ArrayList<ArrayList<Searchable>> searchMedias(String searchTerm) {
        return _mediaSearchController.searchMedias(searchTerm);
    }

    @Override
    public ArrayList<ArrayList<DTO>> search(String searchTerm) {
        return _mediaSearchController.search(searchTerm);
    }

    @Override
    public ArrayList<BookDTO> getBooksByISBN(String isbn) {
        return _mediaSearchController.getBooksByISBN(isbn);
    }

    @Override
    public ArrayList<DvdDTO> getDvdByTitle(String title) {
        return _mediaSearchController.getDvdByTitle(title);
    }

    @Override
    public ArrayList<MagazineDTO> getMagazinesByTitleAndEdition(String title, String edition) {
        return _mediaSearchController.getMagazinesByTitleAndEdition(title, edition);
    }

}
