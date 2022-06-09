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

import com.qa.ims.controller.ItemsController;
import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemsControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private ItemsDAO dao;

	@InjectMocks
	private ItemsController controller;

	@Test
	public void testCreate() {
		final double value= 1.20;
		final String itemName= "bike";
		final Items created = new Items(value, itemName);

		Mockito.when(utils.getDouble()).thenReturn(value);
		Mockito.when(utils.getString()).thenReturn(itemName);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Items> items = new ArrayList<>();
		items.add(new Items(1L, 1.20, "car"));

		Mockito.when(dao.readAll()).thenReturn(items);

		assertEquals(items, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Items updated = new Items(1L, 1.20, "car");

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getDouble()).thenReturn(updated.getValue());
		Mockito.when(this.utils.getString()).thenReturn(updated.getItemName());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getDouble();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final Long itemsId = 1L;

		Mockito.when(utils.getLong()).thenReturn(itemsId);
		Mockito.when(dao.delete(itemsId)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(itemsId);
	}

}
