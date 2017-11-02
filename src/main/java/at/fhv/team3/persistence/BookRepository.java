package at.fhv.team3.persistence;

import at.fhv.team3.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Book findBy_media_mediaId(Integer id);

    List<Book> findAll();


}
