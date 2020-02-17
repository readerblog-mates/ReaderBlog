package readerblog.mates.readerblog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import readerblog.mates.readerblog.entities.Author;
import readerblog.mates.readerblog.entities.Book;

import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {

    @Query(value = "select * from authors a left join authors_books ab on ab.author_id = a.id where ab.book_id in " +
            "(select b.id from books b where b.category_id = :categoryId and b.genre_id = :genreId)", nativeQuery = true)
    List<Author> findByGenreAndCategory(@Param("genreId") Long genreId, @Param("categoryId") Long categoryId);

    @Query(value = "select * from authors a left join authors_books ab on ab.author_id = a.id where ab.book_id in " +
            "(select b.id from books b where b.genre_id = :genreId)", nativeQuery = true)
    List<Author> findByGenre(@Param("genreId") Long genreId);

    @Query(value = "select * from authors a left join authors_books ab on ab.author_id = a.id where ab.book_id in " +
            "(select b.id from books b where b.category_id = :categoryId)", nativeQuery = true)
    List<Author> findByCategory(@Param("categoryId") Long categoryId);

    @Query(value = "select a.id from authors a left join authors_books ab on ab.author_id = a.id where ab.book_id in " +
            "(select b.id from books b where b.genre_id = :genreId)", nativeQuery = true)
    List<Long> findIdByGenre(@Param("genreId") Long genreId);

    @Query(value = "select a.id from authors a left join authors_books ab on ab.author_id = a.id where ab.book_id in " +
            "(select b.id from books b where b.category_id = :categoryId)", nativeQuery = true)
    List<Long> findIdByCategory(@Param("categoryId") Long categoryId);

    List<Author> findByRating(Double rating);

    List<Author> findByFirstNameAndLastNameAndPatronymicName(String firstName, String lastName, String patronymicName);

    List<Author> findByFirstNameAndLastName(String firstName, String lastName);

    List<Author> findByFirstNameAndPatronymicName(String firstName, String patronymicName);

    List<Author> findByLastNameAndPatronymicName(String lastName, String patronymicName);

    List<Author> findByFirstName(String firstName);

    List<Author> findByLastName(String lastName);

    List<Author> findByPatronymicName(String patronymicName);

    List<Author> findAllByBooks(List<Book> books);

    void removeById(Long id);
}
