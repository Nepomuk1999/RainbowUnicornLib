package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.BookedItemDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.interfaces.Borrowable;
import at.fhv.team3.domain.interfaces.Transferable;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by David on 10/30/2017.
 */
@Entity
@Table(name = "bookingItem")
public class BookedItem implements Transferable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "bookingId")
    private int _bookingId;

    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer _customer;

    @OneToOne(optional = true)
    @JoinColumn(name = "book_bookId", nullable = true)
    private Book _book;

    @OneToOne(optional = true)
    @JoinColumn(name = "dvd_dvdId", nullable = true)
    private Dvd _dvd;

    @OneToOne(optional = true)
    @JoinColumn(name = "magazine_magazineId", nullable = true)
    private Magazine _magazine;

    @Column(name = "date")
    private Date _date;

    public BookedItem(){ }

    public void setBookingId(int id){
        _bookingId = id;
    }

    public int getBookingId(){
        return _bookingId;
    }

    public void setCustomer(Customer customer){
        _customer = customer;
    }

    public Customer getCustomer(){
        return _customer;
    }

    public void setDate(Date date){
        _date = date;
    }

    public Date getdate(){
        return _date;
    }

    public DTO createDataTransferObject() {
        return new BookedItemDTO(_bookingId, _customer, _date);
    }

    public void fillFromDTO(DTO dto) {
        HashMap<String, String> allData = dto.getAllData();
        _bookingId = Integer.parseInt(allData.get("id"));
       // _customer = allData.get("customer");
        _date = new Date(allData.get("date"));
    }

    public Borrowable getMedia() {
        if ( _book != null) {
            return _book;
        } else if (_dvd != null) {
            return _dvd;
        } else if (_magazine != null ) {
            return _magazine;
        } else {
            return null;
        }
    }
}
