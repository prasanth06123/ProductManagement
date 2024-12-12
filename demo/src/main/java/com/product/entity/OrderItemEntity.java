package com.product.entity;

public class OrderItemEntity {

	private String orderRefNo;
	private int quantity;
	private double price;
	private ProductEntity product;

	public String getOrderRefNo() {
		return orderRefNo;
	}

	public void setOrderRefNo(String orderRefNo) {
		this.orderRefNo = orderRefNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

}
