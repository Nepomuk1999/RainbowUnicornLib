import at.fhv.team3.application.Logger;
import at.fhv.team3.application.MessageProducer;
import at.fhv.team3.rmi.ServerBind;
import at.fhv.team3.domain.Customer;
import at.fhv.team3.persistence.CustomerRepository;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ServerBind.init();
        Thread t = new Thread(MessageProducer.getInstance());
        t.run();
    }

}
