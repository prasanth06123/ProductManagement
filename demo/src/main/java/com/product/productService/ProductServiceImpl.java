package com.product.productService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.product.db.DBUtils;
import com.product.entity.OrderEntity;
import com.product.entity.OrderItemEntity;
import com.product.entity.ProductEntity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service
public class ProductServiceImpl {

	private static final Log LOG = LogFactory.getLog(ProductServiceImpl.class);

	DBUtils dbUtils = new DBUtils();

	public Map<String, Object> addProduct(ProductEntity product) {

		Map<String, Object> response = new HashMap<>();

		try {

			if (product != null) {

				int rowsAffected = dbUtils.addProduct(product);

				if (rowsAffected > 0) {
					response.put("status", "SUCCESS");
					response.put("message", "Products added successfully");
				} else {
					response.put("status", "ERROR");
					response.put("message", "Oops! something went wrong.Try again later");
				}

			}

		} catch (Exception e) {
			LOG.error("ERROR @addProduct =>" + e.getMessage(), e);
		}
		return response;
	}

	public Map<String, Object> getAllProduct() {

		List<Map<String, Object>> allProductsData = new ArrayList<>();
		Map<String, Object> response = new HashMap<>();

		try {

			allProductsData = dbUtils.getAllProduct();

			if (!allProductsData.isEmpty()) {

				response.put("status", "SUCCESS");
				response.put("message", "Products retrieved successfully");
				response.put("data", allProductsData);

			} else {
				response.put("status", "ERROR");
				response.put("message", "Oops! something went wrong.Try again later");

			}
		} catch (Exception e) {
			LOG.error("ERROR @getAllProduct =>" + e.getMessage(), e);
		}
		return response;
	}

	public Map<String, Object> deleteProduct(String productCode) {

		int rowsAffected = 0;
		Map<String, Object> response = new HashMap<>();

		try {

			if (!productCode.isEmpty()) {

				Map<String, Object> productsData = getProduct(productCode);
				if (!productsData.isEmpty()) {

					rowsAffected = dbUtils.deleteProduct(productCode);
					if (rowsAffected > 0) {

						response.put("status", "SUCCESS");
						response.put("message", "Product deleted successfully");
					} else {

						response.put("status", "ERROR");
						response.put("message", "Oops! something went wrong.Try again later");
					}
				} else {

					response.put("status", "ERROR");
					response.put("message", "Invalid Product Code");
				}
			}

		} catch (Exception e) {
			LOG.error("ERROR @createOrders =>" + e.getMessage(), e);
		}
		return response;
	}

	public Map<String, Object> getProduct(String productCode) {

		List<Map<String, Object>> productsDataList = new ArrayList<>();
		Map<String, Object> productData = new HashMap<>();
		Map<String, Object> response = new HashMap<>();

		try {

			if (!productCode.isEmpty()) {

				productsDataList = dbUtils.getProduct(productCode);
				if (!productsDataList.isEmpty()) {

					productData = productsDataList.get(0);
					if (!productData.isEmpty()) {

						response.put("status", "SUCCESS");
						response.put("message", "Product retrieved successfully");
						response.put("data", productData);
					} else {

						response.put("status", "ERROR");
						response.put("message", "Oops! something went wrong.Try again later");
					}
				}
			}

		} catch (Exception e) {
			LOG.error("ERROR @deleteProduct =>" + e.getMessage(), e);
		}
		return response;
	}

	public Map<String, Object> createOrders(OrderEntity orderEntityDetails) {

		Map<String, Object> response = new HashMap<>();

		try {
			response = buildandSaveOrderData(orderEntityDetails);
		} catch (Exception e) {
			LOG.error("ERROR @createOrders =>" + e.getMessage(), e);
		}
		return response;
	}

	private Map<String, Object> buildandSaveOrderData(OrderEntity orderEntityDetails) {

		Map<String, Object> response = new HashMap<>();

		try {

			if (orderEntityDetails != null) {

				Map<String, Object> orderData = new HashMap<>();
				orderData.put("customer_name", orderEntityDetails.getCustomerName());
				orderData.put("mobile_number", orderEntityDetails.getMobileNumber());
				orderData.put("order_ref_no", orderEntityDetails.getOrderRefNo());
				orderData.put("shipping_address", orderEntityDetails.getShippingAddress());
				orderData.put("status", orderEntityDetails.getStatus());

				int orderRowsAffected = dbUtils.createOrder(orderData);

				if (orderRowsAffected > 0) {

					if (orderEntityDetails.getOrderItemEntity() instanceof List) {

						Map<String, Object> orderItemData = new HashMap<>();
						List<OrderItemEntity> orderItemEntityList = orderEntityDetails.getOrderItemEntity();

						for (OrderItemEntity orderItemEntity : orderItemEntityList) {

							orderItemData = new HashMap<>();

							orderItemData.put("order_ref_no", orderItemEntity.getOrderRefNo());
							orderItemData.put("quantity", orderItemEntity.getQuantity());
							orderItemData.put("price", orderItemEntity.getPrice());

							ProductEntity product = orderItemEntity.getProduct();

							if (product != null) {
								orderItemData.put("product_code", product.getProductCode());
								orderItemData.put("product_name", product.getProductName());
								orderItemData.put("catalog_code", product.getCatalogCode());
								orderItemData.put("product_price", product.getProductPrice());
							}

							int orderItemRowsAffected = dbUtils.createOrderItem(orderData);

							if (orderItemRowsAffected > 0) {

								response.put("status", "SUCCESS");
								response.put("message", "Order placed successfully");
							} else {
								response.put("status", "ERROR");
								response.put("message", "Oops! something went wrong.Try again later");
							}
						}
					}

				}

			}

		} catch (Exception e) {
			LOG.error("ERROR @buildandSaveOrderData =>" + e.getMessage(), e);
		}
		return response;
	}

	public Map<String, Object> getUserOrders(String mobileNumber) {

		List<Map<String, Object>> allUserOrdersData = new ArrayList<>();
		Map<String, Object> response = new HashMap<>();

		try {

			if (!mobileNumber.isEmpty()) {

				allUserOrdersData = dbUtils.getUserOrders(mobileNumber);

				if (!allUserOrdersData.isEmpty()) {

					response.put("status", "SUCCESS");
					response.put("message", "User Orders retrieved successfully");
					response.put("data", allUserOrdersData);

				} else {
					response.put("status", "ERROR");
					response.put("message", "Oops! something went wrong.Try again later");

				}
			}

		} catch (Exception e) {
			LOG.error("ERROR @getUserOrders =>" + e.getMessage(), e);
		}
		return response;
	}
}
