package com.example.blog.controller;

import com.example.blog.config.SecurityConfig;
import com.example.blog.dto.LoginDto;
import com.example.blog.dto.SignUpDto;
import com.example.blog.model.Role;
import com.example.blog.model.User;
import com.example.blog.repository.RoleRepository;
import com.example.blog.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Collections;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Import(SecurityConfig.class)
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("signin")
    public ResponseEntity<String> authentication(@Valid @RequestBody LoginDto loginDto) {
        Authentication authentication
                = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto) {
        if (userRepository.existsByUserName(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username is ready taken!", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is ready taken!",HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setUserName(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        user.setStatus(false);
        user.setCreateDate(LocalDateTime.now());
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
}
