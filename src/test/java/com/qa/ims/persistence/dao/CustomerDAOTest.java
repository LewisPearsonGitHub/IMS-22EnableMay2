package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOTest {

	private final CustomerDAO DAO = new CustomerDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Customer created = new Customer(2L, "chris", "perrins");
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(1L, "jordan", "harrison"));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Customer(1L, "jordan", "harrison"), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long Customers_Id = 1L;
		assertEquals(new Customer(Customers_Id, "jordan", "harrison"), DAO.read(Customers_Id));
	}

	@Test
	public void testUpdate() {
		final Customer updated = new Customer(1L, "chris", "perrins");
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1L, DAO.delete(1L));
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
		Customer customer= new Customer(1L, "1234567891123456789112345678911234567891123123"," ");
		assertNull(DAO.create(customer));
		
		
	}
	@Test
	public void testUpdateException() {
		final Customer updated = new Customer(1L, "1234567891123456789112345678911234567891123123", "perrins");
		assertNull(DAO.update(updated));
	}
//	@Test
//	public void testDeleteException() {
//		System.out.println(DAO.readAll());
//		DAO.delete(1L);
//		System.out.println(DAO.readAll());
//		DAO.delete(1L);
//		System.out.println(DAO.readAll());
//		assertEquals(0,DAO.delete(1L));
//	}
}
