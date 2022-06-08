package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.controller.Action;
import com.qa.ims.controller.CrudController;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemsController;
import com.qa.ims.controller.OrdersController;
import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

public class IMS {

	public static final Logger LOGGER = LogManager.getLogger();

	private final CustomerController customers;
	private final ItemsController items;
	private final Utils utils;
	private final OrdersController orders;

	public IMS() {
		this.utils = new Utils();
		final CustomerDAO custDAO = new CustomerDAO();
		final ItemsDAO itDAO = new ItemsDAO();
		final OrdersDAO orDAO = new OrdersDAO();
		this.orders = new OrdersController(orDAO, utils);
		this.items = new ItemsController(itDAO, utils);
		this.customers = new CustomerController(custDAO, utils);
	}

	public void imsSystem() {
		LOGGER.info("Welcome to the Inventory Management System!");
		DBUtils.connect();

		Domain domain = null;
		do {
			LOGGER.info("Which entity would you like to use?");
			Domain.printDomains();

			domain = Domain.getDomain(utils);

			domainAction(domain);

		} while (domain != Domain.STOP);
	}

	private void domainAction(Domain domain) {
		boolean changeDomain = false;
		do {

			CrudController<?> active = null;
			switch (domain) {
			case CUSTOMER:
				active = this.customers;
				break;
			case ITEM:
				active = this.items;
				break;
			case ORDER:
				active = this.orders;
				break;
			case STOP:
				return;
			default:
				break;
			}

			LOGGER.info(() ->"What would you like to do with " + domain.name().toLowerCase() + ":");

			Action.printActions();
			Action action = Action.getAction(utils);

			if (action == Action.RETURN) {
				changeDomain = true;
			} else {
				doAction(active, action);
			}
		} while (!changeDomain);
	}

	public void doAction(CrudController<?> crudController, Action action) {
		switch (action) {
		case CREATE:
			crudController.create();
			break;
		case READ:
			crudController.readAll();
			break;
		case UPDATE:
			crudController.update();
			break;
		case DELETE:
			crudController.delete();
			break;
		case RETURN:
			break;
		default:
			break;
		}
	}

}
