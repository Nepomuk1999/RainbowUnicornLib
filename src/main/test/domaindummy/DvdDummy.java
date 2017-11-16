package domaindummy;

import at.fhv.team3.domain.Dvd;

public class DvdDummy extends Dvd {

    public DvdDummy(int id, String title, String regiseure, String pictureUrl, String shelfPos) {
        this.setDvdId(id);
        this.setTitle(title);
        this.setRegisseur(regiseure);
        this.setPictureURL(pictureUrl);
        this.setShelfPos(shelfPos);
    }
}
