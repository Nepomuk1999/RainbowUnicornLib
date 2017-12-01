package domaindummy;

import at.fhv.team3.domain.Book;

public class BookDummy extends Book {


    public BookDummy(int id, String title, String author, String publisher, String isbn, String edition, String shelfPos) {
        super(title, author, publisher, isbn, edition, shelfPos);
        this.setBookId(id);
    }
}
