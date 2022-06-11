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

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.persistence.domain.OrdersItems;
import com.qa.ims.utils.DBUtils;

public class OrdersItemsDAO implements Dao<OrdersItems> {
	public static final Logger LOGGER = LogManager.getLogger();

	
	@Override
	public OrdersItems modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderItemsId = resultSet.getLong("orders_items_id");
		Long itemsId = resultSet.getLong("fk_items_id");
		Long ordersId = resultSet.getLong("fk_orders_id");
		Long quantity = resultSet.getLong("quantity");
				
		double value = resultSet.getDouble("value");
		String itemName = resultSet.getString("item_name");
		
		Long customersId = resultSet.getLong("customers_id");
		String firstName = resultSet.getString("first_name");
		String surname = resultSet.getString("surname");
		
		Items items = new Items(itemsId, value, itemName);
		Customer customer = new Customer(customersId, firstName, surname);		
		Orders orders = new Orders(ordersId, customer);
		
		
		return new OrdersItems(orderItemsId, items, orders, quantity);
	}
	
	// Reads all ordersItems records
	
	@Override
	public List<OrdersItems> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT oi.orders_items_id, oi.fk_orders_id, fk_items_id, oi.quantity, i.`value`, i.item_name, c.customers_id, c.first_name, c.surname FROM orders_items oi LEFT JOIN orders o ON oi.fk_orders_id = o.orders_id LEFT JOIN items i ON oi.fk_items_id = i.items_id LEFT JOIN customers c ON o.fk_customers_id = c.customers_id");){
			List<OrdersItems> ordersItems = new ArrayList<>();
			while (resultSet.next()) {
				ordersItems.add(modelFromResultSet(resultSet));
			}
			return ordersItems;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public OrdersItems readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders_items ORDER BY orders_items_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	// Creates an ordersitems record in the database
	
	@Override
	public OrdersItems create(OrdersItems ordersItems) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders_items(fk_items_id, fk_orders_id, quantity) VALUES (?, ?, ?)");) {
			statement.setLong(1, ordersItems.getItems().getItemsId());
			statement.setLong(2, ordersItems.getOrders().getOrderId());
			statement.setLong(3, ordersItems.getQuantity());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public OrdersItems read(Long ordersItemsId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders_items WHERE orders_items_id= ?");) {
			statement.setLong(1, ordersItemsId);
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

	//updates an ordersitems record in the database - not currently used

	@Override
	public OrdersItems update(OrdersItems ordersItems) {
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				PreparedStatement statement = connection
//						.prepareStatement("UPDATE orders_items SET fk_items_id= ?, fk_orders_id=?,quantity=? WHERE orders_items_id = ?");) {
//			statement.setLong(1, ordersItems.getItems().getItemsId());
//			statement.setLong(1, ordersItems.getOrders().getOrderId());
//			statement.setLong(1, ordersItems.getQuantity());
//			statement.executeUpdate();
//			return read(ordersItems.getOrderItemsId());
//		} catch (Exception e) {
//			LOGGER.debug(e);
//			LOGGER.error(e.getMessage());
//		}
		return null;
	}

	//Deletes an ordersitems record in the database
	
	@Override
	public int delete(long ordersItemsId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders_items WHERE orders_items_id= ?");) {
			statement.setLong(1, ordersItemsId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	
}
