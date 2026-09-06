package com.springboot.mySpringBootCRUDapp.entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="item_price")
    private BigDecimal itemPrice;

    @Column(name="quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderItem")
    private List<ColorAmountOfOrderItem> colors = new ArrayList<>();

    @Column(name="product_id")
    private Long productId;

    public OrderItem() {}
	public OrderItem(Long id, String imageUrl, BigDecimal itemPrice, int quantity, Order order, Long productId) {
		this.id = id;
		this.imageUrl = imageUrl;
		this.itemPrice = itemPrice;
		this.quantity = quantity;
		this.order = order;
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public List<ColorAmountOfOrderItem> getColors() {
		return colors;
	}

	public void setColors(List<ColorAmountOfOrderItem> colors) {
		this.colors = colors;
	}
}








