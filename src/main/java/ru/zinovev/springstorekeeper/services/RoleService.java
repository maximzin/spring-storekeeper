package ru.zinovev.springstorekeeper.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zinovev.springstorekeeper.models.Role;
import ru.zinovev.springstorekeeper.models.User;
import ru.zinovev.springstorekeeper.repositories.RoleRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}