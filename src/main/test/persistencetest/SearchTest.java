package persistencetest;

import at.fhv.team3.application.MediaSearchController;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.interfaces.Searchable;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by David on 11/3/2017.
 */

//TODO: Fix rmi exception
public class SearchTest {
    private MediaSearchController _controller;

    public SearchTest(){
        try {
            _controller = new MediaSearchController();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchDasBuch(){
        List<Searchable> result = _controller.searchMedias("Das Buch");
        for(Searchable s : result){
            System.out.println(s.toString());
        }
    }
}
