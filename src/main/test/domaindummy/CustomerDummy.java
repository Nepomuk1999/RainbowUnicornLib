package domaindummy;

import at.fhv.team3.domain.Customer;

public class CustomerDummy extends Customer {

    public CustomerDummy(int id, String fname, String lname, Boolean subscribtion,
                         String email, String phonenumber) {
        this.setCustomerId(id);
        this.setEmail(email);
        this.setFirstName(fname);
        this.setLastName(lname);
        this.setPhoneNumber(phonenumber);
        this.setSubscription(subscribtion);
    }
}
