package com.springboot.mySpringBootCRUDapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mySpringBootCRUDapp.dao.ProductRepository;
import com.springboot.mySpringBootCRUDapp.entity.Product;

import jakarta.transaction.Transactional;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository rep;
	@Override
    @Transactional
	public void updateProductRating(Product product) {
		Product productToUpdate = rep.findById(product.getId());
		System.out.println(productToUpdate);
		productToUpdate.setRating(product.getRating());
		productToUpdate.setRatingsAmount(product.getRatingsAmount());
		rep.save(productToUpdate);
	}

}
