package com.springboot.mySpringBootCRUDapp.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "product")
public class Product {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id") 
	private long id; 
	@Column(name="name")
	private String name;
	@Column(name="unit_price") 
	private BigDecimal unitPrice; 
	@Column(name="description")
	private String description;
	@Column(name="additional_info")
	private String additionalInfo;
	@Column(name="rating") 
	private BigDecimal rating; 
	@Column(name="ratings_amount") 
	private int ratingsAmount; 
	@Column(name="image_url")
	private String imageUrl;
	@Column(name="active")
	private boolean active;
	@Column(name="units_in_stock") 
	private int unitsInStock;
	@Column(name="date_created") 
	@CreationTimestamp
	private Date dateCreated; 
	@Column(name="last_updated") 
	@UpdateTimestamp
	private Date lastUpdated; 
	@ManyToOne
	@JoinColumn(name="subcategory_id", nullable=false) 
	private ProductSubcategory subcategory; 
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<ColorAmountInStock> colors;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<ProductDetailsImages> images;
	public Product() {}
	
	public Product(long id, String name, BigDecimal unitPrice, String description, String additionalInfo,
			BigDecimal rating, int ratingsAmount, String imageUrl, boolean active, int unitsInStock, Date dateCreated,
			Date lastUpdated, ProductSubcategory subcategory, List<ColorAmountInStock> colors,
			List<ProductDetailsImages> images) {
		super();
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.description = description;
		this.additionalInfo = additionalInfo;
		this.rating = rating;
		this.ratingsAmount = ratingsAmount;
		this.imageUrl = imageUrl;
		this.active = active;
		this.unitsInStock = unitsInStock;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
		this.subcategory = subcategory;
		this.colors = colors;
		this.images = images;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public BigDecimal getRating() {
		return rating;
	}
	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}
	public int getRatingsAmount() {
		return ratingsAmount;
	}
	public void setRatingsAmount(int ratingsAmount) {
		this.ratingsAmount = ratingsAmount;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getUnitsInStock() {
		return unitsInStock;
	}
	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public ProductSubcategory getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(ProductSubcategory subcategory) {
		this.subcategory = subcategory;
	}
	public List<ColorAmountInStock> getColors() {
		return colors;
	}
	public void setColors(List<ColorAmountInStock> colors) {
		this.colors = colors;
	}
	public List<ProductDetailsImages> getImages() {
		return images;
	}
	public void setImages(List<ProductDetailsImages> images) {
		this.images = images;
	}
	
}
