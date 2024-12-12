package com.product.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.product.entity.ProductEntity;

public class DBUtils {

	public Connection getDBConnection() {
		try {

			String url = "jdbc:mysql://localhost:3306/product_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
			String userName = "root";
			String password = "prasanth@0609";

			Connection connection = DriverManager.getConnection(url, userName, password);
			return connection;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Integer addProduct(ProductEntity product) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int rowsAffected = 0;

		try {

			connection = getDBConnection();
			String addProductQuery = "INSERT INTO PRODUCT_DB.products (product_code, product_name, catalog_code, product_description, product_price) VALUES (?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(addProductQuery);
			preparedStatement.setString(1, product.getProductCode());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setString(3, product.getCatalogCode());
			preparedStatement.setString(4, product.getProductDescription());
			preparedStatement.setString(5, product.getProductPrice());

			rowsAffected = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected;

	}

	public List<Map<String, Object>> getAllProduct() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = getDBConnection();
			String getALlProductQuery = "SELECT * FROM PRODUCT_DB.products";

			preparedStatement = connection.prepareStatement(getALlProductQuery);
			resultSet = preparedStatement.executeQuery();

			List<Map<String, Object>> productsList = new ArrayList<>();
			ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (resultSet.next()) {

				Map<String, Object> rowMap = new HashMap<>();
				for (int i = 1; i <= columnCount; i++) {

					String columnName = metaData.getColumnName(i);
					Object value = resultSet.getObject(i);
					rowMap.put(columnName, value);
				}

				productsList.add(rowMap);
			}

			return productsList;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<Map<String, Object>> getProduct(String productCode) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = getDBConnection();
			String getALlProductQuery = "SELECT * FROM PRODUCT_DB.products where PRODUCT_CODE = ?";

			preparedStatement = connection.prepareStatement(getALlProductQuery);
			preparedStatement.setString(1, productCode);
			resultSet = preparedStatement.executeQuery();

			List<Map<String, Object>> productsList = new ArrayList<>();
			ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (resultSet.next()) {

				Map<String, Object> rowMap = new HashMap<>();
				for (int i = 1; i <= columnCount; i++) {

					String columnName = metaData.getColumnName(i);
					Object value = resultSet.getObject(i);
					rowMap.put(columnName, value);
				}

				productsList.add(rowMap);
			}

			return productsList;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public int deleteProduct(String productCode) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int rowsAffected = 0;

		try {

			connection = getDBConnection();
			String getALlProductQuery = "DELETE FROM PRODUCT_DB.products where PRODUCT_CODE = ?";

			preparedStatement = connection.prepareStatement(getALlProductQuery);
			preparedStatement.setString(1, productCode);
			rowsAffected = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected;
	}

	public Integer createOrder(Map<String, Object> orderData) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int rowsAffected = 0;

		try {

			connection = getDBConnection();
			String addProductQuery = "INSERT INTO PRODUCT_DB.order (customer_name, mobile_number, order_ref_no, shipping_address, status) VALUES (?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(addProductQuery);
			preparedStatement.setString(1, String.valueOf(orderData.get("customer_name")));
			preparedStatement.setString(2, String.valueOf(orderData.get("mobile_number")));
			preparedStatement.setString(3, String.valueOf(orderData.get("order_ref_no")));
			preparedStatement.setString(4, String.valueOf(orderData.get("shipping_address")));
			preparedStatement.setString(5, String.valueOf(orderData.get("status")));

			rowsAffected = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected;

	}

	public Integer createOrderItem(Map<String, Object> orderData) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int rowsAffected = 0;

		try {

			connection = getDBConnection();
			String addProductQuery = "INSERT INTO PRODUCT_DB.order (order_ref_no, quantity, price, product_code, product_name, catalog_code, product_price) VALUES (?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(addProductQuery);
			preparedStatement.setString(1, String.valueOf(orderData.get("order_ref_no")));
			preparedStatement.setString(2, String.valueOf(orderData.get("quantity")));
			preparedStatement.setString(3, String.valueOf(orderData.get("price")));
			preparedStatement.setString(4, String.valueOf(orderData.get("product_code")));
			preparedStatement.setString(5, String.valueOf(orderData.get("product_name")));
			preparedStatement.setString(5, String.valueOf(orderData.get("catalog_code")));
			preparedStatement.setString(5, String.valueOf(orderData.get("product_price")));

			rowsAffected = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected;

	}

	public List<Map<String, Object>> getUserOrders(String mobileNumber) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = getDBConnection();
			String getALlProductQuery = "SELECT * FROM PRODUCT_DB.order where MOBILE_NUMBER = ?";

			preparedStatement = connection.prepareStatement(getALlProductQuery);
			preparedStatement.setString(1, mobileNumber);
			resultSet = preparedStatement.executeQuery();

			List<Map<String, Object>> productsList = new ArrayList<>();
			ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (resultSet.next()) {

				Map<String, Object> rowMap = new HashMap<>();
				for (int i = 1; i <= columnCount; i++) {

					String columnName = metaData.getColumnName(i);
					Object value = resultSet.getObject(i);
					rowMap.put(columnName, value);
				}

				productsList.add(rowMap);
			}

			return productsList;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
