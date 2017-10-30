package at.fhv.team3.rmi.dto;

import at.fhv.team3.domain.Customer;
import at.fhv.team3.domain.Media;

import java.util.Date;

/**
 * Created by David on 10/30/2017.
 */
public class BookedItemDTO {

    private int _bookingId;
    private Customer _customer;
    private Media _media;
    private Date _date;

    public BookedItemDTO(){

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

    public void setMedia(Media media){
        _media = media;
    }

    public Media getMedia(){
        return _media;
    }

    public void setDate(Date date){
        _date = date;
    }

    public Date getdate(){
        return _date;
    }
}
