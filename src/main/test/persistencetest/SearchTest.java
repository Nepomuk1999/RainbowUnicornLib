package persistenceTest;

import at.fhv.team3.application.MediaSearchController;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.interfaces.Searchable;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by David on 11/3/2017.
 */

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
 //       ArrayList<ArrayList<Searchable>> result = _controller.searchMedias("Harry");
 //       for(ArrayList al : result) {
 //           for (Searchable s : al) {
//                DTO dto = s.createDataTransferObject();
//               HashMap<String, String> data = dto.getAllData();
 //               for (String str : data.values()) {
//                    System.out.print(str + " ");
 //               }
 //               System.out.println("");
//            }
//        }
    }
}
