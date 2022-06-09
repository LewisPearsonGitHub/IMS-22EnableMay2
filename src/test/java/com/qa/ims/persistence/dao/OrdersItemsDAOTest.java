package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.persistence.domain.OrdersItems;
import com.qa.ims.utils.DBUtils;

public class OrdersItemsDAOTest {
	private final OrdersItemsDAO DAO = new OrdersItemsDAO();
	Customer customer = new Customer(1L, "jordan", "harrison");
	
	Items items = new Items(1L, 1L, "cat");
	Orders orders = new Orders(1L, customer, 1L);
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		
		final OrdersItems created = new OrdersItems(items, orders, 1L);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<OrdersItems> expected = new ArrayList<>();
		expected.add(new OrdersItems(items, orders, 1L));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new OrdersItems(items, orders, 1L), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ordersItemsId = 1L;
		assertEquals(new OrdersItems(ordersItemsId,items, orders, 1L), DAO.read(ordersItemsId));
	}

	@Test
	public void testUpdate() {
		final OrdersItems updated = new OrdersItems(items, orders, 1L);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
	
	@Test
	public void testReadException() {
		DAO.delete(1L);
		assertNull(DAO.read(1L));
	}
}

