package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.interfaces.Searchable;
import at.fhv.team3.domain.interfaces.Transferable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by David on 10/30/2017.
 */
@Entity
@Table(name = "customer")
public class Customer implements Searchable {

    @Id
    private int _customerId;

    @OneToMany
    private ArrayList<BorrowedItem> borrowedItems = new ArrayList<BorrowedItem>();

    private String _firstName;
    private String _lastName;
    private boolean _subscription;
    private String _email;
    private String _phoneNumber;

    public Customer(){

    }

    public void setCustomerId(int id){
        _customerId = id;
    }

    public int getCustomerId(){
        return _customerId;
    }

    public void setFirstName(String firstname){
        _firstName = firstname;
    }

    public String getFirstName(){
        return _firstName;
    }

    public void setLastName(String lastname){
        _lastName = lastname;
    }

    public String getLastName(){
        return _lastName;
    }

    public void setSubscription(boolean subscription){
        _subscription = subscription;
    }

    public boolean getSubscription(){
        return _subscription;
    }

    public void setEmail(String email){
        _email = email;
    }

    public String getEmail(){
        return _email;
    }

    public void setPhoneNumber(String phoneNumber){
        _phoneNumber = phoneNumber;
    }

    public String getPhoneNumber(){
        return _phoneNumber;
    }

    public DTO createDataTransferObject() {
        return null;
    }

    public void fillFromDTO(DTO dto) {
        HashMap<String, String> allData = dto.getAllData();
        _customerId = Integer.parseInt(allData.get("id"));
        _firstName = allData.get("firstname");
        _lastName = allData.get("lastname");
        boolean subscription;
        if(allData.get("subscription").equals("true")){
            subscription = true;
        } else {
            subscription = false;
        }
        _subscription = subscription;
        _email = allData.get("email");
        _phoneNumber = allData.get("phonenumber");
    }

    public boolean containsSearchTerm(String searchTerm) {
        if( Pattern.compile(Pattern.quote(searchTerm), Pattern.CASE_INSENSITIVE).matcher(_firstName).find()
                || Pattern.compile(Pattern.quote(searchTerm), Pattern.CASE_INSENSITIVE).matcher(_lastName).find()
                || Pattern.compile(Pattern.quote(searchTerm), Pattern.CASE_INSENSITIVE).matcher(_email).find()
                || Pattern.compile(Pattern.quote(searchTerm), Pattern.CASE_INSENSITIVE).matcher(_phoneNumber).find()) {
            return true;
        }
        return false;
    }
}
