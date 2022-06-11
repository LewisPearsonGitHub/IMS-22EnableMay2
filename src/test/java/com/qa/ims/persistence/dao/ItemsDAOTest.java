package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DBUtils;

public class ItemsDAOTest {
	private final ItemsDAO DAO = new ItemsDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Items created = new Items(2L, 5, "dog");
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Items> expected = new ArrayList<>();
		expected.add(new Items(1L, 1.2, "car"));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Items(1L, 1.20, "car"), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long itemsId = 1L;
		assertEquals(new Items(itemsId, 1.20, "car"), DAO.read(itemsId));
	}

	@Test
	public void testUpdate() {
		final Items updated = new Items(1L, 5, "cat");
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
//			DAO.delete(2L); DAO.delete(1L);
//		
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
		final Items item= new Items(1L, 1.003,"1234567891123456789112345678911234567891123123");
		assertNull(DAO.create(item));
	}
	@Test
	public void testUpdateException() {
		final Items item = new Items(1L, 1.003, "1234567891123456789112345678911234567891123123");
		assertNull(DAO.update(item));
	}
//	@Test
//	public void testDeleteException() {
//		System.out.println(DAO.readAll());
//		DAO.delete(1L);
//		System.out.println(DAO.readAll());
//		assertNull(DAO.delete(1L));
//		
//	}

}
