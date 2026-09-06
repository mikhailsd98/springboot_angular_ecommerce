package com.springboot.mySpringBootCRUDapp.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "color_amount_of_order_item")
public class ColorAmountOfOrderItem {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id") 
	private Long id; 
	@Column(name="color")
	private String color;
	@Column(name="units_of_order_item")
	private int quantity;
	@ManyToOne
	@JoinColumn(name="order_item_id", nullable=false) 
	@JsonIgnore
	private OrderItem orderItem; 
	public ColorAmountOfOrderItem() {}
	public ColorAmountOfOrderItem(String color, int quantity, OrderItem orderItem) {
		super();
		this.color = color;
		this.quantity = quantity;
		this.orderItem = orderItem;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public OrderItem getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
	
}