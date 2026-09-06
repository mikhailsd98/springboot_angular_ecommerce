package com.springboot.mySpringBootCRUDapp.entity;

import java.util.Set;
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
@Table(name = "color_amount_in_stock")
public class ColorAmountInStock {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id") 
	private Long id; 
	@Column(name="color")
	private String color;
	@Column(name="units_in_stock")
	private int units_in_stock;
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false) 
	private Product product; 
	public ColorAmountInStock() {}
	public ColorAmountInStock(String color, int units_in_stock, Product product) {
		super();
		this.color = color;
		this.units_in_stock = units_in_stock;
		this.product = product;
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
	public int getUnits_in_stock() {
		return units_in_stock;
	}
	public void setUnits_in_stock(int units_in_stock) {
		this.units_in_stock = units_in_stock;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}