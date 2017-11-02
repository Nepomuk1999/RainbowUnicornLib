package persistenceTest;


import at.fhv.team3.application.MediaSearchController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.rmi.RemoteException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseTest {


    MediaSearchController mediaSearchController = new MediaSearchController();

    public DatabaseTest() throws RemoteException {
    }

    @Test
    public void TestDatabaseConnection() {

    }
}
