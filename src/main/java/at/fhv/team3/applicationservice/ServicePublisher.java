package at.fhv.team3.applicationservice;

import javax.xml.ws.Endpoint;

/**
 * Created by David on 12/12/2017.
 */
public class ServicePublisher {

    public void publishSerives(){
        Endpoint.publish("http://localhost:8888/MediaSearch", new MediaSearchService());
    }
}
