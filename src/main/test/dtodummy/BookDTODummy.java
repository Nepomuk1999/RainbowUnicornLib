package dtodummy;

import at.fhv.team3.domain.dto.BookDTO;

public class BookDTODummy extends BookDTO {

    public BookDTODummy(int id, String title, String publisher, String author, String isbn, String edition, String pictureURL, String shelfPos) {
        super(id, title, publisher, author, isbn, edition, pictureURL, shelfPos);
    }
}
