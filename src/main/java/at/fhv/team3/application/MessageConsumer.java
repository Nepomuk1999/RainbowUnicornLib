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

    private List<Message> _messages;
    private static MessageConsumer _instance;

    private MessageConsumer() throws RemoteException{
        if(_messages == null) {
            _messages = new LinkedList<Message>();
        }
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
        return _messages.size();
    }

    public void addMessage(Message m){
        _messages.add(m);
    }

    public MessageDTO pull(){
        MessageDTO m = null;
        if(_messages != null && !_messages.isEmpty()) {
            Message message = _messages.get(0);
            m = (MessageDTO) message.createDataTransferObject();
            _messages.remove(message);
        } else {
            m.setMessage("No Messages found.");
        }
        return m;
    }

}
