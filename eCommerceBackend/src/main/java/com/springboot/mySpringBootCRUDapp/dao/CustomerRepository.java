package com.springboot.mySpringBootCRUDapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.mySpringBootCRUDapp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmail(String email);
}


