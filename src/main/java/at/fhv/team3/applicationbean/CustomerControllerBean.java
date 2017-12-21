package at.fhv.team3.applicationbean;

import at.fhv.team3.application.CustomerController;
import at.fhv.team3.applicationbean.interfaces.RemoteCustomerBeanFace;
import at.fhv.team3.domain.dto.CustomerDTO;
import at.fhv.team3.domain.dto.DTO;

import javax.ejb.Stateless;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by ClemensB on 03.12.17.
 */

@Stateless(mappedName = "CustomerEJB")
public class CustomerControllerBean implements RemoteCustomerBeanFace {

    private CustomerController _customerController;

    public CustomerControllerBean(){
        try {
            _customerController = new CustomerController();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<DTO> findCustomer(String term){
        return _customerController.findCustomer(term);
    }

    @Override
    public void saveCustomer(CustomerDTO dto){
        _customerController.saveCustomer(dto);
    }
}
