package com.springboot.mySpringBootCRUDapp.dao;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.springboot.mySpringBootCRUDapp.entity.Order;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {
	Page<Order> findByCustomerEmailOrderByDateCreatedDesc(@Param("email") String email, Pageable pageable);
	Page<Order> findByCustomerEmailOrderByDateCreatedAsc(@Param("email") String email, Pageable pageable);
	Page<Order> findByCustomerEmailOrderByItemsPriceDesc(@Param("email") String email, Pageable pageable);
}
