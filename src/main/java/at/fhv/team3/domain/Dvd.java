package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.interfaces.Searchable;

import javax.persistence.*;

/**
 * Created by David on 10/30/2017.
 */
public class Dvd implements Searchable {

    @Id
    private int _dvdId;

    @ManyToOne
    private int media_mediaId;

    private String _title;
    private String _regisseur;
    private String _pictureURL;
    private String _shelfPos;

    public Dvd(){

    }

    public void setDvdId(int dvdId){
        _dvdId = dvdId;
    }

    public int getDvdId(){
        return _dvdId;
    }

    public void setTitle(String title){
        _title = title;
    }

    public String getTitle(){
        return _title;
    }

    public void setRegisseur(String regisseur){
        _regisseur = regisseur;
    }

    public String getRegisseur(){
        return _regisseur;
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

    public int getMedia_mediaId() {
        return media_mediaId;
    }

    public void setMedia_mediaId(int media_mediaId) {
        this.media_mediaId = media_mediaId;
    }

    public boolean containsSearchTerm(String searchTerm) {
        if(_title.contains(searchTerm) || _regisseur.contains(searchTerm)){
            return true;
        }
        return false;
    }

    public DTO createDataTransferObject() {
        return null;
    }
}
