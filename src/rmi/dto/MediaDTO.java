package rmi.dto;

import domain.Book;
import domain.Dvd;
import domain.Magazine;
import domain.MediaType;

import java.util.Date;

/**
 * Created by David on 10/30/2017.
 */
public class MediaDTO {

    private int _mediaId;
    private MediaType _type;
    private Date _returnDate;
    private Magazine _magazine;
    private Dvd _dvd;
    private Book _book;

    public Media(){

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

    public void setMagazine(Magazine magazine){
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
}
