package domaindummy;

import at.fhv.team3.domain.Magazine;

public class MagazineDummy extends Magazine {

    public MagazineDummy(int id, String title, String edition, String publisher,
                         String pictureUrl, String shelfPos) {
        this.setMagazineId(id);
        this.setTitle(title);
        this.setEdition(edition);
        this.setPublisher(publisher);
        this.setPictureURL(pictureUrl);
        this.setShelfPos(shelfPos);
    }
}
