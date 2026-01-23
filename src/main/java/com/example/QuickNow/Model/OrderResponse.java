package com.example.QuickNow.Model;

import java.time.LocalDateTime;
import com.example.QuickNow.Model.Order.Status;

public class OrderResponse {

	private int orderId;
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	private String userName;
	private String mobile;
	private String productName;
	private double price;
	private String address;
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	private String paymentMethod;
	private String status;
	private LocalDateTime createAt;
	private String imageUrl;

	public OrderResponse(int orderId, String userName, String productName, double price, Status status,
			LocalDateTime createAt, String imageUrl, String address, String paymentMethod,String mobile) {

		this.orderId = orderId;
		this.userName = userName;
		this.productName = productName;
		this.price = price;
		this.status = status.name();
		this.createAt = createAt;
		this.imageUrl = imageUrl;
		this.address = address;
		this.paymentMethod = paymentMethod;
	    this.mobile = mobile;
	}

	public OrderResponse() {
	}

	// Getters and Setters
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
