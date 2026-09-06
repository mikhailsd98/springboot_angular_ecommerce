package com.springboot.mySpringBootCRUDapp.dao;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.springboot.mySpringBootCRUDapp.entity.Order;
import com.springboot.mySpringBootCRUDapp.entity.Product;
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findById(long id);
	
	Page<Product> findBySubcategoryId(@Param("id") long id, Pageable pageable);

	@Query("select p from Product p where p.name=:name")
	Page<Product> sumeMethodForMakingCustomQuery(@Param("name") Long id, Pageable pageable);
	
	Page<Product> findBySubcategoryCategoryId(@Param("id") long id, Pageable pageable);
	
	
	Page<Product> findByNameContainingOrderByDateCreatedDesc(@Param("name") String name, Pageable pageable);
	Page<Product> findByNameContainingOrderByRatingDesc(@Param("name") String name, Pageable pageable);
	Page<Product> findByNameContainingOrderByUnitPriceDesc(@Param("name") String name, Pageable pageable);
	Page<Product> findByNameContainingOrderByUnitPriceAsc(@Param("name") String name, Pageable pageable);
}