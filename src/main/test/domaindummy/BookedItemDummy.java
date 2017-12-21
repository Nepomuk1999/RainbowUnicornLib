package domaindummy;

import at.fhv.team3.domain.*;

import java.util.Date;

public class BookedItemDummy extends BookedItem {

    public BookedItemDummy(int id, Customer customer, Book book, Dvd dvd, Magazine magazinen,
                           Date date) {
        this.setBookingId(id);
        this.setCustomer(customer);
        this.setBook(book);
        this.setDvd(dvd);
        this.setMagazine(magazinen);
        this.setDate(date);
    }
}
