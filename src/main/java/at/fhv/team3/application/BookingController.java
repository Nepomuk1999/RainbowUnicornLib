package at.fhv.team3.application;

import at.fhv.team3.domain.*;
import at.fhv.team3.domain.dto.*;
import at.fhv.team3.domain.interfaces.Borrowable;
import at.fhv.team3.persistence.BookingRepository;
import at.fhv.team3.persistence.BorrowedItemRepository;
import at.fhv.team3.rmi.interfaces.RMIBooking;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by David on 11/14/2017.
 */
public class BookingController extends UnicastRemoteObject implements RMIBooking{

    private static BookingController currentController;
    private BookingRepository _bookingRepository;
    private BorrowedItemRepository _borrowRepository;

    public BookingController() throws RemoteException{
        _bookingRepository = BookingRepository.getInstance();
        _borrowRepository = BorrowedItemRepository.getInstance();
    }

    public static BookingController getInstance() {
        if (currentController == null) {
            try {
                return new BookingController();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return currentController;
    }
    //Alle Reservierungen als DTOs zurückgeben (Liste von BookedItemDTOs)
    public List<DTO> getAllBookings(){
        List<BookedItem> allBookings = _bookingRepository.getAll();
        List<DTO> dtos = new ArrayList<DTO>();
        for(BookedItem bi : allBookings){
            dtos.add(bi.createDataTransferObject());
        }
        return dtos;
    }

    //Für ein Buch/eine Dvd/ein Magazin überprüfen, ob eine Reservierung vorhanden ist (Liste von BookedItemDTOs)
    public List<DTO> getBookingsForMedia(DTO media){
        List<BookedItem> allBookings = _bookingRepository.getAll();
        List<DTO> matching = new ArrayList<DTO>();
        Borrowable b = null;
        if(media.getClass() == BookDTO.class){
            b = new Book();
            b.fillFromDTO(media);
        } else if(media.getClass() == DvdDTO.class){
           b = new Dvd();
           b.fillFromDTO(media);
        } else if(media.getClass() == MagazineDTO.class){
            b = new Magazine();
            b.fillFromDTO(media);
        }
        if(b != null) {
            Borrowable tmp = null;
            for (BookedItem bi : allBookings) {
                if(bi.getBook() != null){
                    tmp = bi.getBook();
                } else if(bi.getDvd() != null){
                    tmp = bi.getDvd();
                } else if(bi.getMagazine() != null){
                    tmp = bi.getMagazine();
                }
                if(tmp.getClass() == b.getClass()){
                    if(tmp.getId() == b.getId()){
                        matching.add(bi.createDataTransferObject());
                    }
                }
            }
        }
        return matching;
    }

    public void deleteBooking(BookedItem bi){
        _bookingRepository.delete(bi);
    }

    //Reservierung erstellen und in die Datenbank speichern (ValidationResult)
    public ValidationResult bookItem(DTO dto, CustomerDTO customerDto){
        BookedItem bookedItem = new BookedItem();
        Customer customer = new Customer();
        customer.fillFromDTO(customerDto);
        bookedItem.setCustomer(customer);
        bookedItem.setDate(new Date());
        if (dto instanceof BookDTO) {
            Book b = new Book();
            b.fillFromDTO(dto);
            bookedItem.setBook(b);
        } else if (dto instanceof DvdDTO) {
            Dvd d = new Dvd();
            d.fillFromDTO(dto);
            bookedItem.setDvd(d);
        } else {
            Magazine m = new Magazine();
            m.fillFromDTO(dto);
            bookedItem.setMagazine(m);
        }
        ValidationResult vr = validateBooking(bookedItem);
        if(!vr.hasErrors()) {
            _bookingRepository.save(bookedItem);
        }
        return vr;
    }

    //Validierung ob die Reservierung des Mediums möglich ist (ValidationResult)
    private ValidationResult validateBooking(BookedItem bookedItem){
        ValidationResult validationResult = new ValidationResult();
        List<BookedItem> bookedItems = _bookingRepository.getAll();
        List<BorrowedItem> borrowedItems = _borrowRepository.getAll();
            for (BookedItem booked: bookedItems) {
                if (booked.getMedia().getClass() == bookedItem.getMedia().getClass()) {
                    if (booked.getMedia().isSameMedia(bookedItem.getMedia()) && booked.getCustomer().equals(bookedItem.getCustomer())) {
                        validationResult.add("The customer already has this media booked.");
                    }
                }
            }
            for (BorrowedItem borrowed : borrowedItems) {
                if (borrowed.getMedia().getClass() == bookedItem.getMedia().getClass()) {
                    if (borrowed.getMedia().isSameMedia(bookedItem.getMedia()) && borrowed.getCustomer().equals(bookedItem.getCustomer())) {
                        validationResult.add("The customer already has this media borrowed.");
                    }
                }
            }
        return validationResult;
    }
}
