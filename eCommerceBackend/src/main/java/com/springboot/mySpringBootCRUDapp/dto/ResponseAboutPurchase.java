package com.springboot.mySpringBootCRUDapp.dto;

public class ResponseAboutPurchase {

    private String trackingNumber;

	public ResponseAboutPurchase(String trackingNumber) {
		super();
		this.trackingNumber = trackingNumber;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setOrderTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
}
