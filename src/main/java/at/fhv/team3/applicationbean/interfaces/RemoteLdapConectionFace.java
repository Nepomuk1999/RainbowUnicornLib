package at.fhv.team3.applicationbean.interfaces;

import at.fhv.team3.domain.Employee;
import at.fhv.team3.domain.dto.EmployeeDTO;
import at.fhv.team3.domain.dto.KeyDTO;

import javax.ejb.Remote;
import javax.naming.NamingException;
import java.io.Serializable;

@Remote
public interface RemoteLdapConectionFace extends Serializable {

    public EmployeeDTO authenticateUser(String name, String password) throws NamingException;

    public KeyDTO getPublicKey();
}
