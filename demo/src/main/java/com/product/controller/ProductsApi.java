package com.product.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.product.entity.OrderEntity;
import com.product.entity.ProductEntity;

public interface ProductsApi {

	@PostMapping("/api/addProduct")
	ResponseEntity<Object> addProduct(@RequestBody ProductEntity product);

	@GetMapping("/api/getAllProducts")
	ResponseEntity<Object> getAllProduct();

	@PostMapping("/api/getProduct")
	ResponseEntity<Object> getProduct(@RequestBody Map<String, Object> productCode);

	@PostMapping("/api/deleteProduct")
	ResponseEntity<Object> deleteProduct(@RequestBody Map<String, Object> productCode);

	@PostMapping("/api/createOrders")
	ResponseEntity<Object> createOrders(@RequestBody OrderEntity orderEntityDetails);

	@PostMapping("/api/getUserOrders")
	ResponseEntity<Object> getUserOrders(@RequestBody String mobileNumber);

}
