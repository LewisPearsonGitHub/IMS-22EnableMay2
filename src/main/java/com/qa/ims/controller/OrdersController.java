package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

public class OrdersController implements CrudController<Orders>{
	public static final Logger LOGGER = LogManager.getLogger();
	private OrdersDAO ordersDAO;
	private Utils utils;
	Customer customer = new Customer();

	public OrdersController(OrdersDAO ordersDAO, Utils utils) {
		super();
		this.ordersDAO = ordersDAO;
		this.utils = utils;
	}

	/**
	 * Reads all Orders to the logger
	 */
	@Override
	public List<Orders> readAll() {
		List<Orders> orders = ordersDAO.readAll();
		for (Orders order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates a Orders by taking in user input
	 */
	@Override
	public Orders create() {
		LOGGER.info("Please enter a customerID");
		Long customerId = utils.getLong();
		// customer.setId(customerId);
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.read(customerId);
		Orders orders = ordersDAO.create(new Orders(customer));
		LOGGER.info("Order created");
		return orders;
	}

	/**
	 * Updates an existing Orders by taking in user input
	 */
	@Override
	public Orders update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long ordersId = utils.getLong();
		LOGGER.info("Please enter a customer ID");
		Long customerId = utils.getLong();
		customer.setId(customerId);
		Orders orders = ordersDAO.update(new Orders(ordersId, customer));
		LOGGER.info("Orders Updated");
		return orders;
	}

	/**
	 * Deletes an existing Orders by the id of the Orders
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long ordersId = utils.getLong();
		return ordersDAO.delete(ordersId);
	}
	
}
