package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.DvdDTO;
import at.fhv.team3.domain.interfaces.Borrowable;

import javax.persistence.*;
import java.util.ArrayList;
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

    public void setDvdId(int _dvdId){
        _dvdId = _dvdId;
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

    public void createFromString(String s) {
        ArrayList<String> stringList = new ArrayList<String>();
        for(String word : s.split(" ")) {
            stringList.add(word);
        }

        setDvdId(Integer.parseInt(stringList.get(0)));
        setTitle(stringList.get(1));
        setRegisseur(stringList.get(2));
        setPictureURL(stringList.get(3));
        setShelfPos(stringList.get(4));
    }

    public int getId() {
        return _dvdId;
    }

    public boolean isSameMedia(Borrowable b) {
        Dvd dvd = (Dvd) b;
        if(dvd.getTitle().equals(_title) && dvd.getRegisseur().equals(_regisseur)){
            return true;
        }
        return false;
    }

    public String getMessageString() {
        return "Dvd Title: " + _title + " Regisseur: " + _regisseur;
    }
}
