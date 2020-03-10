package readerblog.mates.readerblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import readerblog.mates.readerblog.entities.Genre;

/**
 * @author @ivanleschinsky
 * @author unknown
 */
//TODO fix update method
@Repository
@EnableTransactionManagement
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);

    Genre removeById(Long id);

    Genre removeByName(String name);

    @Query(value = "UPDATE genres set name = :newName where name = :oldName", nativeQuery = true)
    Integer update(String oldName, String newName);
}
