package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.DvdDTO;
import at.fhv.team3.domain.interfaces.Borrowable;

import javax.persistence.*;

/**
 * Created by David on 10/30/2017.
 */
@Entity
@Table(name = "dvd")
public class Dvd implements Borrowable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dvdId")
    private int _dvdId;

    @ManyToOne
    @JoinColumn(name = "media_mediaId")
    private Media media;

    private String _title;
    private String _regisseur;
    private String _pictureURL;
    private String _shelfPos;

    public Dvd(){

    }

    public void set_dvdId(int _dvdId){
        _dvdId = _dvdId;
    }

    public int get_dvdId(){
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

    public Media getMedia_mediaId() {
        return media;
    }

    public void setMedia_mediaId(Media media) {
        this.media = media;
    }

    public boolean containsSearchTerm(String searchTerm) {
        if(_title.contains(searchTerm) || _regisseur.contains(searchTerm)){
            return true;
        }
        return false;
    }

    public DTO createDataTransferObject() {
        return new DvdDTO(_dvdId, _title, _regisseur, _pictureURL, _shelfPos);
    }
}
