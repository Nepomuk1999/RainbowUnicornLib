package at.fhv.team3.applicationbean;

import at.fhv.team3.application.BorrowController;
import at.fhv.team3.applicationbean.interfaces.RemoteBorrowBeanFace;
import at.fhv.team3.domain.dto.CustomerDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.ValidationResult;

import javax.ejb.Stateless;
import java.rmi.RemoteException;

/**
 * Created by ClemensB on 03.12.17.
 */
@Stateless
public class BorrowControllerBean implements RemoteBorrowBeanFace{

    private BorrowController _borrowControllerBean;

    private BorrowControllerBean(){
        try {
            _borrowControllerBean = new BorrowController();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public ValidationResult handOut(DTO media, CustomerDTO customer){
        return _borrowControllerBean.handOut(media, customer);
    }

    public ValidationResult handIn(DTO media){
        return _borrowControllerBean.handIn(media);
    }

    public ValidationResult extend(DTO media) {
        return _borrowControllerBean.extend(media);
    }

    public DTO getCustomerByMedia(DTO media){
        return _borrowControllerBean.getCustomerByMedia(media);
    }


}
