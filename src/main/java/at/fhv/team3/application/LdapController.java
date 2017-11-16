package at.fhv.team3.application;

import at.fhv.team3.domain.Employee;
import at.fhv.team3.domain.dto.EmployeeDTO;
import at.fhv.team3.persistence.EmployeeRepository;
import at.fhv.team3.rmi.interfaces.RMILdap;

import javax.naming.*;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

public class LdapController extends UnicastRemoteObject implements RMILdap {

    private String url;
    private Hashtable<String, String> env;
    private EmployeeRepository _employeeRepository;

    public LdapController() throws RemoteException{
        url = "ldap://openldap.fhv.at:389/o=fhv.at";
        env = new Hashtable<String, String>();
        _employeeRepository = EmployeeRepository.getInstance();
    }

    public EmployeeDTO authenticateUser(String name, String password) throws NamingException {
        Employee employee = findEmployee(name);
        EmployeeDTO dto = (EmployeeDTO) employee.createDataTransferObject();
        boolean access = false;

        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, url);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "uid=" + employee.getUsername() + ",ou="+employee.getOu()+",o=fhv.at");
        env.put(Context.SECURITY_CREDENTIALS, password);
        try {
            Context ctx = new InitialContext(env);
            ctx.close();
            access = true;
        } catch (NamingException ex) {
            access = false;
        }
        dto.setLoggedIn(access);
        return dto;
    }

    private Employee findEmployee(String name){
        List<Employee> employees = _employeeRepository.getAll();
        for(Employee employee : employees){
            if(employee.getUsername().equals(name)){
               return employee;
            }
        }
        return new Employee();
    }

}