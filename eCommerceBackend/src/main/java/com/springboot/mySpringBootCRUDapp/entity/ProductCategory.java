package com.springboot.mySpringBootCRUDapp.entity;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_category")
public class ProductCategory {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id") 
	private Long id; 
	@Column(name="category_name")
	private String categoryName;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<ProductSubcategory> productsubcategories;
	public ProductCategory() {}
	public ProductCategory(String categoryName, List<ProductSubcategory> productsubcategories) {
		this.categoryName = categoryName;
		this.productsubcategories = productsubcategories;
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
	public List<ProductSubcategory> getProductsubcategories() {
		return productsubcategories;
	}
	public void setProductsubcategories(List<ProductSubcategory> productsubcategories) {
		this.productsubcategories = productsubcategories;
	}
	
}