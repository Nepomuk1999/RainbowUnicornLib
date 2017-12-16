package at.fhv.team3.applicationservice;

import javax.xml.ws.Endpoint;

/**
 * Created by David on 12/12/2017.
 */
public class ServicePublisher {

    public static void publishSerivces(){
        //TODO: Remote (?)
        Endpoint.publish("http://localhost:8888/MediaSearch", new MediaSearchService());
        System.out.println("Published service: Media Search");
    }
}
