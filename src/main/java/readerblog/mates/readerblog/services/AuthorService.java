package readerblog.mates.readerblog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import readerblog.mates.readerblog.entities.Author;
import readerblog.mates.readerblog.entities.Book;

import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Service
public interface AuthorService {
    Author save(Author author);
    Author findOne(Long id);
    List<Author> findAll();
    List<Author> findByRating(Double ratingMin, Double ratingMax);
    List<Author> findByName(String firstName, String lastName, String patronymicName);
    List<Author> findByGenre(Long genreId);
    List<Author> findByCategory(Long categoryId);
    List<Long> findIdByGenre(Long genreId);
    List<Long> findIdByCategory(Long categoryId);
    List<Author> findByCategoryAndGenre(Long categoryId, Long genreId);
    Page<Author> findAllByPagingAndFiltering(Specification<Author> specification, Pageable pageable);
    Author remove(Long authorId);
    List<Author> findAllByOrderByLastName();
    List<Author> findAllByOrderByFirstName();
    Boolean updateRating(Long id, Double rating);
    List<Author> findByLastNameFirstLetter(String firstLetters);
    List<Author> findByFirstNameFirstLetter(String firstLetters);
    List<Author> saveAll(List<Author> authors);
}
