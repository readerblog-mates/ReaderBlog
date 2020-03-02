package readerblog.mates.readerblog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import readerblog.mates.readerblog.model.Role;
import readerblog.mates.readerblog.model.User;
import readerblog.mates.readerblog.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role findById(int id) {
        return roleRepository.findById(id).orElse(null);
    }
}
