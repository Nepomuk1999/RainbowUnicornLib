package at.fhv.team3.applicationbean.interfaces;

import java.security.Key;

/**
 * Created by ClemensB on 03.12.17.
 */
public interface RemoteEasyCryptBeanFace {

    public String encrypt(String text) throws Exception;

    public String decrypt(String geheim) throws Exception;

    public Key getKey();

    public void setKey(Key key);

    public String getVerfahren();

    public void setVerfahren(String verfahren);
}
