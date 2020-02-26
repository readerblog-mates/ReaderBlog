package readerblog.mates.readerblog.services;

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
    void remove(Long id);
    Book save(Book book);
    List<Book> saveAll(List<Book> books);
    List<Book> findByTitle(String title);
    List<Book> findByPages(Integer pages);
    List<Book> findByFormat(String format);
    List<Book> findByYear(Integer yearOfWriting);
    List<Book> findByAuthors(List<Long> authorIds);
    List<Book> findByLanguage(String originLanguage);
    List<Book> findByPublisher(String publisher);
    List<Book> findByRating(Double rating);
    List<Book> findByCategories(List<Long> categoryIds);
    List<Book> findByRating(Double ratingMin, Double ratingMax);
    List<Book> findAllById(List<Long> ids);
}
