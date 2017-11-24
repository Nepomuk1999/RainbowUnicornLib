package at.fhv.team3.domain.dto;

import java.util.HashMap;

/**
 * Created by David on 11/24/2017.
 */
public class MessageDTO extends DTO {
    private CustomerDTO _customer;
    private BookDTO _book;
    private DvdDTO _dvd;
    private MagazineDTO _magazine;
    private String _message;

    public MessageDTO(){}
    public void setCustomer(CustomerDTO customer){
        _customer = customer;
    }

    public CustomerDTO getCustomer(){
        return _customer;
    }

    public DTO getBorrowable(){
        if(_book != null){
            return _book;
        } else if(_dvd != null){
            return _dvd;
        } else {
            return _magazine;
        }
    }

    public void setMedia(DTO media){
        if(media.getClass() == _book.getClass()){
            _book = (BookDTO) media;
        } else if(media.getClass() == _dvd.getClass()){
            _dvd = (DvdDTO) media;
        } else if(media.getClass() == _magazine.getClass()){
            _magazine = (MagazineDTO) media;
        }
    }

    public void setMessage(String message){
        _message = message;
    }

    public String getMessage(){
        return _message;
    }

    public void setId(int id) {

    }

    public int getId() {
        return 0;
    }

    public HashMap<String, String> getAllData() {
        return null;
    }

    public boolean equals(DTO dto) {
        return false;
    }
}
