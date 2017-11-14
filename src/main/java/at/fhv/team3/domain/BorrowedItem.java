package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.BorrowedItemDTO;
import at.fhv.team3.domain.dto.CustomerDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.ExternalLibDTO;
import at.fhv.team3.domain.interfaces.Borrowable;
import at.fhv.team3.domain.interfaces.Transferable;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by David on 10/30/2017.
 */
@Entity
@Table(name = "borrowedItem")
public class BorrowedItem implements Transferable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrowedId")
    private int _borrowedId;

    @Column(name = "borrowedDate")
    private Date _borrowedDate;

    @OneToOne
    @JoinColumn(name = "libId", nullable = true)
    private ExternalLib _externalLib;

    @OneToOne
    @JoinColumn(name = "customerId", nullable = true)
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

    public void setBorrowedId(int id){
        _borrowedId = id;
    }

    public int getBorrowedId(){
        return _borrowedId;
    }

    public void setBorrowedDate(Date borrowedDate){
        _borrowedDate = borrowedDate;
    }

    public Date getBorrowedDate(){
        return _borrowedDate;
    }

    public void setExternalLib(ExternalLib lib){
        _externalLib = lib;
    }

    public ExternalLib getExternalLib(){
        return _externalLib;
    }

    public void setCustomer(Customer customer){
        _customer = customer;
    }

    public Customer getCustomer(){
        return _customer;
    }

    public void setBook(Book book){
        _book = book;
    }

    public Book getBook(){
        return _book;
    }

    public void setDvd(Dvd dvd){
        _dvd = dvd;
    }

    public Dvd getDvd(){
        return _dvd;
    }

    public void setMagazine(Magazine magazine){
        _magazine = magazine;
    }

    public Magazine getMagazine(){
        return _magazine;
    }

    public DTO createDataTransferObject() {
        return new BorrowedItemDTO(_borrowedId, _borrowedDate, (ExternalLibDTO) _externalLib.createDataTransferObject(), (CustomerDTO) _customer.createDataTransferObject());
    }

    public void fillFromDTO(DTO dto) {
        HashMap<String, String> allData = dto.getAllData();
        _borrowedId = Integer.parseInt(allData.get("id"));
        _externalLib.createFromString(allData.get("externalLib"));
        _customer.createFromString(allData.get("customer"));
        _borrowedDate = new Date(allData.get("borrowedDate"));
        if (allData.get("book") != null) {
            _book.createFromString(allData.get("book"));
        } else if (allData.get("dvd") != null) {
            _dvd.createFromString(allData.get("dvd"));
        } else {
            _magazine.createFromString(allData.get("magazine"));
        }
    }

    public Book get_book() {
        return _book;
    }

    public void set_book(Book _book) {
        this._book = _book;
    }

    public Dvd get_dvd() {
        return _dvd;
    }

    public void set_dvd(Dvd _dvd) {
        this._dvd = _dvd;
    }

    public Magazine get_magazine() {
        return _magazine;
    }

    public void set_magazine(Magazine _magazine) {
        this._magazine = _magazine;
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
