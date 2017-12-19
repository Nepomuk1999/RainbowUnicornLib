package at.fhv.team3.applicationservice;


import at.fhv.team3.application.BorrowController;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.ExternalLibDTO;
import at.fhv.team3.domain.dto.ValidationResult;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "BorrowService")
public class BorrowService {

    public String handOut(DTO media, ExternalLibDTO externalLibDTO) {
        ValidationResult vr = BorrowController.getInstance().handOut(media, externalLibDTO);
        if (vr.hasErrors()) {
            return "Something went wrong!";
        }
        return "Success";
    }


    public String handIn(DTO media) {
        ValidationResult vr = BorrowController.getInstance().handIn(media);
        if (vr.hasErrors()) {
            return "Something went wrong!";
        }
        return "Success";
    }

}
