package at.fhv.team3.applicationbean.interfaces;

import at.fhv.team3.domain.Employee;
import at.fhv.team3.domain.dto.EmployeeDTO;
import at.fhv.team3.domain.dto.KeyDTO;

import javax.naming.NamingException;

public interface RemoteLdapConectionFace {

    public EmployeeDTO authenticateUser(String name, String password) throws NamingException;

    public KeyDTO getPublicKey();
}
