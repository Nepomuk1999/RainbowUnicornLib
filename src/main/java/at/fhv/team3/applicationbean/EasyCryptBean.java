package at.fhv.team3.applicationbean;

import at.fhv.team3.application.EasyCrypt;
import at.fhv.team3.application.LdapController;
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
        try {
            _easyCrypt = new EasyCrypt(LdapController.getInstance().getPrivateKey(), "RSA");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String encrypt(String text) throws Exception {
        return _easyCrypt.encrypt(text);
    }

    @Override
    public String decrypt(String geheim) throws Exception {
        return _easyCrypt.decrypt(geheim);
    }

    @Override
    public Key getKey() {
        return _easyCrypt.getKey();
    }

    @Override
    public void setKey(Key key){
        _easyCrypt.setKey(key);
    }

    @Override
    public String getVerfahren() {
        return _easyCrypt.getVerfahren();
    }

    @Override
    public void setVerfahren(String verfahren) {
        _easyCrypt.setVerfahren(verfahren);
    }
}
