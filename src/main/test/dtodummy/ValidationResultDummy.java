package dtodummy;

import at.fhv.team3.domain.dto.ValidationResult;

import java.util.ArrayList;

public class ValidationResultDummy extends ValidationResult {

    public ValidationResultDummy(ArrayList<String> errorMessages) {
        super();
        for (String str : errorMessages) {
            add(str);
        }
    }
}
