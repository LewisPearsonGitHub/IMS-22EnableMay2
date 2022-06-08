package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Orders {
	private Long orderId;
	private Customer customer;
	
	public Customer getCustomerID() {
		return customer;
	}
	
	public Orders(Long orderId, Customer customer) {
		this.orderId = orderId;
		this.customer = customer;
	}
	public Orders(Customer customer) {
		this.customer = customer;
	}




	public Long getOrderId() {
		return orderId;
	}



	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	@Override
	public String toString() {
		return "id=" + orderId + ", customer=" + customer + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(customer, orderId);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		return Objects.equals(customer, other.customer) && Objects.equals(orderId, other.orderId);
	}
	
	
	
}
