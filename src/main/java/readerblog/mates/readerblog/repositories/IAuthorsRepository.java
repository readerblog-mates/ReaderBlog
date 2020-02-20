/*
  @author haroldEkb@mail.ru
 */

package readerblog.mates.readerblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import readerblog.mates.readerblog.entities.Author;

import java.util.List;

@Repository
public interface IAuthorsRepository extends JpaRepository<Author, Long> {
    List<Author> findByLastNameStartingWith(Character firstLetter);
    List<Author> findAllByOrderByLastName();
}
