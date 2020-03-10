package readerblog.mates.readerblog.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import readerblog.mates.readerblog.entities.User;
import readerblog.mates.readerblog.enums.StatusOfUser;
import readerblog.mates.readerblog.repositories.UserRepository;
import readerblog.mates.readerblog.services.UserService;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergey Petukhov
 */

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(@Autowired UserRepository repository){
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
        if (user == null)
            return null;
        else return repository.save(user);
    }

    @Override
    public User findOneById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public List<User> findByFirstNameAndLastName(String firstName, String lastName) {
        if (firstName == null || lastName == null)
            return null;
        else return repository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public User findByNickName(String nickName) {
        if (nickName == null)
            return null;
        else return repository.findByNickName(nickName);
    }

    @Override
    public List<User> findByStatus(StatusOfUser status) {
        if (status == null)
            return null;
        else return repository.findByStatus(status);
    }

    @Override
    public User findAllByEmail(String email) {
        if (email == null)
            return null;
        else return repository.findByEmail(email).get();
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }
}
