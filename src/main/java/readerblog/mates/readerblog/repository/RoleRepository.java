package readerblog.mates.readerblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import readerblog.mates.readerblog.model.Role;
import readerblog.mates.readerblog.model.User;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
