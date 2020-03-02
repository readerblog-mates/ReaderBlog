package readerblog.mates.readerblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import readerblog.mates.readerblog.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user From User user left join fetch user.roles  WHERE user.email =:email")
    Optional<User> findByEmail(String email);
    @Query("SELECT user FROM User user left join fetch user.roles WHERE user.id =:id")
    Optional<User> findById(Long id);
    Boolean existsByEmail(String email);
    @Query("SELECT user FROM User user left join fetch user.roles WHERE user.providerId =:providerId")
    Optional<User> findByProviderId(String providerId);

}
