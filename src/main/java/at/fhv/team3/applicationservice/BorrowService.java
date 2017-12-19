package at.fhv.team3.applicationservice;


import at.fhv.team3.application.BorrowController;
import at.fhv.team3.application.LdapController;
import at.fhv.team3.application.MediaSearchController;
import at.fhv.team3.domain.dto.*;
import at.fhv.team3.domain.interfaces.Borrowable;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

@WebService(name = "BorrowService")
public class BorrowService {

    @WebMethod
    public String handOut(int id, String type, String externalLib) {
        List<DTO> medias = new ArrayList<DTO>();
        DTO media = null;
        if(type.equals("1")){
            medias = MediaSearchController.getInstance().getAllBookDTOs();
        } else if(type.equals("2")){
            medias = MediaSearchController.getInstance().getAllDvdDTOs();
        } else {
            medias = MediaSearchController.getInstance().getAllMagazineDTOs();
        }
        if(medias != null) {
            media = getDTO(id, medias);
        } else {
            return "Media not found!";
        }
        ValidationResult vr = BorrowController.getInstance().handOut(media, new ExternalLibDTO(1, "Testlib", "11223344"));
        if (vr.hasErrors()) {
            return "Something went wrong during the validation!";
        }
        return "Success";
    }

    @WebMethod
    public String handIn(int id, String type) {
        List<DTO> medias = new ArrayList<DTO>();
        DTO media = null;
        if(type.equals("1")){
            medias = MediaSearchController.getInstance().getAllBookDTOs();
        } else if(type.equals("2")){
            medias = MediaSearchController.getInstance().getAllDvdDTOs();
        } else {
            medias = MediaSearchController.getInstance().getAllMagazineDTOs();
        }
        if(medias != null) {
            media = getDTO(id, medias);
        } else {
            return "Media not found!";
        }
        ValidationResult vr = BorrowController.getInstance().handIn(media);
        if (vr.hasErrors()) {
            return "Something went wrong during the validation!";
        }
        return "Success";
    }

    @WebMethod
    public String login(String username, String password){
        try {
            EmployeeDTO employee = LdapController.getInstance().authenticateUser(username, password);
            if(employee != null){
                if(employee.isLoggedIn()){
                    return "success";
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return "failure";
    }

    private DTO getDTO(int id, List<DTO> dtos){
        for(DTO dto : dtos){
            if(dto.getId() == id){
                return dto;
            }
        }
        return null;
    }

}
