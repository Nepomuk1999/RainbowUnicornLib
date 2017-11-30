package at.fhv.team3.application;

import at.fhv.team3.domain.dto.MessageDTO;
import at.fhv.team3.domain.Message;
import at.fhv.team3.rmi.interfaces.RMIMessageConsumer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by David on 11/24/2017.
 */
public class MessageConsumer extends UnicastRemoteObject implements RMIMessageConsumer {

    private static MessageConsumer _instance;

    private MessageConsumer() throws RemoteException{

    }

    public static MessageConsumer getInstance(){
        if(_instance == null){
            try {
                _instance = new MessageConsumer();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return _instance;
    }

    public int getMessageCount(){
        return MessageProducer.getInstance().getMessageCount();
    }

    public void addMessage(Message m){
        MessageProducer.getInstance().addMessage(m);
    }

    public MessageDTO pull(){
        return MessageProducer.getInstance().pull();
    }

}
