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

import com.qa.ims.controller.OrdersItemsController;
import com.qa.ims.persistence.dao.OrdersItemsDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.persistence.domain.OrdersItems;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrdersItemsControllerTest {
	@Mock 
	private Utils utils;
	
	@Mock
	private OrdersItemsDAO dao;
	
	@InjectMocks
	private OrdersItemsController controller;
	Customer customer = new Customer(1L, "jordan", "harrison");
	Items items = new Items(1L, 1, "car");
	Orders orders = new Orders(1L, customer, 1L);
	
	
//	@Test
//	public void testCreate() {
//		
//		final Long quantity =1L;
//		
//		final OrdersItems created = new OrdersItems(items, orders, quantity);
//		
//		Mockito.when(utils.getLong()).thenReturn(1L);
//		Mockito.when(dao.create(created)).thenReturn(created);
//
//		assertEquals(created, controller.create());
//		
//		Mockito.verify(utils, Mockito.times(1)).getLong();
//		Mockito.verify(dao, Mockito.times(1)).create(created);
//	}

	@Test
	public void testReadAll() {
		List<OrdersItems> ordersItems = new ArrayList<>();
		ordersItems.add(new OrdersItems(items, orders, 1L));

		Mockito.when(dao.readAll()).thenReturn(ordersItems);

		assertEquals(ordersItems, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

//	@Test
//	public void testUpdate() {
//		OrdersItems updated = new OrdersItems(items, orders, 1L);
//
//		Mockito.when(this.utils.getLong()).thenReturn(1L, 1L);
//		// Mockito.when(this.utils.getString()).thenReturn(updated.);
//		Mockito.when(this.dao.update(updated)).thenReturn(updated);
//
//		assertEquals(updated, this.controller.update());
//
//		Mockito.verify(this.utils, Mockito.times(2)).getLong();
//		//Mockito.verify(this.utils, Mockito.times(2)).getString();
//		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
//	}

	@Test
	public void testDelete() {
		final Long ordersItemsId = 1L;

		Mockito.when(utils.getLong()).thenReturn(ordersItemsId);
		Mockito.when(dao.delete(ordersItemsId)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ordersItemsId);
	}

}
