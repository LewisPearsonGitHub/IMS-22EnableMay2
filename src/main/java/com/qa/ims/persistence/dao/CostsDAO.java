package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Costs;
import com.qa.ims.utils.DBUtils;

public class CostsDAO implements Dao<Costs> {
	public static final Logger LOGGER = LogManager.getLogger();
	
	
	@Override
	public Costs modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long ordersId = resultSet.getLong("fk_orders_id");
		double cost = resultSet.getDouble("Cost");
		
		
		return new Costs(ordersId, cost);
		}
		
		@Override
		public List<Costs> readAll() {
			try (Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT sum(`value`*quantity) AS 'Cost', fk_orders_id FROM orders_items oi JOIN items i ON oi.fk_items_id = i.items_id GROUP BY fk_orders_id ");) {
				List<Costs> costs= new ArrayList<>();
				while (resultSet.next()) {
					costs.add(modelFromResultSet(resultSet));
				}
				return costs;
			} catch (SQLException e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			return new ArrayList<>();
		
	}

		@Override
		public Costs read(Long id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Costs create(Costs t) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Costs update(Costs t) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int delete(long id) {
			// TODO Auto-generated method stub
			return 0;
		}
}
