package at.fhv.team3.rmi.dto;

/**
 * Created by David on 10/30/2017.
 */
public class MagazineDTO {

    private int _magazineId;
    private String _title;
    private String _edition;
    private String _publisher;
    private String _pictureURL;
    private String _shelfPos;

    public MagazineDTO(){

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
}
