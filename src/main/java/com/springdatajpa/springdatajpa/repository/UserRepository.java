package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
