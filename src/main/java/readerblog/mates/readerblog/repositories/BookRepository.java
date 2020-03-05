package readerblog.mates.readerblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import readerblog.mates.readerblog.entities.Book;
import readerblog.mates.readerblog.entities.Category;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    List<Book> findAllByPagesBetween(Integer firstPage, Integer lastPage);
    List<Book> findAllByPublisherIn(List<String> publishers);
    List<Book> findAllByYearOfWritingIn(List<Integer> yearOfWriting);
    List<Book> findAllByFormatIn(List<String> formats);
    List<Book> findAllByTitle(String title);
    List<Book> findAllByOriginLanguageIn(List<String> languages);
    Book removeById(Long id);
    List<Book> findAllByRatingBetween(Double minRating, Double maxRating);
}
