package at.fhv.team3.application;

import at.fhv.team3.domain.Employee;
import at.fhv.team3.domain.dto.EmployeeDTO;
import at.fhv.team3.domain.dto.KeyDTO;
import at.fhv.team3.persistence.EmployeeRepository;
import at.fhv.team3.rmi.interfaces.RMILdap;

import javax.naming.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class LdapController extends UnicastRemoteObject implements RMILdap {

    private static LdapController currentController;
    private String url;
    private Hashtable<String, String> env;
    private EmployeeRepository _employeeRepository;
    private KeyPair rsakeys = generateRandomKey();

    public LdapController() throws RemoteException{
        url = "ldap://openldap.fhv.at:389/o=fhv.at";
        env = new Hashtable<String, String>();
        _employeeRepository = EmployeeRepository.getInstance();
    }

    public static LdapController getInstance() {
        if (currentController == null) {
            try {
                return new LdapController();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return currentController;
    }

    //Login authentifizierung (EmployeeDTO)
    public EmployeeDTO authenticateUser(String name, String password) throws NamingException {
        EasyCrypt ecPri;
        String decryptUsername = "";
        String decryptPassword = "";
        //System.out.println(name + " " + password);
        try {
            ecPri = new EasyCrypt(rsakeys.getPrivate(), "RSA");
            decryptUsername = ecPri.decrypt(name);
            decryptPassword = ecPri.decrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(decryptUsername + " " + decryptPassword);
        Employee employee = findEmployee(decryptUsername);
        EmployeeDTO dto = (EmployeeDTO) employee.createDataTransferObject();
        boolean access = false;

        System.out.println(decryptUsername);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, url);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "uid=" + employee.getUsername() + ",ou="+employee.getOu()+",o=fhv.at");
        env.put(Context.SECURITY_CREDENTIALS, decryptPassword);
        try {
            Context ctx = new InitialContext(env);
            ctx.close();
            access = true;
        } catch (NamingException ex) {
            access = false;
        }
        dto.setLoggedIn(access);
        if(access){
            Logger.log("User " + dto.getUsername() + " logged in at " + new Date().toString());
        }
        return dto;
    }

    //Einen Mitarbeiter aus der Datenbank suchen (Employee)
    private Employee findEmployee(String name){
        List<Employee> employees = _employeeRepository.getAll();
        for(Employee employee : employees){
            if(employee.getUsername().equals(name)){
               return employee;
            }
        }
        return new Employee();
    }

    //RSA-Schlüssel für Verschlüsselung generieren (KeyPair)
    private static KeyPair generateRandomKey(){
        KeyPairGenerator keygen = null;
        try {
            keygen = KeyPairGenerator.getInstance("RSA");
            keygen.initialize(1024);
            KeyPair rsaKeys = keygen.genKeyPair();
            return rsaKeys;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Den Public-Key aus den RSA-Schlüsseln holen und als DTO für die Übertragung zum Client zurückgeben (KeyDTO)
    public KeyDTO getPublicKey(){
        KeyDTO keydto = new KeyDTO();
        keydto.setPublicKey(rsakeys.getPublic());
        return keydto;
    }

}