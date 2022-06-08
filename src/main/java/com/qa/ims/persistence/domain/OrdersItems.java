package com.qa.ims.persistence.domain;

public class OrdersItems {
	private Long orderItemsId;
	private Items items;
	private Orders orders;
	private Long quantity;
	
	public OrdersItems(Items items, Orders orders, Long quantity) {
		this.setItems(items);
		this.setOrders(orders);
		this.setQuantity(quantity);
		
	}
	public OrdersItems(Long orderItemsId, Items items, Orders orders, Long quantity) {
		this.setOrderItemsId(orderItemsId);
		this.setItems(items);
		this.setOrders(orders);
		this.setQuantity(quantity);
	}
	public Long getOrderItemsId() {
		return orderItemsId;
	}
	public void setOrderItemsId(Long orderItemsId) {
		this.orderItemsId = orderItemsId;
	}
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrdersItems [orderItemsId=" + orderItemsId + ", items=" + items + ", orders=" + orders + ", quantity="
				+ quantity + "]";
	}
	
	
	
}
