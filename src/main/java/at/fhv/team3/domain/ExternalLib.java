package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.interfaces.Transferable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by David on 10/30/2017.
 */
@Entity
@Table(name = "externalLib")
public class ExternalLib implements Transferable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "libId")
    private int _libId;

    @Column(name = "name")
    private String _name;

    @Column(name = "accountData")
    private String _accountData;

    public ExternalLib(){

    }

    public void setLibId(int id){
        _libId = id;
    }

    public int getLibId(){
        return _libId;
    }

    public void setName(String name){
        _name = name;
    }

    public String getName(){
        return _name;
    }

    public void setAccountData(String accountData){
        _accountData = accountData;
    }

    public String getAccountData(){
        return _accountData;
    }

    public DTO createDataTransferObject() {
        return null;
    }

    public void fillFromDTO(DTO dto) {
        HashMap<String, String> allData = dto.getAllData();
        _libId = Integer.parseInt(allData.get("id"));
        _name = allData.get("name");
        _accountData = allData.get("accountData");
    }

    public boolean equals(ExternalLib lib){
        if(lib.getAccountData().equals(_accountData) && lib.getLibId() == _libId && lib.getName().equals(_name)){
            return true;
        }
        return false;
    }

    public void createFromString(String s) {
        ArrayList<String> stringList = new ArrayList<String>();
        for(String word : s.split(" ")) {
            stringList.add(word);
        }

        setLibId(Integer.parseInt(stringList.get(0)));
        setName(stringList.get(1));
        setAccountData(stringList.get(2));
    }
}
