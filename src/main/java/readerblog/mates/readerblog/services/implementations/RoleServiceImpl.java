package readerblog.mates.readerblog.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import readerblog.mates.readerblog.entities.Role;
import readerblog.mates.readerblog.repositories.RoleRepository;
import readerblog.mates.readerblog.services.RoleService;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

}
