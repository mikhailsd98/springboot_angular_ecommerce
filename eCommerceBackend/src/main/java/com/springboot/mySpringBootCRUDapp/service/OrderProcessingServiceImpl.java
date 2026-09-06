package com.springboot.mySpringBootCRUDapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mySpringBootCRUDapp.dao.CustomerRepository;
import com.springboot.mySpringBootCRUDapp.dto.Purchase;
import com.springboot.mySpringBootCRUDapp.dto.ResponseAboutPurchase;
import com.springboot.mySpringBootCRUDapp.entity.Customer;
import com.springboot.mySpringBootCRUDapp.entity.Order;
import com.springboot.mySpringBootCRUDapp.entity.OrderItem;

import jakarta.transaction.Transactional;
import java.util.UUID;
import java.util.List;
import java.util.Set;
@Service
public class OrderProcessingServiceImpl implements OrderProcessingService {
	@Autowired
    private CustomerRepository customerRepository;
	
    @Override
    @Transactional
    public ResponseAboutPurchase placeOrder(Purchase purchase) {
        Order order = purchase.getOrder();

        String trackingNumberOfOrder = UUID.randomUUID().toString();
        order.setTrackingNumber(trackingNumberOfOrder);

        Set<OrderItem> orderItems = purchase.getOrderItems();
        for (OrderItem item : orderItems) {
        	order.add(item); 
        	item.getColors().forEach(color -> color.setOrderItem(item));
        }
        order.setAddress(purchase.getAddress());

        Customer customer = purchase.getCustomer();
        
        Customer existingCustomer = customerRepository.findByEmail(customer.getEmail());

        if (existingCustomer != null) {
            customer = existingCustomer;
        }
        
        customer.add(order);

        customerRepository.save(customer);

        return new ResponseAboutPurchase(trackingNumberOfOrder);
    }
}









