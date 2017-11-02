package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.BookDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.interfaces.Borrowable;

import javax.persistence.*;

/**
 * Created by David on 10/30/2017.
 */
@Entity
@Table(name = "book")
public class Book implements Borrowable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private int _bookId;

    @ManyToOne(targetEntity = Media.class)
    @JoinColumn(name = "media_mediaId")
    private Media media;

    private String _title;
    private String _publisher;
    private String _author;
    private String _isbn;
    private String _edition;
    private String _pictureURL;
    private String _shelfPos;

    public Book(){}

    public Book( String title, String author, String publisher, String isbn) {
        this._title = title;
        this._author = author;
        this._publisher = publisher;
        this._isbn = isbn;
    }

    public void set_bookId(int id){
        _bookId = id;
    }

    public int get_bookId(){
        return _bookId;
    }

    public void setTitle(String title){
        _title = title;
    }

    public String getTitle(){
        return _title;
    }

    public void setPublisher(String publisher){
        _publisher = publisher;
    }

    public String getPublisher(){
        return _publisher;
    }

    public void setAuthor(String author){
        _author = author;
    }

    public String getAuthor(){
        return _author;
    }

    public void setIsbn(String isbn){
        _isbn = isbn;
    }

    public String getIsbn(){
        return _isbn;
    }

    public void setEdition(String edition){
        _edition = edition;
    }

    public void setPictureURL(String pictureURL){
        _pictureURL = pictureURL;
    }

    public void setShelfPos(String shelfPos){
        _shelfPos = shelfPos;
    }

    public String getShelfPos(){
        return _shelfPos;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia_mediaId(Media media) {
        this.media = media;
    }

    public boolean containsSearchTerm(String searchTerm) {
        if(_title.contains(searchTerm) || _publisher.contains(searchTerm) || _author.contains(searchTerm) || _isbn.contains(searchTerm) || _edition.contains(searchTerm)){
            return true;
        }
        return false;
    }

    public DTO createDataTransferObject() {
        return new BookDTO(_bookId, _title, _publisher, _author, _isbn, _edition, _pictureURL, _shelfPos);
    }
}
