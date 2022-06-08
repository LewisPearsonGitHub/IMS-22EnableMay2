package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.dao.OrdersItemsDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.persistence.domain.OrdersItems;
import com.qa.ims.utils.Utils;

public class OrdersItemsController implements CrudController<OrdersItems> {
	public static final Logger LOGGER = LogManager.getLogger();
	private OrdersItemsDAO ordersItemsDAO;
	private Utils utils;
	Customer customer = new Customer();
	Items items = new Items();
	Orders orders = new Orders();

	public OrdersItemsController(OrdersItemsDAO ordersItemsDAO, Utils utils) {
		super();
		this.ordersItemsDAO = ordersItemsDAO;
		this.utils = utils;
	}

	/**
	 * Reads all Orders to the logger
	 */
	@Override
	public List<OrdersItems> readAll() {
		List<OrdersItems> ordersItems = ordersItemsDAO.readAll();
		for (OrdersItems order : ordersItems) {
			LOGGER.info(order);
		}
		return ordersItems;
	}

	/**
	 * Creates a Orders by taking in user input
	 */
	@Override
	public OrdersItems create() {
		LOGGER.info("Please enter a customer ID");
		Long customersID = utils.getLong();
		LOGGER.info("Please enter an Items ID");
		Long itemsID = utils.getLong();
		LOGGER.info("Please enter an Orders ID");
		Long ordersID = utils.getLong();
		LOGGER.info("Please enter the quantity");
		Long quantity = utils.getLong();
		// customer.setId(customerId);
		OrdersItemsDAO ordersItemsDAO = new OrdersItemsDAO();
		ItemsDAO itemsDAO = new ItemsDAO();
		CustomerDAO customerDAO = new CustomerDAO();
		
		Customer customer = customerDAO.read(customersID);
		Items items = itemsDAO.read(itemsID);
		
		
		Orders orders = new Orders(ordersID, customer);
		
		OrdersItems ordersItems = ordersItemsDAO.create(new OrdersItems(items,orders,quantity));
		LOGGER.info("Added an item to an order");
		return ordersItems;
	}

	/**
	 * Updates an existing Orders by taking in user input
	 */
	@Override
	public OrdersItems update() {
		LOGGER.info("Update not possible");
		return null;
	}

	/**
	 * Deletes an existing Orders by the id of the Orders
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long ordersItemsId = utils.getLong();
		return ordersItemsDAO.delete(ordersItemsId);
	}
}
