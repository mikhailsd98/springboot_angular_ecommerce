package com.springboot.mySpringBootCRUDapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springboot.mySpringBootCRUDapp.dto.Purchase;
import com.springboot.mySpringBootCRUDapp.dto.ResponseAboutPurchase;
import com.springboot.mySpringBootCRUDapp.entity.Product;
import com.springboot.mySpringBootCRUDapp.service.OrderProcessingService;
import com.springboot.mySpringBootCRUDapp.service.ProductService;

@RestController
@RequestMapping("/eCommerceApi/productChange")
public class ProductController {
	@Autowired
	private ProductService productService;

    @PostMapping(value = "/changeRating", consumes = {"application/json"})
    public Boolean changeRating(@RequestBody Product product) {
    	productService.updateProductRating(product);
        return true;
    }

}









