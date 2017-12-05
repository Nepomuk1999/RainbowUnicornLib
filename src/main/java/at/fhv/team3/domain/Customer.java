package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.CustomerDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.interfaces.Searchable;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerId")
    private int _customerId;

//    @OneToMany
//    private ArrayList<BorrowedItem> borrowedItems = new ArrayList<BorrowedItem>();

    @Column(name = "firstName")
    private String _firstName;

    @Column(name = "lastName")
    private String _lastName;

    @Column(name = "subscription")
    private boolean _subscription;

    @Column(name = "email")
    private String _email;

    @Column(name = "phoneNr")
    private String _phoneNumber;

    public Customer(){}

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
        return new CustomerDTO(_customerId, _firstName, _lastName, _subscription, _email, _phoneNumber);
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

    public boolean equals(Customer c){
        if(c.getCustomerId() == _customerId && c.getFirstName().equals(_firstName) && c.getLastName().equals(_lastName) && c.getEmail().equals(_email) && c.getPhoneNumber().equals(_phoneNumber)){
            return true;
        }
        return false;
    }

    public void createFromString(String s) {
        ArrayList<String> stringList = new ArrayList<String>();
        for(String word : s.split(" ")) {
            stringList.add(word);
        }
        setCustomerId(Integer.parseInt(stringList.get(0)));
        setFirstName(stringList.get(1));
        setLastName(stringList.get(2));
        setSubscription(Boolean.getBoolean(stringList.get(3)));
        setEmail(stringList.get(4));
        setPhoneNumber(stringList.get(5));
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
