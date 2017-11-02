package at.fhv.team3.domain.dto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by David on 10/31/2017.
 */
public abstract class DTO{

    protected int _id;

    protected DTO() throws RemoteException {
    }

    public abstract void setId(int id);

    public abstract int getId();
}
