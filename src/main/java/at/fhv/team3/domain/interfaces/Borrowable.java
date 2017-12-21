package at.fhv.team3.domain.interfaces;

import java.util.Date;

/**
 * Created by David on 10/31/2017.
 */
public interface Borrowable extends Searchable {

    public int getId();

    //Für die Gruppierung von expliziten Exemplaren zum Medium
    public boolean isSameMedia(Borrowable b);
    public String getMessageString();
    public void setReturnDate(Date d);
    public Date getReturnDate();
}
