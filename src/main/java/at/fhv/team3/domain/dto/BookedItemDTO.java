package at.fhv.team3.domain.dto;

import at.fhv.team3.domain.Customer;

import java.util.Date;

/**
 * Created by David on 10/30/2017.
 */
public class BookedItemDTO extends DTO{

    private int _bookingId;
    private Customer _customer;
    private Date _date;

    public BookedItemDTO(int id, Customer customer, Date date) {
        _bookingId = id;
        _customer = customer;
        _date = date;
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

    public void setId(int id) {
        setBookingId(id);
    }

    public int getId() {
        return getBookingId();
    }

    public String[] getAllDataAsStringArray() {
        String[] allData = {""+_bookingId, _customer.getFirstName() + " " + _customer.getLastName(), _date.toString()};
        return allData;
    }
}
