package com.product.entity;

import java.util.List;

public class OrderEntity {

	private String orderRefNo;
	private String customerName;
	private String mobileNumber;
	private String shippingAddress;
	private String status;
	private List<OrderItemEntity> orderItem;

	public List<OrderItemEntity> getOrderItemEntity() {
		return orderItem;
	}

	public void setOrderItemEntity(List<OrderItemEntity> orderItemEntity) {
		this.orderItem = orderItemEntity;
	}

	public String getOrderRefNo() {
		return orderRefNo;
	}

	public void setOrderRefNo(String orderRefNo) {
		this.orderRefNo = orderRefNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
