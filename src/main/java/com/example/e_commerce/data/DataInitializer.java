package com.example.e_commerce.data;

import com.example.e_commerce.model.Role;
import com.example.e_commerce.model.User;
import com.example.e_commerce.repository.RoleRepository;
import com.example.e_commerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Set<String> defaultRoles = Set.of("ROLE_USER", "ROLE_ADMIN");
        createDefaultRoleIfNotExist(defaultRoles);
        createDefaultUserIfNotExist();
        createDefaultAdminIfNotExist();
    }

    private void createDefaultUserIfNotExist() {
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        for (int i = 0; i < 5; i++) {
            String defaultUserEmail = "user" + i + "@example.com";
            if (!userRepository.existsByEmail(defaultUserEmail)) {
                User user = new User();
                user.setFirstName("The User");
                user.setLastName("User" + i);
                user.setEmail(defaultUserEmail);
                user.setRoles(Set.of(userRole));
                user.setPassword(passwordEncoder.encode("password"));
                userRepository.save(user);
                System.out.println("Created default user: " + user);
            }
        }
    }

    private void createDefaultAdminIfNotExist() {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").get();
        for (int i = 0; i < 5; i++) {
            String defaultEmail = "admin" + i + "@example.com";
            if (!userRepository.existsByEmail(defaultEmail)) {
                User user = new User();
                user.setFirstName("The Admin");
                user.setLastName("Admin" + i);
                user.setEmail(defaultEmail);
                user.setRoles(Set.of(adminRole));
                user.setPassword(passwordEncoder.encode("password"));
                userRepository.save(user);
                System.out.println("Created default admin: " + user);
            }
        }
    }

    private void createDefaultRoleIfNotExist(Set<String> roles) {
        roles.stream()
                .filter(role -> roleRepository.findByName(role).isEmpty())
                .map(Role::new)
                .forEach(roleRepository::save);
    }

}
