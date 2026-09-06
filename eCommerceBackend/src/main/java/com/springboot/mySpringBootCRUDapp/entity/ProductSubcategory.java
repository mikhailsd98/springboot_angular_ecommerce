package com.springboot.mySpringBootCRUDapp.entity;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

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
@Table(name = "product_subcategory")
public class ProductSubcategory {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id") 
	private Long id; 
	@Column(name="subcategory_name") 
	private String categoryName;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subcategory")
	private List<Product> products;
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false) 
	private ProductCategory category; 
	public ProductSubcategory() {}
	public ProductSubcategory(String categoryName, List<Product> products) {
		this.categoryName = categoryName;
		this.products = products;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
