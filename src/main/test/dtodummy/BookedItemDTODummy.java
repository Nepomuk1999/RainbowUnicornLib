package dtodummy;

import at.fhv.team3.domain.dto.BookedItemDTO;
import at.fhv.team3.domain.dto.CustomerDTO;
import at.fhv.team3.domain.dto.DTO;

import java.util.Date;

public class BookedItemDTODummy extends BookedItemDTO {

    public BookedItemDTODummy(int id, CustomerDTO customer, Date date, DTO dto) {
        super(id, customer, date, dto);
    }
}
