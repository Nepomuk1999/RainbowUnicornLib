package at.fhv.team3.application;

import at.fhv.team3.domain.BookedItem;
import at.fhv.team3.domain.BorrowedItem;
import at.fhv.team3.domain.Message;
import at.fhv.team3.persistence.BookingRepository;
import at.fhv.team3.persistence.BorrowedItemRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by David on 11/27/2017.
 */
public class MessageProducer implements Runnable{

    private MessageConsumer _consumer;
    private static MessageProducer _instance;
    private boolean _run = true;
    private BorrowedItemRepository _borrowRepository;
    private BookingRepository _bookedRepository;

    private MessageProducer(){
        _consumer = MessageConsumer.getInstance();
        _borrowRepository = BorrowedItemRepository.getInstance();
        _bookedRepository = BookingRepository.getInstance();
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
            _consumer.addMessage(m);
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

    public void run() {
        do {
            Date date = new Date();
            int hour = date.getHours();
            int minute = date.getMinutes();
            int second = date.getSeconds();
            if (hour == 0 && minute == 0 && second == 0) {
                produceMessages();
            }
        } while(_run);
    }

    public void stop(){
        _run = false;
    }
}
