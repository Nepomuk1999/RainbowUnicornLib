package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.interfaces.Searchable;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by David on 10/30/2017.
 */
@Entity
@Table(name = "media")
public class Media implements Searchable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mediaId")
    private int _mediaId;

    @Column(name = "mediaType")
    private MediaType _type;

    @Column(name = "returnDate")
    private Date _returnDate;

    @OneToMany(mappedBy = "_bookId", cascade = CascadeType.ALL)
    private Set<Book> bookList = new HashSet<Book>();

    @OneToMany(mappedBy = "_dvdId", cascade = CascadeType.ALL)
    private Set<Dvd> dvdList = new HashSet<Dvd>();

    @OneToMany(mappedBy = "_magazineId", cascade = CascadeType.ALL)
    private Set<Magazine> magazineList = new HashSet<Magazine>();

    public Media(){}

    public Media (MediaType mediaType, Date returnDate) {
        this._type = mediaType;
        this._returnDate = returnDate;
    }

    public void setMediaId(int id){
        _mediaId = id;
    }

    public int getMediaId(){
        return _mediaId;
    }

    public void setType(MediaType type){
        _type = type;
    }

    public MediaType getType(){
        return _type;
    }

    public void setReturnDate(Date returnDate){
        _returnDate = returnDate;
    }

    public Date getReturnDate(){
        return _returnDate;
    }

    public Set<Dvd> getDvdList() {
        return dvdList;
    }

    public void addToDvdList(Dvd value) {
        dvdList.add(value);
    }

    public Set<Magazine> getMagazineList() {
        return magazineList;
    }

    public void addToMagazineList(Magazine value) {
        magazineList.add(value);
    }

    public Set<Book> getBookList() {
        return bookList;
    }

    public void addToBookList(Book value) {
        bookList.add(value);
    }

    public boolean containsSearchTerm(String searchTerm) {

        return false;
    }

    public DTO createDataTransferObject() {
        return null;
    }
}

