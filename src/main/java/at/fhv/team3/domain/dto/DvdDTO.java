package at.fhv.team3.domain.dto;

/**
 * Created by David on 10/30/2017.
 */
public class DvdDTO extends DTO{

    private int _dvdId;
    private String _title;
    private String _regisseur;
    private String _pictureURL;
    private String _shelfPos;

    public DvdDTO(int id, String title, String regisseur, String pictureURL, String shelfPos){
        _dvdId = id;
        _title = title;
        _regisseur = regisseur;
        _pictureURL = pictureURL;
        _shelfPos = shelfPos;
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

    public void setId(int id) {
        setDvdId(id);
    }

    public int getId() {
        return getDvdId();
    }
}
