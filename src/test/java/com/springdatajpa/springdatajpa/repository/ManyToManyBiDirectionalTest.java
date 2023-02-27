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
public class ManyToManyBiDirectionalTest {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveRole() {

        User user = User.builder()
                .firstName("Himanshu")
                .lastName("Pandey")
                .email("himanshupandey@gmail.com")
                .password("secretkeyhim")
                .roles(new HashSet<>())
                .build();

        User user1 = User.builder()
                .firstName("Depandre")
                .lastName("Singh")
                .email("depandresingh@gmail.com")
                .password("secretkeydep")
                .roles(new HashSet<>())
                .build();

        Role admin = Role.builder()
                .name("ROLE_ADMIN")
                .users(new HashSet<>())
                .build();

        user.getRoles().add(admin);
        user1.getRoles().add(admin);

        admin.getUsers().addAll(List.of(user, user1));

        roleRepository.save(admin);
    }

    @Test
    void fetchRole(){
        Optional<Role> roleOptional = roleRepository.findById(1L);
        roleOptional.ifPresent(role -> {
            System.out.println(role.getName());
            role.getUsers().forEach(System.out::println);
        });
    }
}
