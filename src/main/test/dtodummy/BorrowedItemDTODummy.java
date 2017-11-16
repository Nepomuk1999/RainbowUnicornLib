package dtodummy;

import at.fhv.team3.domain.dto.BorrowedItemDTO;
import at.fhv.team3.domain.dto.DTO;

import java.util.Date;

public class BorrowedItemDTODummy extends BorrowedItemDTO {

    public BorrowedItemDTODummy(int id, Date borrowedDate, DTO borrower, DTO dto) {
        super(id, borrowedDate, borrower, dto);
    }
}
