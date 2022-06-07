package com.qa.ims.persistence.domain;

public class Items {
	
	private Long items_id;
	private double value;
	private String item_name;
	
	public Items(double value, String item_name) {
		this.setValue(value);
		this.setItemName(item_name);
	}
	
	public Items (Long items_id, double value, String item_name) {
		this.setItemsId(items_id);
		this.setValue(value);
		this.setItemName(item_name);
	}

	public Long getItemsId() {
		return items_id;
	}

	public void setItemsId(Long items_id) {
		this.items_id = items_id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getItemName() {
		return item_name;
	}

	public void setItemName(String item_name) {
		this.item_name = item_name;
	}
	
	@Override
	public String toString() {
		return "id:" + items_id + " value : £" + value + " item name:" + item_name;
	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(item_name, items_id, value);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Items other = (Items) obj;
//		return Objects.equals(item_name, other.item_name) && Objects.equals(items_id, other.items_id)
//				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
//	}	
	
	
	
}
