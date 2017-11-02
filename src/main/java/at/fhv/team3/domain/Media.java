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

    // private ArrayList<Borrowable> _medias;

    @OneToMany(mappedBy = "_bookId", cascade = CascadeType.ALL)
    private Set<Book> bookList = new HashSet<Book>();

    @OneToMany(mappedBy = "_dvdId", cascade = CascadeType.ALL)
    private Set<Dvd> dvdList = new HashSet<Dvd>();

    @OneToMany(mappedBy = "_magazineId", cascade = CascadeType.ALL)
    private Set<Magazine> magazineList = new HashSet<Magazine>();
/*  private Magazine _magazine;
    private Dvd _dvd;
    private Book _book; */

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

    /*
    public void addMedia(Borrowable media){
        _medias.add(media);
    }

    public ArrayList<Borrowable> getMedias(){
        return _medias;
    }
    */

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


/*    public void setMagazine(Magazine magazine){
        _magazine = magazine;
    }

    public Magazine getMagazine(){
        return _magazine;
    }

    public void setDvd(Dvd dvd){
        _dvd = dvd;
    }

    public Dvd getDvd(){
        return _dvd;
    }

    public void setBook(Book book){
        _book = book;
    }

    public Book getBook(){
        return _book;
    }
    */

    public boolean containsSearchTerm(String searchTerm) {

        return false;
    }

    public DTO createDataTransferObject() {
        return null;
    }
}

