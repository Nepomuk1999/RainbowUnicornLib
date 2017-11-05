package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.BorrowedItemDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.interfaces.Transferable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by David on 10/30/2017.
 */
@Entity
@Table(name = "borrowedItem")
public class BorrowedItem implements Transferable {

    @Id
    private int _borrowedId;
    private Date _borrowedDate;

    @ManyToOne
    private ExternalLib _externalLib;

    @ManyToOne
    private Customer _customer;

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

    public DTO createDataTransferObject() {
        return new BorrowedItemDTO(_borrowedId, _borrowedDate, _externalLib, _customer);
    }
}
