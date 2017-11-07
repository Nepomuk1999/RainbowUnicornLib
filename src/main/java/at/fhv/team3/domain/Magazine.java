package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.MagazineDTO;
import at.fhv.team3.domain.interfaces.Borrowable;

import javax.persistence.*;
import java.util.regex.Pattern;

/**
 * Created by David on 10/30/2017.
 */
@Entity
@Table(name = "magazine")
public class Magazine implements Borrowable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "magazineId")
    private int _magazineId;

    @Column(name = "title")
    private String _title;

    @Column(name = "edition")
    private String _edition;

    @Column(name = "publisher")
    private String _publisher;

    @Column(name = "pictureURL")
    private String _pictureURL;

    @Column(name = "shelfPos")
    private String _shelfPos;

    public Magazine(){}

    public void set_magazineId(int id){
        _magazineId = id;
    }

    public int get_magazineId(){
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

    public boolean containsSearchTerm(String searchTerm) {
        if( Pattern.compile(Pattern.quote(searchTerm), Pattern.CASE_INSENSITIVE).matcher(_title).find()
                || Pattern.compile(Pattern.quote(searchTerm), Pattern.CASE_INSENSITIVE).matcher(_edition).find()
                || Pattern.compile(Pattern.quote(searchTerm), Pattern.CASE_INSENSITIVE).matcher(_publisher).find()) {
            return true;
        }
        return false;
    }

    public DTO createDataTransferObject() {
        return new MagazineDTO(_magazineId, _title, _edition, _publisher, _pictureURL, _shelfPos);
    }
}
