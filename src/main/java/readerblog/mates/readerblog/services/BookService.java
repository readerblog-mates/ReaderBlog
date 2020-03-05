package readerblog.mates.readerblog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import readerblog.mates.readerblog.entities.Author;
import readerblog.mates.readerblog.entities.Book;

import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Service
public interface BookService {
    List<Book> findByGenre(Long genreId);
    List<Book> findByCategory(Long categoryId);
    List<Book> findByCategoryAndGenre(Long categoryId, Long genreId);
    List<Book> findAll();
    Book remove(Long id);
    Book save(Book book);
    List<Book> saveAll(List<Book> books);
    List<Book> findByTitle(String title);
    List<Book> findByPagesBetween(Integer firstPage, Integer lastPage);
    List<Book> findByFormat(List<String> formats);
    List<Book> findByYear(List<Integer> yearOfWriting);
    List<Book> findByAuthors(List<Long> authorIds);
    List<Book> findByLanguage(List<String> originLanguages);
    List<Book> findByPublisher(List<String> publishers);
    List<Book> findByRating(Double ratingMin, Double ratingMax);
    Boolean updateRating(Long id, Double rating);
    Book findOne(Long id);
    Page<Book> findAllByPagingAndFiltering(Specification<Book> specification, Pageable pageable);
}
