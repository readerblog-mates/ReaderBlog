package readerblog.mates.readerblog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import readerblog.mates.readerblog.entities.Role;
import readerblog.mates.readerblog.entities.User;
import readerblog.mates.readerblog.enums.StatusOfUser;
import readerblog.mates.readerblog.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Sergey Petukhov
 */

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteByEmail(String email) {
        repository.deleteByEmail(email);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User findOneById(Long id) {
        return repository.getOne(id);
    }

    // find all users who have the roles we need
    @Override
    public List<User> findByRoles(List<Role> roles) {
        return repository.findByRoles(roles);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findByFirstNameAndLastName(String firstName, String lastName) {
        return repository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public User findByNickName(String nickName) {
        return repository.findByNickName(nickName);
    }

    @Override
    public List<User> findByStatus(StatusOfUser status) {
        return repository.findByStatus(status);
    }

    @Override
    public User findAllByEmail(String email) {
        return repository.findByEmail(email);
    }

}
