package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DBUtils;

public class CostsDAO {
	public static final Logger LOGGER = LogManager.getLogger();
	
	
	@Override
	public Items modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long itemsId = resultSet.getLong("items_id");
		double value = resultSet.getDouble("value");
		String itemName = resultSet.getString("item_name");
		return new Items(itemsId, value, itemName);
		
	}
		
		@Override
		public List<Items> readAll() {
			try (Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
				List<Items> items = new ArrayList<>();
				while (resultSet.next()) {
					items.add(modelFromResultSet(resultSet));
				}
				return items;
			} catch (SQLException e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			return new ArrayList<>();
		
	}
}
