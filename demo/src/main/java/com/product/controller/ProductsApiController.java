package com.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.OrderEntity;
import com.product.entity.ProductEntity;
import com.product.productService.ProductServiceImpl;

@RestController
public class ProductsApiController implements ProductsApi {

	ProductServiceImpl productServiceImpl = new ProductServiceImpl();

	@Override
	public ResponseEntity<Object> addProduct(ProductEntity product) {

		Map<String, Object> response = new HashMap<>();
		response = productServiceImpl.addProduct(product);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getAllProduct() {

		Map<String, Object> response = new HashMap<>();
		response = productServiceImpl.getAllProduct();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getProduct(Map<String, Object> requestData) {

		Map<String, Object> response = new HashMap<>();
		if (!requestData.isEmpty()) {

			String productCode = String.valueOf(requestData.get("productCode"));
			response = productServiceImpl.getProduct(productCode);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return null;
	}

	@Override
	public ResponseEntity<Object> deleteProduct(Map<String, Object> requestData) {

		Map<String, Object> response = new HashMap<>();
		if (!requestData.isEmpty()) {

			String productCode = String.valueOf(requestData.get("productCode"));
			response = productServiceImpl.deleteProduct(productCode);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return null;
	}

	@Override
	public ResponseEntity<Object> createOrders(OrderEntity orderEntityDetails) {

		Map<String, Object> response = new HashMap<>();
		if (orderEntityDetails != null) {

			response = productServiceImpl.createOrders(orderEntityDetails);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return null;
	}

	@Override
	public ResponseEntity<Object> getUserOrders(String mobileNumber) {

		Map<String, Object> response = new HashMap<>();
		if (mobileNumber != null) {

			response = productServiceImpl.getUserOrders(mobileNumber);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return null;
	}

}
