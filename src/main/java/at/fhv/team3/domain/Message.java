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
    private ExternalLib _lib;
    private Borrowable _borrowable;
    private String _message;

    public Message(){}
    public void setCustomer(Customer customer){
        _customer = customer;
    }

    public Customer getCustomer(){
        return _customer;
    }

    public ExternalLib getLib(){
        return _lib;
    }

    public void setLib(ExternalLib lib){
        _lib = lib;
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
        if(_message != null) {
            dto.setMessage(_message);
        }
        if(_borrowable != null) {
            dto.setMedia(_borrowable.createDataTransferObject());
        }
        return dto;
    }

    public boolean equals(Message message){
       /* if(message.getCustomer() != null && message.getMessage() != null) {
                if (_customer.equals(message.getCustomer())) {
                    if (_message.equals(message.getMessage())) {
                        if (message.getBorrowable() != null && _borrowable != null) {
                            if (_borrowable.equals(message.getBorrowable())) {
                                return true;
                            }
                        }
                    }
                }
            }*/
        if(_message.equals(message.getMessage())){
            return true;
        }
        return false;
    }

    //TODO: implement
    public void fillFromDTO(DTO dto) {

    }
}
