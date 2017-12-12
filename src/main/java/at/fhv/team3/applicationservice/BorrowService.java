package at.fhv.team3.applicationservice;


import at.fhv.team3.application.BorrowController;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.ExternalLibDTO;
import at.fhv.team3.domain.dto.ValidationResult;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class BorrowService {

    @WebMethod
    public ValidationResult handOut(DTO media, ExternalLibDTO externalLibDTO) {
        return BorrowController.getInstance().handOut(media, externalLibDTO);
    }

    @WebMethod
    public ValidationResult handIn(DTO media) {
        return BorrowController.getInstance().handIn(media);
    }
}
