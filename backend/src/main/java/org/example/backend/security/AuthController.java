package org.example.backend.security;

import lombok.RequiredArgsConstructor;
import org.example.backend.security.dto.JwtResponse;
import org.example.backend.security.dto.LoginRequest;
import org.example.backend.security.dto.MessageResponse;
import org.example.backend.security.dto.SignupRequest;
import org.example.backend.user.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.backend.UrlMapping.*;

@RequestMapping(AUTH)
@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final AuthService authService;
  private final JwtUtils jwtUtils;

  @PostMapping(SIGN_IN)
  public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());

    return ResponseEntity.ok(
        JwtResponse.builder()
            .token(jwt)
            .id(userDetails.getId())
            .username(userDetails.getUsername())
            .email(userDetails.getEmail())
            .roles(roles)
            .build());
  }

  @PostMapping(SIGN_UP)
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (authService.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (authService.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    authService.register(signUpRequest);


    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
