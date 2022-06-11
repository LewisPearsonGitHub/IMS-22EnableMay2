package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrdersController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrdersControllerTest {
	@Mock
	private Utils utils;

	@Mock
	private OrdersDAO dao;

	@InjectMocks
	private OrdersController controller;
	
	@Test
	public void testCreate() {
		Customer customer = new Customer(1L, "jordan", "harrison");
		final Long customerId = 1L;
		
		final Orders created = new Orders(customer);
		
		Mockito.when(utils.getLong()).thenReturn(customerId);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		Customer customer = new Customer(1L, "jordan", "harrison");
		List<Orders> orders = new ArrayList<>();
		orders.add(new Orders(1L, customer, 1L));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		
		Customer customer = new Customer(1L, "jordan", "harrison");
		final Orders updated = new Orders(customer);
		
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(utils.getLong()).thenReturn(updated.getCustomer().getId());
		Mockito.when(dao.update(updated)).thenReturn(updated);

		assertEquals(updated, controller.update());

		Mockito.verify(utils, Mockito.times(2)).getLong();
		//Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final Long ordersId = 1L;

		Mockito.when(utils.getLong()).thenReturn(ordersId);
		Mockito.when(dao.delete(ordersId)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ordersId);
	}

}
