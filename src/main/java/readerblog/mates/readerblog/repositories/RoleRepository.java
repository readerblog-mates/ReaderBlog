package readerblog.mates.readerblog.repository;

/**
 * @author Sergey Petukhov
 */

import org.springframework.data.repository.CrudRepository;
import readerblog.mates.readerblog.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    //добавление роли (   Role save(Role role){ };   ) и удаление роли по id есть в CrudRepository
    void deleteRoleByName(String name);
    // нужно ли удалять по id и name одновременно?
    void deleteRoleByNameAndId(String name, Long id);

}
