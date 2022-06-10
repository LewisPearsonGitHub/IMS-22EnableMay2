package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Orders {
	private Long orderId;
	private Customer customer;
	private Long cost;
	
	public Customer getCustomerID() {
		return customer;
	}
	
	public Orders(Long orderId, Customer customer) {
		this.orderId = orderId;
		this.customer = customer;
	}
	
	public Orders(Long orderId, Customer customer, Long cost) {
		this.orderId = orderId;
		this.customer = customer;
		this.cost = cost;
	}
	public Orders(Customer customer) {
		this.customer = customer;
	}

//	public Long getCost() {
//		return cost;
//	}
//	public void setCost(Long cost) {
//		this.cost = cost;
//	}
//
	public Orders() {
		// TODO Auto-generated constructor stub
	}

	public Long getOrderId() {
		return orderId;
	}



//	public void setOrderId(Long orderId) {
//		this.orderId = orderId;
//	}



	public Customer getCustomer() {
		return customer;
	}



//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", customer=" + customer + ", cost= £" + cost + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cost, customer, orderId);
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
		return Objects.equals(cost, other.cost) && Objects.equals(customer, other.customer)
				&& Objects.equals(orderId, other.orderId);
	}



	
	
	
}
