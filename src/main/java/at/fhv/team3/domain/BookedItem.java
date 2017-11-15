package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.BookedItemDTO;
import at.fhv.team3.domain.dto.CustomerDTO;
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

    public void setBook(Book b){
        _book = b;
    }

    public Book getBook(){
        return _book;
    }

    public void setDvd(Dvd d){
        _dvd = d;
    }

    public Dvd getDvd(){
        return _dvd;
    }

    public void setMagazine(Magazine m){
        _magazine = m;
    }

    public Magazine getMagazine(){
        return _magazine;
    }

    public Date getdate(){
        return _date;
    }

    public DTO createDataTransferObject() {
        DTO dto;
        if(_book != null){
            dto = _book.createDataTransferObject();
        } else if(_dvd != null){
            dto = _dvd.createDataTransferObject();
        } else {
            dto = _magazine.createDataTransferObject();
        }
        return new BookedItemDTO(_bookingId, (CustomerDTO) _customer.createDataTransferObject(), _date, dto);
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
