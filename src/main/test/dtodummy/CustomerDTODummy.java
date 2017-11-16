package dtodummy;

import at.fhv.team3.domain.dto.CustomerDTO;

public class CustomerDTODummy extends CustomerDTO {

    public CustomerDTODummy(int id, String fname, String lname, boolean subscription, String email, String phone) {
        super(id, fname, lname, subscription, email, phone);
    }
}
