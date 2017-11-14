import at.fhv.team3.application.ServerBind;
import at.fhv.team3.domain.Customer;
import at.fhv.team3.persistence.CustomerRepository;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ServerBind.init();
   //     test();
    }

    public static void test(){
        List<Customer> customer = CustomerRepository.getInstance().getAll();
        for(Customer c : customer){
            System.out.println(c.getFirstName());
        }
    }
}
