package at.fhv.team3.applicationbean;

import at.fhv.team3.application.LdapController;
import at.fhv.team3.applicationbean.interfaces.RemoteLdapConectionFace;
import at.fhv.team3.domain.dto.EmployeeDTO;
import at.fhv.team3.domain.dto.KeyDTO;

import javax.ejb.Stateful;
import javax.naming.NamingException;
import java.rmi.RemoteException;

@Stateful
public class LdapControllerBean implements RemoteLdapConectionFace {

    LdapController controller = LdapController.getInstance();


    public EmployeeDTO authenticateUser(String name, String password) throws NamingException {
        return controller.authenticateUser(name, password);
    }

    public KeyDTO getPublicKey() {
        return controller.getPublicKey();
    }
}
