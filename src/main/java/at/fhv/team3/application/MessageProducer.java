package at.fhv.team3.application;

import at.fhv.team3.domain.BookedItem;
import at.fhv.team3.domain.BorrowedItem;
import at.fhv.team3.domain.Message;
import at.fhv.team3.domain.dto.MessageDTO;
import at.fhv.team3.persistence.BookingRepository;
import at.fhv.team3.persistence.BorrowedItemRepository;

import java.util.*;

/**
 * Created by David on 11/27/2017.
 */
public class MessageProducer implements Runnable{

    private static MessageProducer _instance;
    private boolean _run = true;
    private BorrowedItemRepository _borrowRepository;
    private BookingRepository _bookedRepository;
    private List<Message> _messages;

    private MessageProducer(){
        _borrowRepository = BorrowedItemRepository.getInstance();
        _bookedRepository = BookingRepository.getInstance();
        _messages = new LinkedList<Message>();
    }

    public static MessageProducer getInstance(){
        if(_instance == null){
            _instance = new MessageProducer();
        }
        return _instance;
    }

    public void produceMessages(){
        List<Message> messages = new ArrayList<Message>();

        messages.addAll(getBorrowingMessages());
        messages.addAll(getBookingMessages());

        for(Message m : messages){
            _messages.add(m);
        }
    }

    //TODO: implement
    private List<Message> getBorrowingMessages(){
        List<BorrowedItem> borrowedItems = _borrowRepository.getAll();
        List<Message> messages = new ArrayList<Message>();
        for(BorrowedItem bi : borrowedItems){
            
        }
        return messages;
    }

    //TODO: implement
    private List<Message> getBookingMessages(){
        List<BookedItem> bookedItems = _bookedRepository.getAll();
        List<Message> messages = new ArrayList<Message>();
        for(BookedItem bi : bookedItems){

        }
        return messages;
    }

    public int getMessageCount(){
        return _messages.size();
    }

    public void addMessage(Message m){
        Logger.log("Message added to queue: " + m.getMessage() + " at " + new Date().toString());
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

    public void run() {
        produceMessages();
        while(_run) {
            Date date = new Date();
            int hour = date.getHours();
            int minute = date.getMinutes();
            int second = date.getSeconds();
            if (hour == 0 && minute == 0 && second == 0) {
                Logger.getInstance().init();
                produceMessages();
            }
        }
    }

    public void stop(){
        _run = false;
    }
}
