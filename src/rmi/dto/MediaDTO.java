package rmi.dto;

import domain.MediaType;

import java.util.Date;

/**
 * Created by David on 10/30/2017.
 */
public class MediaDTO {
    private int _mediaId;
    private MediaType _type;
    private Date _returnDate;
    private int _magazineId;
    private int _dvdId;
    private int _bookId;

    public MediaDTO(){

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

    public void setMagazineId(int id){
        _magazineId = id;
    }

    public int getMagazineId(){
        return _magazineId;
    }

    public void setDvdId(int id){
        _dvdId = id;
    }

    public int getDvdId(){
        return _dvdId;
    }

    public void setBookId(int id){
        _bookId = id;
    }

    public int getBookId(){
        return _bookId;
    }
}
