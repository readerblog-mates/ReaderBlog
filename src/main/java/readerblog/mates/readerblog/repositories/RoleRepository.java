package readerblog.mates.readerblog.repositories;

/**
 * @author Sergey Petukhov
 */

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import readerblog.mates.readerblog.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    //добавление роли (   Role save(Role role){ };   ) и удаление роли по id есть в CrudRepository
    Role findByName(String name);

    void deleteRoleByName(String name);
    // нужно ли удалять по id и name одновременно?
    void deleteRoleByNameAndId(String name, Long id);

}
