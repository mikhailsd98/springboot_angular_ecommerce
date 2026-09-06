package com.springboot.mySpringBootCRUDapp.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="tracking_number")
    private String trackingNumber;

    @Column(name="items_price")
    private BigDecimal itemsPrice;
    
    @Column(name="items_quantity")
    private int itemsQuantity;
    
    @Column(name="order_notes")
    private String orderNotes;
    
    @Column(name="note_by_email")
    private boolean noteByEmail;
    
    @Column(name="status")
    private String status;

    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    
    public void add(OrderItem item) {

        if (item != null) {
            if (orderItems == null) {
                orderItems = new ArrayList<>();
            }

            orderItems.add(item);
            item.setOrder(this);
        }
    }
    public Order() {}
	public Order(Long id, String trackingNumber, BigDecimal itemsPrice, int itemsQuantity, String orderNotes,
			boolean noteByEmail, String status, Date dateCreated, Date lastUpdated, List<OrderItem> orderItems,
			Customer customer, Address address) {
		this.id = id;
		this.trackingNumber = trackingNumber;
		this.itemsPrice = itemsPrice;
		this.itemsQuantity = itemsQuantity;
		this.orderNotes = orderNotes;
		this.noteByEmail = noteByEmail;
		this.status = status;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
		this.orderItems = orderItems;
		this.customer = customer;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public BigDecimal getItemsPrice() {
		return itemsPrice;
	}

	public void setItemsPrice(BigDecimal itemsPrice) {
		this.itemsPrice = itemsPrice;
	}

	public int getItemsQuantity() {
		return itemsQuantity;
	}

	public void setItemsQuantity(int itemsQuantity) {
		this.itemsQuantity = itemsQuantity;
	}

	public String getOrderNotes() {
		return orderNotes;
	}

	public void setOrderNotes(String orderNotes) {
		this.orderNotes = orderNotes;
	}

	public boolean isNoteByEmail() {
		return noteByEmail;
	}

	public void setNoteByEmail(boolean noteByEmail) {
		this.noteByEmail = noteByEmail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
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
    
}









