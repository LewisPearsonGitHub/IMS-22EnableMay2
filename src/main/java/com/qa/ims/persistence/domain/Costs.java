package com.qa.ims.persistence.domain;

public class Costs {
	private Long orderId;
	private Long cost;
	
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getCost() {
		return cost;
	}
	public void setCost(Long cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "Costs [orderId=" + orderId + ", cost=" + cost + "]";
	}
	
	
	
}
