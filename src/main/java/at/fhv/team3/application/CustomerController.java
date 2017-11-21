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

    //Alle Kunden aus der Datenbank laden (Liste von Kunden)
    private List<Customer> loadCustomer(){
        return _customerRepository.getAll();
    }

    //Einen bestimmten Kunden nach dem Namen suchen (ArrayList von CustomerDTOs
    public ArrayList<DTO> findCustomer(String term){
        List<Customer> allCustomers = loadCustomer();
        ArrayList<DTO> matchingCustomers = new ArrayList<DTO>();
        for(Customer c : allCustomers){
            String name = c.getFirstName() + " " + c.getLastName();
            if(name.toLowerCase().contains(term.toLowerCase())){
                matchingCustomers.add(c.createDataTransferObject());
            }
        }
        return matchingCustomers;
    }

    //Einen neuen Kunden in die Datenbank einfügen
    public void saveCustomer(Customer c){
        _customerRepository.save(c);
    }

    //Einen neuen Kunden aus einem CustomerDTO in die Datenbank einfügen
    public void saveCustomer(CustomerDTO dto){
        Customer customer = new Customer();
        customer.fillFromDTO(dto);
        saveCustomer(customer);
    }
}
