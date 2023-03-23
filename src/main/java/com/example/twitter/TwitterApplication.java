package com.example.twitter;

import com.example.twitter.model.Role;
import com.example.twitter.model.User;
import com.example.twitter.repository.RoleRepository;
import com.example.twitter.repository.UserRepository;
import com.example.twitter.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class TwitterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepo, UserRepository userRepository) {
        return args -> {
            Role role = roleRepo.save(new Role(1, "USER"));
            Set<Role> roles = new HashSet<>();
            roles.add(role);
           User user = new User();
            user.setAuthorities(roles);
            user.setFirstName("Jakhongir");
            user.setLastName("Burkhonov");
            user.setEmail("burkhanovjakhongir@gmail.com");
            user.setUsername("javageek");
            user.setPassword("johaboy");
            user.setEnabled(true);
            userRepository.save(user);
        };
    }
}


