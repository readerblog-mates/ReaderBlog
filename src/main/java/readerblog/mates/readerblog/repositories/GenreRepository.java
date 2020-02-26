package readerblog.mates.readerblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import readerblog.mates.readerblog.entities.Book;
import readerblog.mates.readerblog.entities.Genre;

import java.util.List;

/**
 * @author @ivanleschinsky
 * @author unknown
 */
//TODO fix update method
@Repository
@EnableTransactionManagement
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);

    void removeById(Long id);

    void removeByName(String name);

    @Query(value = "UPDATE genres set name = :newName where name = :oldName", nativeQuery = true)
    void update(String oldName, String newName);

    List<Genre> findAllByBooksIn(List<Book> books);
}
