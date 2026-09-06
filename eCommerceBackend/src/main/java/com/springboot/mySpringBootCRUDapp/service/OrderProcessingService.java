package com.springboot.mySpringBootCRUDapp.service;

import com.springboot.mySpringBootCRUDapp.dto.Purchase;
import com.springboot.mySpringBootCRUDapp.dto.ResponseAboutPurchase;

public interface OrderProcessingService {
    ResponseAboutPurchase placeOrder(Purchase purchase);
}
