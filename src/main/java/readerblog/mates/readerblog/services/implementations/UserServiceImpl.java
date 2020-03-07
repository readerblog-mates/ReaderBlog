package readerblog.mates.readerblog.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import readerblog.mates.readerblog.entities.User;
import readerblog.mates.readerblog.enums.StatusOfUser;
import readerblog.mates.readerblog.repositories.UserRepository;
import readerblog.mates.readerblog.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Sergey Petukhov
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    @Transactional
    public User deleteByEmail(String email) {
        if (email == null)
            return null;
        else return repository.deleteByEmail(email);
    }

    @Override
    @Transactional
    public User deleteById(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()){
            repository.deleteById(id);
            return user.get();
        }
        return null;
    }

    @Override
    @Transactional
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    @Transactional
    public User save(User user) {
        if (user == null)
            return null;
        else return repository.save(user);
    }

    @Override
    @Transactional
    public User findOneById(Long id) {
        return repository.getOne(id);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public List<User> findByFirstNameAndLastName(String firstName, String lastName) {
        return  (firstName == null || lastName == null) ? new ArrayList<>() :
                repository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    @Transactional
    public User findByNickName(String nickName) {
        if (nickName == null)
            return null;
        else return repository.findByNickName(nickName);
    }

    @Override
    @Transactional
    public List<User> findByStatus(StatusOfUser status) {
        return status == null ? new ArrayList<>() : repository.findByStatus(status);
    }

    @Override
    @Transactional
    public User findAllByEmail(String email) {
        if (email == null)
            return null;
        else return repository.findByEmail(email).get();
    }
}
