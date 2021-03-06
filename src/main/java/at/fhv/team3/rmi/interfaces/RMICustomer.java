package at.fhv.team3.rmi.interfaces;

import at.fhv.team3.domain.Customer;
import at.fhv.team3.domain.dto.CustomerDTO;
import at.fhv.team3.domain.dto.DTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 11/8/2017.
 */
public interface RMICustomer  extends Remote {

    public ArrayList<DTO> findCustomer(String term) throws RemoteException;
    public void saveCustomer(CustomerDTO dto) throws RemoteException;

}
