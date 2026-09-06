package com.springboot.mySpringBootCRUDapp.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.springboot.mySpringBootCRUDapp.entity.ColorAmountInStock;
import com.springboot.mySpringBootCRUDapp.entity.Product;
import com.springboot.mySpringBootCRUDapp.entity.ProductDetailsImages;

//@CrossOrigin("http://localhost:4200/")
@RepositoryRestResource
public interface ProductDetailsImagesRepository extends JpaRepository<ProductDetailsImages, Long> {
}