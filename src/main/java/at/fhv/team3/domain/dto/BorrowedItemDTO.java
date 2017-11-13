package at.fhv.team3.domain.dto;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by David on 10/30/2017.
 */
public class BorrowedItemDTO extends DTO {

    private int _borrowedId;
    private Date _borrowedDate;
    private ExternalLibDTO _externalLib;
    private CustomerDTO _customer;


    public BorrowedItemDTO(int id, Date borrowedDate, ExternalLibDTO externalLib, CustomerDTO customer) {
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

    public void setExternalLib(ExternalLibDTO lib){
        _externalLib = lib;
    }

    public ExternalLibDTO getExternalLib(){
        return _externalLib;
    }

    public void setCustomer(CustomerDTO customer){
        _customer = customer;
    }

    public CustomerDTO getCustomer(){
        return _customer;
    }

    public void setId(int id) {
        setBorrowedId(id);
    }

    public int getId() {
        return getBorrowedId();
    }

    public HashMap<String, String> getAllData() {
        HashMap<String, String> allData = new HashMap<String, String>();
        allData.put("id", ""+_borrowedId);
        allData.put("externalLib", _externalLib.toString());
        allData.put("date", _borrowedDate.toString());
        allData.put("customer", _customer.toString());
        return allData;
    }

    public boolean equals(DTO dto) {
        HashMap<String, String> data = dto.getAllData();
        if(data.get("externalLib").equals(_externalLib.getName()) && data.get("date").equals(_borrowedDate.toString()) && data.get("customer").equals(_customer.getFirstName() + " " + _customer.getLastName())){
            return true;
        }
        return false;
    }


}
