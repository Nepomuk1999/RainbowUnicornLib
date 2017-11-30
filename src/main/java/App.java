import at.fhv.team3.application.Logger;
import at.fhv.team3.application.MessageProducer;
import at.fhv.team3.rmi.ServerBind;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ServerBind.init();
        Logger.getInstance().init();
        Thread t = new Thread(MessageProducer.getInstance());
        t.run();
    }

}
