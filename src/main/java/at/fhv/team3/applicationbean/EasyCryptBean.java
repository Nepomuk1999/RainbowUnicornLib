package at.fhv.team3.applicationbean;

import at.fhv.team3.application.EasyCrypt;
import at.fhv.team3.applicationbean.interfaces.RemoteEasyCryptBeanFace;

import javax.ejb.Stateless;
import java.security.Key;

/**
 * Created by ClemensB on 03.12.17.
 */

@Stateless(mappedName = "EasyCryptEJB")
public class EasyCryptBean implements RemoteEasyCryptBeanFace {

    private EasyCrypt _easyCrypt;

    public EasyCryptBean(){

    }
    public EasyCryptBean(Key k, String verfahren){
        try {
            _easyCrypt = new EasyCrypt(k, verfahren);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String text) throws Exception {
        return _easyCrypt.encrypt(text);
    }

    public String decrypt(String geheim) throws Exception {
        return _easyCrypt.decrypt(geheim);
    }

    public Key getKey() {
        return _easyCrypt.getKey();
    }

    public void setKey(Key key){
        _easyCrypt.setKey(key);
    }

    public String getVerfahren() {
        return _easyCrypt.getVerfahren();
    }

    public void setVerfahren(String verfahren) {
        _easyCrypt.setVerfahren(verfahren);
    }
}
