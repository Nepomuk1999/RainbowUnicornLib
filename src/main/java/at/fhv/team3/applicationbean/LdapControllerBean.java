package at.fhv.team3.applicationbean;

import at.fhv.team3.application.LdapController;
import at.fhv.team3.applicationbean.interfaces.RemoteLdapConnectionFace;
import at.fhv.team3.domain.dto.EmployeeDTO;
import at.fhv.team3.domain.dto.KeyDTO;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import java.rmi.RemoteException;

@Stateless
public class LdapControllerBean implements RemoteLdapConnectionFace {

    private LdapController _ldapController;

    public LdapControllerBean(){
        try {
            _ldapController = new LdapController();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public EmployeeDTO authenticateUser(String name, String password) throws NamingException {
        return _ldapController.authenticateUser(name, password);
    }

    public KeyDTO getPublicKey() {
        return _ldapController.getPublicKey();
    }
}
