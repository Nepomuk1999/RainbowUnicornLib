package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.BookedItemDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.interfaces.Transferable;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by David on 10/30/2017.
 */
@Entity
@Table(name = "bookingItem")
public class BookedItem implements Transferable {

    @Id
    private int _bookingId;

    @ManyToOne
    private Customer _customer;

    private Date _date;

    public BookedItem(){

    }

    public void setBookingId(int id){
        _bookingId = id;
    }

    public int getBookingId(){
        return _bookingId;
    }

    public void setCustomer(Customer customer){
        _customer = customer;
    }

    public Customer getCustomer(){
        return _customer;
    }

    public void setDate(Date date){
        _date = date;
    }

    public Date getdate(){
        return _date;
    }

    public DTO createDataTransferObject() {
        return new BookedItemDTO(_bookingId, _customer, _date);
    }

    public void fillFromDTO(DTO dto) {
        HashMap<String, String> allData = dto.getAllData();
        _bookingId = Integer.parseInt(allData.get("id"));
       // _customer = allData.get("customer");
        _date = new Date(allData.get("date"));
    }
}
