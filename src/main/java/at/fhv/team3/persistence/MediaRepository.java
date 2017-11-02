package at.fhv.team3.persistence;

import at.fhv.team3.domain.Media;
import at.fhv.team3.domain.MediaType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

//http://blog.netgloo.com/2014/10/27/using-mysql-in-spring-boot-via-spring-data-jpa-and-hibernate/ <--TUTORIAL

@Transactional
public interface MediaRepository extends CrudRepository<Media, Integer> {

    Media findMediaBy_mediaId(Integer id);

    List<Media> findAll(List<Media> medias);

    List<Media> findAllBy_type(MediaType mediaType);

    List<Media> findAllBy_returnDate(Date date);

}
