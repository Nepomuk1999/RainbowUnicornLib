package domaindummy;

import at.fhv.team3.domain.*;

import java.util.Date;

public class BorrowedItemDummy extends BorrowedItem {

    public BorrowedItemDummy(int id, Date date, int extendCounter, ExternalLib externalLib,
                             Customer customer, Book book, Dvd dvd, Magazine magazine) {
        setBorrowedId(id);
        setBorrowedDate(date);
        setExtendCount(extendCounter);
        setExternalLib(externalLib);
        setCustomer(customer);
        setCustomer(customer);
        setBook(book);
        setDvd(dvd);
        setMagazine(magazine);
    }

}
