package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Items {
	
	private Long itemsId;
	private double value;
	private String itemName;
	
	public Items(double value, String itemName) {
		this.setValue(value);
		this.setItemName(itemName);
	}
	
	public Items (Long itemsId, double value, String itemName) {
		this.setItemsId(itemsId);
		this.setValue(value);
		this.setItemName(itemName);
	}

	public Items() {
		// TODO Auto-generated constructor stub
	}

	public Long getItemsId() {
		return itemsId;
	}

	public void setItemsId(Long itemsId) {
		this.itemsId = itemsId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Override
	public String toString() {
		return "id:" + itemsId + " value : £" + value + " item name:" + itemName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemName, itemsId, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Items other = (Items) obj;
		return Objects.equals(itemName, other.itemName) && Objects.equals(itemsId, other.itemsId)
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	
	
	
	
}
