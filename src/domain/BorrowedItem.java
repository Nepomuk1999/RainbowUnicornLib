package domain;

import java.util.Date;

/**
 * Created by David on 10/30/2017.
 */
public class BorrowedItem {

    private int _borrowedId;
    private Date _borrowedDate;
    private ExternalLib _externalLib;
    private Customer _customer;
    private Media _media;

    public BorrowedItem(){

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

    public void setMedia(Media m){
        _media = _media;
    }

    public Media getMedia(){
        return _media;
    }
}
