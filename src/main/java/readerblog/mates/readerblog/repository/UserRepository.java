package readerblog.mates.readerblog.repository;

/**
 * @author Sergey Petukhov
 */

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import readerblog.mates.readerblog.entities.User;
import readerblog.mates.readerblog.enums.StatusOfUser;

public interface UserRepository extends CrudRepository<User,Long> {
    // методы save и deleteById есть в CrudRepository по умолчанию
    void deleteByEmail(String email);
}
