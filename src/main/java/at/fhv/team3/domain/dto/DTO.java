package at.fhv.team3.domain.dto;

import java.util.HashMap;

/**
 * Created by David on 10/31/2017.
 */
public abstract class DTO{

    protected int _id;

    public abstract void setId(int id);

    public abstract int getId();

    public abstract HashMap<String, String> getAllData();
}
