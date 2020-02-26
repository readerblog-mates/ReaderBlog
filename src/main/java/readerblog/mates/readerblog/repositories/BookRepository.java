package readerblog.mates.readerblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import readerblog.mates.readerblog.entities.Author;
import readerblog.mates.readerblog.entities.Book;
import readerblog.mates.readerblog.entities.Category;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByGenreId(Long genreId);
    List<Book> findAllByCategoriesIn(List<Category> categories);
    List<Book> findAllByGenreIdAndCategoriesIn(Long genreId, List<Category> categories);
    List<Book> findAllByAuthorsIn(List<Author> authors);
    List<Book> findAllByPages(Integer pages);
    List<Book> findAllByPublisher(String publisher);
    List<Book> findAllByYearOfWriting(Integer yeraOfWriting);
    List<Book> findAllByFormat(String format);
    List<Book> findAllByTitle(String title);
    List<Book> findAllByOriginLanguage(String language);
    void removeById(Long id);
    List<Book> findAllByIdIn(List<Long> ids);
    List<Book> findAllByRating(Double rating);
    List<Book> findAllByRatingBetween(Double minRating, Double maxRating);
}
