package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.EmployeeDTO;
import at.fhv.team3.domain.interfaces.Transferable;

import javax.persistence.*;
import java.util.HashMap;

/**
 * Created by David on 10/30/2017.
 */
@Entity
@Table(name = "employee")
public class Employee implements Transferable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private int _employeeId;

    @Column(name = "firstName")
    private String _firstName;

    @Column(name = "lastName")
    private String _lastName;

    @Column(name ="role")
    private String _role;

    @Column(name = "userName")
    private String _userName;

    @Column(name = "ou")
    private String _ou;

    public Employee(){}

    public void setEmployeeId(int id){
        _employeeId = id;
    }

    public int getEmployeeId(){
        return _employeeId;
    }

    public void setFirstName(String firstname){
        _firstName = firstname;
    }

    public String getFirstName(){
        return _firstName;
    }

    public void setLastName(String lastname){
        _lastName = lastname;
    }

    public String getLastName(){
        return _lastName;
    }

    public void setRole(String role){
        _role = role;
    }

    public String getRole(){
        return _role;
    }

    public void setUsername(String username){
        _userName = username;
    }

    public String getUsername(){
        return _userName;
    }

    public String getOu(){
        return _ou;
    }

    public void setOu(String ou){
        _ou = ou;
    }

    public DTO createDataTransferObject() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(getEmployeeId());
        employeeDTO.setFirstName(getFirstName());
        employeeDTO.setLastName(getLastName());
        employeeDTO.setRole(getRole());
        employeeDTO.setUsername(getUsername());
        return employeeDTO;
    }

    public void fillFromDTO(DTO dto) {
        HashMap<String, String> allData = dto.getAllData();
        _employeeId = Integer.parseInt(allData.get("id"));
        _firstName = allData.get("firstname");
        _lastName = allData.get("lastname");
        _role = allData.get("role");
        _userName = allData.get("username");
    }
}
