package at.fhv.team3.domain.dto;

import at.fhv.team3.domain.Customer;
import at.fhv.team3.domain.ExternalLib;

import java.util.Date;

/**
 * Created by David on 10/30/2017.
 */
public class BorrowedItemDTO extends DTO{

    private int _borrowedId;
    private Date _borrowedDate;
    private ExternalLib _externalLib;
    private Customer _customer;


    public BorrowedItemDTO(int id, Date borrowedDate, ExternalLib externalLib, Customer customer) {
        _borrowedId = id;
        _borrowedDate = borrowedDate;
        _externalLib = externalLib;
        _customer = customer;

    }

    public void setBorrowedId(int id){
        _borrowedId = id;
    }

    public int getBorrowedId(){
        return _borrowedId;
    }

    public void setBorrowedDate(Date borrowedDate){
        _borrowedDate = borrowedDate;
    }

    public Date getBorrowedDate(){
        return _borrowedDate;
    }

    public void setExternalLib(ExternalLib lib){
        _externalLib = lib;
    }

    public ExternalLib getExternalLib(){
        return _externalLib;
    }

    public void setCustomer(Customer customer){
        _customer = customer;
    }

    public Customer getCustomer(){
        return _customer;
    }

    public void setId(int id) {
        setBorrowedId(id);
    }

    public int getId() {
        return getBorrowedId();
    }

    public String[] getAllDataAsStringArray() {
        String[] allData = {""+_borrowedId, _borrowedDate.toString(), _externalLib.getName(), _customer.getFirstName() + " " + _customer.getLastName()};
        return allData;
    }
}
