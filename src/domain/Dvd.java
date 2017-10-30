package domain;

/**
 * Created by David on 10/30/2017.
 */
public class Dvd {

    private int _dvdId;
    private String _title;
    private String _regisseur;
    private String _pictureURL;

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
}
