package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Role;
import com.springdatajpa.springdatajpa.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ManyToManyUnidirectionalMappingTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUser(){
        User user = User.builder()
                .firstName("Himanshu")
                .lastName("Pandey")
                .email("himanshupandey@gmail.com")
                .password("secretkey")
                .roles(new HashSet<>())
                .build();

        Role admin = Role.builder()
                .name("ROLE_ADMIN")
                .build();

        Role customer = Role.builder()
                .name("ROLE_CUSTOMER")
                .build();

        user.getRoles().addAll(List.of(admin, customer));

        userRepository.save(user);
    }

    @Test
    void updateUser(){
        Optional<User> userOptional = userRepository.findById(1L);
        userOptional.ifPresent(user -> {
            user.setFirstName("Kartik");
            user.setEmail("kartik@gmail.com");
            Role roleUser = Role.builder()
                    .name("ROLE_USER")
                    .build();

            user.getRoles().add(roleUser);
            userRepository.save(user);
        });
    }

    @Test
    void fetchUser(){
        Optional<User> userOptional = userRepository.findById(1L);
        userOptional.ifPresent(user -> {
            System.out.println(user.getEmail());
            user.getRoles().forEach(role -> System.out.println(role.getName()));
        });
    }

    @Test
    void deleteUser(){
        userRepository.deleteById(1L);
    }


}
