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
    private int _libId;

    @OneToMany
    private ArrayList<BorrowedItem> borrowedItems = new ArrayList<BorrowedItem>();

    private String _name;
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
}
