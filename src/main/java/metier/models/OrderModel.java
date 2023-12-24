package metier.models;

import java.util.ArrayList;
import java.util.List;

import metier.entities.Order;

public class OrderModel {
	private List<Order> orders = new ArrayList<Order>();

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
