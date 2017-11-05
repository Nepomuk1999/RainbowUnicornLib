package at.fhv.team3.domain.dto;

/**
 * Created by David on 10/30/2017.
 */
public class CustomerDTO extends DTO{

    private int _customerId;
    private String _firstName;
    private String _lastName;
    private boolean _subscription;
    private String _email;
    private String _phoneNumber;

    public CustomerDTO(){

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

    public void setId(int id) {
        setCustomerId(id);
    }

    public int getId() {
        return getCustomerId();
    }

    public String[] getAllDataAsStringArray() {
        String subscription;
        if(_subscription){
            subscription = "true";
        } else {
            subscription = "false";
        }
        String[] allData = {""+_customerId, _firstName, _lastName, subscription, _email, _phoneNumber};
        return allData;
    }
}
