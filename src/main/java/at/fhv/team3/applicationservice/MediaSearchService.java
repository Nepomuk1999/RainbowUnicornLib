package at.fhv.team3.applicationservice;

import at.fhv.team3.application.MediaSearchController;
import at.fhv.team3.domain.dto.DTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

/**
 * Created by David on 12/12/2017.
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class MediaSearchService {

    @WebMethod
    public ArrayList<ArrayList<DTO>> search(String searchTerm){
        return MediaSearchController.getInstance().search(searchTerm);
    }
}
