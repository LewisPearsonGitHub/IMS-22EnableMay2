package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.Utils;

public class ItemsController implements CrudController<Items>{
	public static final Logger LOGGER = LogManager.getLogger();

	private ItemsDAO itemsDAO;
	private Utils utils;

	public ItemsController(ItemsDAO itemsDAO, Utils utils) {
		super();
		this.itemsDAO = itemsDAO;
		this.utils = utils;
	}

	/**
	 * Reads all Items to the logger
	 */
	@Override
	public List<Items> readAll() {
		List<Items> items = itemsDAO.readAll();
		for (Items item : items) {
			LOGGER.info(item);
		}
		return items;
	}

	/**
	 * Creates a Items by taking in user input
	 */
	@Override
	public Items create() {
		LOGGER.info("Please enter an item value (XX.XX)");
		double value = utils.getDouble();
		LOGGER.info("Please enter an item name");
		String itemName = utils.getString();
		Items items = itemsDAO.create(new Items(value, itemName));
		LOGGER.info("Item created");
		return items;
	}

	/**
	 * Updates an existing Items by taking in user input
	 */
	@Override
	public Items update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long itemsId = utils.getLong();
		LOGGER.info("Please enter a value (XX.XX)");
		double value = utils.getDouble();
		LOGGER.info("Please enter an item name");
		String itemName = utils.getString();
		Items items = itemsDAO.update(new Items(itemsId, value, itemName));
		LOGGER.info("Items Updated");
		return items;
	}

	/**
	 * Deletes an existing Items by the id of the Items
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long itemsId = utils.getLong();
		return itemsDAO.delete(itemsId);
}
}
