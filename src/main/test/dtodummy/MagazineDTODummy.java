package dtodummy;

import at.fhv.team3.domain.dto.MagazineDTO;

public class MagazineDTODummy extends MagazineDTO {

    public MagazineDTODummy(int id, String title, String edition, String publisher, String pictureURL, String shelfPos) {
        super(id, title, edition, publisher, pictureURL, shelfPos);
    }
}
