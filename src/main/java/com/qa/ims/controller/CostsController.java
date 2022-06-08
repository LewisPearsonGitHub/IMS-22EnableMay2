package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CostsDAO;
import com.qa.ims.persistence.domain.Costs;
import com.qa.ims.utils.Utils;

public class CostsController implements CrudController<Costs> {
	public static final Logger LOGGER = LogManager.getLogger();
	private CostsDAO costsDAO;
	private Utils utils;
	Costs costs = new Costs();
	

	public CostsController(CostsDAO costsDAO, Utils utils) {
		this.costsDAO = costsDAO;
		this.utils = utils;
	}

	@Override
	public List<Costs> readAll() {
		List<Costs> costs = costsDAO.readAll();
		for (Costs cost : costs) {
			LOGGER.info(cost);
		}
		return null;
	}

	@Override
	public Costs create() {
		LOGGER.info("Create not possible");
		return null;
	}

	@Override
	public Costs update() {
		LOGGER.info("Update not possible");
		return null;
	}

	@Override
	public int delete() {
		LOGGER.info("Delete not possible");
		return 0;
	}

}
