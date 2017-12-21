package at.fhv.team3.applicationservice;

import at.fhv.team3.application.MediaSearchController;
import at.fhv.team3.domain.dto.BookDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.DvdDTO;

import javax.json.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.*;

/**
 * Created by David on 12/12/2017.
 */

@WebService(name = "MediaSearch")
public class MediaSearchService {

    public String search(String searchTerm){
        ArrayList<ArrayList<DTO>> all = MediaSearchController.getInstance().search(searchTerm);

        StringBuilder sb = new StringBuilder();

        for(ArrayList<DTO> list : all){
            for(DTO dto : list){
                sb.append("|");

                if(dto.getClass() == BookDTO.class){
                    sb.append("Book: ");
                } else if(dto.getClass() == DvdDTO.class){
                    sb.append("Dvd: ");
                } else {
                    sb.append("Magazine: ");
                }
                sb.append(dto.toString());

            }
        }
        return sb.toString();
    }


}
