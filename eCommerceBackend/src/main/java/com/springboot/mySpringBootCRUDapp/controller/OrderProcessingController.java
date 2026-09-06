package com.springboot.mySpringBootCRUDapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springboot.mySpringBootCRUDapp.dto.Purchase;
import com.springboot.mySpringBootCRUDapp.dto.ResponseAboutPurchase;
import com.springboot.mySpringBootCRUDapp.service.OrderProcessingService;

@RestController
@RequestMapping("/eCommerceApi/checkout")
public class OrderProcessingController {
	@Autowired
	private OrderProcessingService orderProcessingService;

    @PostMapping(value = "/purchase", consumes = {"application/json"})
    public ResponseAboutPurchase placeOrder(@RequestBody Purchase purchase) {
    	ResponseAboutPurchase responseAboutPurchase = orderProcessingService.placeOrder(purchase);
        return responseAboutPurchase;
    }

}









