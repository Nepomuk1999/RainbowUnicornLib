package rmi.dto;

/**
 * Created by David on 10/30/2017.
 */
public class ExternalLibDTO {

    private int _libId;
    private String _name;
    private String _accountData;

    public ExternalLibDTO(){

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
}
