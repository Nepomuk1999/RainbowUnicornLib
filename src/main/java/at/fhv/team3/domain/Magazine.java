package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.interfaces.Borrowable;
import at.fhv.team3.domain.interfaces.Searchable;

import javax.persistence.*;

/**
 * Created by David on 10/30/2017.
 */
@Entity
@Table(name = "magazine")
public class Magazine implements Borrowable {

    @Id
    private int _magazineId;

    @ManyToOne
    private int media_mediaId;

    private String _title;
    private String _edition;
    private String _publisher;
    private String _pictureURL;
    private String _shelfPos;

    public Magazine(){

    }

    public void setMagazineId(int id){
        _magazineId = id;
    }

    public int getMagazineId(){
        return _magazineId;
    }

    public void setTitle(String title){
        _title = title;
    }

    public String getTitle(){
        return _title;
    }

    public void setEdition(String edition){
        _edition = edition;
    }

    public String getEdition(){
        return _edition;
    }

    public void setPublisher(String publisher){
        _publisher = publisher;
    }

    public String getPublisher(){
        return _publisher;
    }

    public void setPictureURL(String pictureURL){
        _pictureURL = pictureURL;
    }

    public String getPictureURL(){
        return _pictureURL;
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
        if(_title.contains(searchTerm) || _edition.contains(searchTerm) || _publisher.contains(searchTerm)){
            return true;
        }
        return false;
    }

    public DTO createDataTransferObject() {
        return null;
    }
}
