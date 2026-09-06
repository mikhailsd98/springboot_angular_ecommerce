package com.springboot.mySpringBootCRUDapp.dao;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.springboot.mySpringBootCRUDapp.entity.Product;
import com.springboot.mySpringBootCRUDapp.entity.ProductSubcategory;
@RepositoryRestResource
public interface ProductSubcategoryRepository extends JpaRepository<ProductSubcategory, Long> {
	Page<ProductSubcategory> findById(@Param("id") Long id, Pageable pageable);
}