package readerblog.mates.readerblog.repositories;

/**
 * @author Sergey Petukhov
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import readerblog.mates.readerblog.entities.User;
import readerblog.mates.readerblog.enums.StatusOfUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // методы save и deleteById есть в CrudRepository по умолчанию

    void deleteByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
