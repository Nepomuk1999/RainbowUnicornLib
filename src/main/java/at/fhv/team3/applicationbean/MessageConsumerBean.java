package at.fhv.team3.applicationbean;

import at.fhv.team3.application.MessageConsumer;
import at.fhv.team3.applicationbean.interfaces.RemoteMessageConsumerBeanFace;
import at.fhv.team3.domain.dto.MessageDTO;

import javax.ejb.Stateless;
import java.rmi.RemoteException;

/**
 * Created by ClemensB on 03.12.17.
 */

@Stateless
public class MessageConsumerBean implements RemoteMessageConsumerBeanFace{

    private MessageConsumer _messageConsumer= MessageConsumer.getInstance();;

    public int getMessageCount(){
        return _messageConsumer.getMessageCount();
    }

    public MessageDTO pull(){
        return _messageConsumer.pull();
    }
}
