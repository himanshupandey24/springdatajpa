package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
