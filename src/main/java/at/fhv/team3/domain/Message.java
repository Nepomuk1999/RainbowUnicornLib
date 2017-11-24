package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.CustomerDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.MessageDTO;
import at.fhv.team3.domain.interfaces.Borrowable;
import at.fhv.team3.domain.interfaces.Transferable;

/**
 * Created by David on 11/24/2017.
 */
public class Message implements Transferable{

    private Customer _customer;
    private Borrowable _borrowable;
    private String _message;

    public Message(){}
    public void setCustomer(Customer customer){
        _customer = customer;
    }

    public Customer getCustomer(){
        return _customer;
    }

    public Borrowable getBorrowable(){
        return _borrowable;
    }

    public void setBorrowable(Borrowable borrowable){
        _borrowable = borrowable;
    }

    public void setMessage(String message){
        _message = message;
    }

    public String getMessage(){
        return _message;
    }

    public DTO createDataTransferObject() {
        MessageDTO dto = new MessageDTO();
        if(_customer != null) {
            dto.setCustomer((CustomerDTO) _customer.createDataTransferObject());
        }
        dto.setMessage(_message);
        dto.setMedia(_borrowable.createDataTransferObject());
        return dto;
    }

    //TODO: implement
    public void fillFromDTO(DTO dto) {

    }
}
