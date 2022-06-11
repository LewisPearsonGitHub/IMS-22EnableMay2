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
	
	Items items = new Items(1L, 1.2, "car");
	Orders orders = new Orders(1L, customer);
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		
		 final ItemsDAO itemDAO = new ItemsDAO();
		 final CustomerDAO cuDAO = new CustomerDAO();
		 final OrdersDAO orDAO = new OrdersDAO();
		Customer customer = new Customer(1L, "jordan", "harrison");
		
		Items items = new Items(1L, 1L, "cat");
		Orders orders = new Orders(1L, customer);
	itemDAO.create(items); cuDAO.create(customer); orDAO.create(orders);
		final OrdersItems created = new OrdersItems(2L, new Items(1L, "car"), new Orders(1L, new Customer(1L, "jordan", "harrison")), 1L);
		assertEquals(created, DAO.create(created));
		
	}

	@Test
	public void testReadAll() {
		List<OrdersItems> expected = new ArrayList<>();
		expected.add(new OrdersItems(1L,items, orders, 5L));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		System.out.println(DAO.readLatest());
		assertEquals(new OrdersItems(1L, items, orders, 5L), DAO.readLatest());
		
	}

	@Test
	public void testRead() {
		final long ordersItemsId = 1L;
		assertEquals(new OrdersItems(1L, items, orders, 1L), DAO.read(ordersItemsId));
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

	@Test
	public void testReadAllException() {
		
		DAO.delete(2L); DAO.delete(1L);
		assertNull(DAO.readAll());
	}
	@Test
	public void testReadLatestException() {
		DAO.delete(2L);
		DAO.delete(1L);
		assertNull(DAO.readLatest());
	}
	
	@Test
	public void testCreateException() {
		
		assertNull(DAO.create(new OrdersItems(1L, new Items(1L,1L, "lewispearsonlewispearsonlewisplewispearsonlewispearsonlewispearsonearsonlewispearsonlewispearsonlewispearson"),orders,1L)));
	}
	@Test
	public void testDeleteException() {
		
		
		DAO.delete(1L);
		
		assertEquals(0,DAO.delete(1L));
	}
}

