package readerblog.mates.readerblog.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import readerblog.mates.readerblog.entities.Author;

@Repository
public interface AuthorAdminRepository extends CrudRepository<Author, Long> {

}