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
import com.qa.ims.utils.DBUtils;

public class OrdersDAOTest {
	private final OrdersDAO DAO = new OrdersDAO();
	Customer customer = new Customer(1L, "jordan", "harrison");
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		
		final Orders created = new Orders(1L, customer, 6L);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Orders> expected = new ArrayList<>();
		expected.add(new Orders(1L, customer, 6L));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Orders(1L, customer, 6L), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ordersId = 1L;
		assertEquals(new Orders(ordersId, customer, 6L), DAO.read(ordersId));
	}

	@Test
	public void testUpdate() {
		final Orders updated = new Orders(1L, customer, 6L);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(0));
	}
	
	@Test
	public void testReadException() {
	DAO.delete(0L);
	assertNull(DAO.read(0L));
	}
	
//	@Test
//	public void testReadAllException() {
//		
//		DAO.delete(2L); DAO.delete(1L);
//		assertNull(DAO.readAll());
//	}
	@Test
	public void testReadLatestException() {
		DAO.delete(2L);
		DAO.delete(1L);
		assertNull(DAO.readLatest());
	}
	
	@Test
	public void testCreateException() {
		final Customer customer = new Customer(1111111111111111111L, "4567891123123", "perrins");
		final Orders order= new Orders(1111111111111111111L, customer);
		assertNull(DAO.create(order));
	}
	@Test
	public void testUpdateException() {
		DAO.delete(1L);
		final Customer customer = new Customer(-1111111111111111111L, "4567891123123", "perrins");
		final Orders order= new Orders();
		assertNull(DAO.update(order));
	}
//	@Test
//	public void testDeleteException() {
//		System.out.println(DAO.readAll());
//		DAO.delete(1L);
//		System.out.println(DAO.readAll());
//		assertEquals(0, DAO.delete(1L));
//		System.out.println(DAO.readAll());
//	}

}
