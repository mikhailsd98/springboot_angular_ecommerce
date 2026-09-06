package com.springboot.mySpringBootCRUDapp.dto;
import java.util.List;
import java.util.Set;

import com.springboot.mySpringBootCRUDapp.entity.Address;
import com.springboot.mySpringBootCRUDapp.entity.Customer;
import com.springboot.mySpringBootCRUDapp.entity.Order;
import com.springboot.mySpringBootCRUDapp.entity.OrderItem;

public class Purchase {

    private Customer customer;
    private Address address;
    private Order order;
    private Set<OrderItem> orderItems;
	public Purchase(Customer customer, Address address, Order order, Set<OrderItem> orderItems) {
		this.customer = customer;
		this.address = address;
		this.order = order;
		this.orderItems = orderItems;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
