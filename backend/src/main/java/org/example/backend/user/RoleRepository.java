package org.example.backend.user;

import org.example.backend.user.model.ERole;
import org.example.backend.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole role);

}
