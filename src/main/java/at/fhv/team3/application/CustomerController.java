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

    private List<Customer> loadCustomer(){
        return _customerRepository.getAll();
    }

    public ArrayList<DTO> findCustomer(String term){
        List<Customer> allCustomers = loadCustomer();
        ArrayList<DTO> matchingCustomers = new ArrayList<DTO>();
        for(Customer c : allCustomers){
            String name = c.getFirstName() + " " + c.getLastName();
            if(name.contains(term)){
                matchingCustomers.add(c.createDataTransferObject());
            }
        }
        return matchingCustomers;
    }

    public void saveCustomer(Customer c){
        _customerRepository.save(c);
    }

    public void saveNewCustomer(CustomerDTO dto){
        Customer customer = new Customer();
        customer.fillFromDTO(dto);
        saveCustomer(customer);
    }
}
