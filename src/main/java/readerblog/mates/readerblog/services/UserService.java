package readerblog.mates.readerblog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import readerblog.mates.readerblog.entities.Author;
import readerblog.mates.readerblog.entities.Book;
import readerblog.mates.readerblog.entities.Role;
import readerblog.mates.readerblog.entities.User;
import readerblog.mates.readerblog.enums.StatusOfUser;

import java.util.List;

/**
 * @author Sergey Petukhov
 */
public interface UserService {

    void deleteByEmail(String email);

    void deleteById(Long id);

    boolean existsByEmail(String email);

    User save (User user);

    User findOneById(Long id);

    List<User> findAll();

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    User findAllByEmail(String email);

    User findByNickName(String nickName);

    List<User> findByStatus(StatusOfUser status);
    User findByEmail(String email);

}
