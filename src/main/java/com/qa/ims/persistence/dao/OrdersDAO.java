package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.persistence.domain.Customer;

public class OrdersDAO implements Dao<Orders>{
	public static final Logger LOGGER = LogManager.getLogger();
	
	
	
	@Override
	public Orders modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long ordersId = resultSet.getLong("orders_id");
		Long customerId = resultSet.getLong("fk_customers_id");
		String firstName = resultSet.getString("first_name");
		String surname = resultSet.getString("surname");
		Customer customer = new Customer(customerId, firstName, surname);
		
		Long cost = resultSet.getLong("cost");
		return new Orders(ordersId, customer, cost);
	}

	//Reads all orders
	
	@Override
	public List<Orders> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT o.orders_id,oi.fk_items_id, fk_customers_id, sum(oi.quantity*i.`value`) AS cost, c.first_name, c.surname FROM orders o LEFT JOIN customers c ON c.customers_id = o.fk_customers_id LEFT JOIN orders_items oi ON oi.fk_orders_id = o.orders_id LEFT JOIN items i ON oi.fk_items_id=i.items_id GROUP BY o.orders_id");) {
			List<Orders> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	public Orders readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT o.orders_id, fk_customers_id, sum(quantity*`value`) AS cost, c.first_name, c.surname FROM orders o JOIN customers c ON c.customers_id = o.fk_customers_id JOIN orders_items oi ON oi.fk_orders_id = o.orders_id JOIN items i ON oi.fk_items_id=i.items_id GROUP BY o.orders_id ORDER BY o.orders_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	// Creates an order in the database
	
	@Override
	public Orders create(Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(fk_customers_id) VALUES (?)");) {
			statement.setLong(1, order.getCustomer().getId());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Orders read(Long ordersId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT o.orders_id, fk_customers_id, sum(quantity*`value`) AS cost, c.first_name, c.surname FROM orders o JOIN customers c ON c.customers_id = o.fk_customers_id JOIN orders_items oi ON oi.fk_orders_id = o.orders_id JOIN items i ON oi.fk_items_id=i.items_id  WHERE orders_id = ? GROUP BY o.orders_id ORDER BY o.orders_id");) {
			statement.setLong(1, ordersId);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	
// Updates an order in the database
	
	@Override
	public Orders update(Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders SET fk_customers_id = ? WHERE orders_id = ?");) {
			statement.setLong(1, order.getCustomer().getId());
			statement.setLong(2, order.getOrderId());
			statement.executeUpdate();
			return read(order.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	//Deletes an order in the database
	
	@Override
	public int delete(long ordersId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE orders_id= ?");) {
			statement.setLong(1, ordersId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
}
