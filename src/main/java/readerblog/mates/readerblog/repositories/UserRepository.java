package readerblog.mates.readerblog.repositories;

/**
 * @author Sergey Petukhov
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import readerblog.mates.readerblog.entities.Role;
import readerblog.mates.readerblog.entities.User;
import readerblog.mates.readerblog.enums.StatusOfUser;

import java.util.List;
import java.util.Optional;
/**
 * @author Sergey Petukhov
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // методы save и deleteById есть в CrudRepository по умолчанию

    void deleteByEmail(String email);

    boolean existsByEmail(String email);

    User findByEmail(String email);

    List<User> findByRoles(List<Role> roles);

    User findByFirstNameAndLastName(String firstName, String lastName);

    User findByNickName(String nickName);

    List<User> findByStatus(StatusOfUser status);
}
