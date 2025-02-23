package org.example.backend.security;

import lombok.RequiredArgsConstructor;
import org.example.backend.security.dto.SignupRequest;
import org.example.backend.user.RoleRepository;
import org.example.backend.user.UserRepository;
import org.example.backend.user.model.ERole;
import org.example.backend.user.model.Role;
import org.example.backend.user.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder encoder;

  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  public void register(SignupRequest signUpRequest) {
    User user = User.builder()
        .username(signUpRequest.getUsername())
        .password(encoder.encode(signUpRequest.getPassword()))
        .email(signUpRequest.getEmail())
        .build();

    Set<String> rolesStr = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();

    if (rolesStr == null) {
      Role defaultRole = roleRepository.findByName(ERole.CUSTOMER)
          .orElseThrow(() -> new RuntimeException("Cannot find CUSTOMER role"));
      roles.add(defaultRole);
    } else {
      rolesStr.forEach(r -> {
        Role ro = roleRepository.findByName(ERole.valueOf(r))
            .orElseThrow(() -> new RuntimeException("Cannot find role: " + r));
        roles.add(ro);
      });
    }

    user.setRoles(roles);
    userRepository.save(user);
  }
}
