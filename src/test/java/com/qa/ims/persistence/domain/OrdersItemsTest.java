package com.qa.ims.persistence.domain;
import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
public class OrdersItemsTest {
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(OrdersItems.class).verify();
	}
}
