package at.fhv.team3.application;

import at.fhv.team3.domain.Customer;
import at.fhv.team3.domain.dto.CustomerDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.persistence.CustomerRepository;
import at.fhv.team3.rmi.interfaces.RMICustomer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 11/8/2017.
 */
public class CustomerController extends UnicastRemoteObject implements RMICustomer {

    private CustomerRepository _customerRepository;

    public CustomerController() throws RemoteException {
        _customerRepository = CustomerRepository.getInstance();
    }

    public List<DTO> findCustomer(String term) throws RemoteException{
        List<Customer> allCustomers = _customerRepository.getAll();
        List<DTO> matchingCustomers = new ArrayList<DTO>();
        for(Customer c : allCustomers){
            if(c.containsSearchTerm(term)){
                matchingCustomers.add(c.createDataTransferObject());
            }
        }
        return matchingCustomers;
    }

    public void saveNewCustomer(CustomerDTO dto) throws RemoteException{
        Customer customer = new Customer();
        customer.fillFromDTO(dto);
        _customerRepository.save(customer);
    }
}
