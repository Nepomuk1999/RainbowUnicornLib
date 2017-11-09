package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.DvdDTO;
import at.fhv.team3.domain.interfaces.Borrowable;

import javax.persistence.*;
import java.util.HashMap;
import java.util.regex.Pattern;

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

    @Column(name = "title")
    private String _title;

    @Column(name = "regisseur")
    private String _regisseur;

    @Column(name = "pictureURL")
    private String _pictureURL;

    @Column(name = "shelfPos")
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

    public boolean containsSearchTerm(String searchTerm) {
        if( Pattern.compile(Pattern.quote(searchTerm), Pattern.CASE_INSENSITIVE).matcher(_title).find()
                || Pattern.compile(Pattern.quote(searchTerm), Pattern.CASE_INSENSITIVE).matcher(_regisseur).find()) {
            return true;
        }
        return false;
    }

    public DTO createDataTransferObject() {
        return new DvdDTO(_dvdId, _title, _regisseur, _pictureURL, _shelfPos);
    }

    public void fillFromDTO(DTO dto) {
        HashMap<String, String> allData = dto.getAllData();
        _dvdId = Integer.parseInt(allData.get("id"));
        _title = allData.get("title");
        _regisseur = allData.get("regisseur");
        _pictureURL = allData.get("pictureURL");
        _shelfPos = allData.get("shelfPos");
    }

    public int getId() {
        return _dvdId;
    }
}
