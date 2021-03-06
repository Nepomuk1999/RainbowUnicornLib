package at.fhv.team3.domain.dto;

import at.fhv.team3.domain.Customer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by David on 10/30/2017.
 */
public class BookedItemDTO extends DTO {

    private int _bookingId;
    private CustomerDTO _customer;
    private Date _date;
    private BookDTO _book;
    private DvdDTO _dvd;
    private MagazineDTO _magazine;
    private String name;
    private String start;

    public BookedItemDTO(int id, CustomerDTO customer, Date date, DTO dto) {
        _bookingId = id;
        _customer = customer;
        _date = date;
        if (dto instanceof BookDTO) {
            _book = (BookDTO) dto;
        } else if (dto instanceof DvdDTO) {
            _dvd = (DvdDTO) dto;
        } else {
            _magazine = (MagazineDTO) dto;
        }
    }

    public void setBookingId(int id){
        _bookingId = id;
    }

    public int getBookingId(){
        return _bookingId;
    }

    public void setCustomer(CustomerDTO customer){
        _customer = customer;
    }

    public CustomerDTO getCustomer(){
        return _customer;
    }

    public void setDate(Date date){
        _date = date;
    }

    public Date getdate(){
        return _date;
    }

    public void setId(int id) {
        setBookingId(id);
    }

    public int getId() {
        return getBookingId();
    }

    public void setBook(BookDTO book){
        _book = book;
    }

    public BookDTO getBook(){
        return _book;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return _customer.getFirstName() + " " + _customer.getLastName();
    }

    public void setStart(Date date){
        start = date.toString();
    }

    public String getStart(){
        return _date.toString();
    }

    public void setDvd(DvdDTO dvd){
        _dvd = dvd;
    }

    public DvdDTO getDvD(){ return _dvd;}

    public void setMagazine(MagazineDTO magazine){
        _magazine = magazine;
    }

    public MagazineDTO getMagazine(){
        return _magazine;
    }

    public HashMap<String, String> getAllData() {
        HashMap<String, String> allData = new HashMap<String, String>();
        allData.put("id", ""+_bookingId);
        allData.put("customer", _customer.getFirstName() + " " + _customer.getLastName());

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM HH:mm:ss z YYYY");
        allData.put("date", sdf.format(_date));

        if (_book != null) {
            allData.put("book", _book.toString());
        } else if (_dvd != null) {
            allData.put("dvd", _dvd.toString());
        } else if (_magazine != null) {
            allData.put("magazine", _magazine.toString());
        } else {
            return null;
        }
        return allData;
    }

    public boolean equals(DTO dto) {
        HashMap<String, String> data = dto.getAllData();
        if (data.get("customer").equals(_customer.getFirstName() + " " + _customer.getLastName()) && data.get("date").equals(_date.toString())) {
            return true;
        }
        return false;
    }

    public String toString() {
        HashMap<String, String> map = getAllData();
        StringBuilder sb = new StringBuilder();
        sb.append(map.get("id") + " ");
        sb.append(map.get("customer") + " ");
        sb.append(map.get("date") + " ");

        if (map.containsKey("book")) {
            sb.append(map.get("book"));
        } else if (map.containsKey("dvd")) {
            sb.append(map.get("dvd"));
        } else if (map.containsKey("magazine")) {
            sb.append(map.get("magazine"));
        } else {
            sb.append("ITEM CAN`T BE READ PROPPERLY CLASS BOOKEDITEMDTO METHODE: TOSTRING");
        }

        return sb.toString();
    }
}
