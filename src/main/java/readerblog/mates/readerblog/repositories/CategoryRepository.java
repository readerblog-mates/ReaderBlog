package readerblog.mates.readerblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import readerblog.mates.readerblog.entities.Book;
import readerblog.mates.readerblog.entities.Category;

import java.util.List;

/**
 * @author @ivanleschinsky
 * @author unknown
 */
//TODO fix update method
@Repository
@EnableTransactionManagement
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    void removeById(Long id);

    void removeByName(String name);

    @Query(value = "UPDATE categories set name = :newName where name = :oldName", nativeQuery = true)
    void update(String oldName, String newName);

    List<Category> findAllByBooksIn(List<Book> books);

    List<Category> findAllByIdIn(List<Long> ids);
}
