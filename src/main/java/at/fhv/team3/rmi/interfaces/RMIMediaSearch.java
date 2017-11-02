package at.fhv.team3.rmi.interfaces;

import at.fhv.team3.domain.dto.DTO;

import java.util.ArrayList;

/**
 * Created by David on 11/2/2017.
 */
public interface RMIMediaSearch {

    public ArrayList<DTO> search(String searchTerm);
}
