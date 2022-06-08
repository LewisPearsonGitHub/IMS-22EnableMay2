package com.qa.ims.persistence.domain;

public class Costs {
	private Long orderId;
	private double cost;
	
	
	public Costs(Long orderId, double cost) {
		super();
		this.orderId = orderId;
		this.cost = cost;
	}
	public Costs() {
		// TODO Auto-generated constructor stub
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "Costs [orderId=" + orderId + ", cost=" + cost + "]";
	}
	
	
	
}
