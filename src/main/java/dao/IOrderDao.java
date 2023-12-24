package dao;

import java.util.List;

import metier.entities.Order;
import metier.entities.Status;

public interface IOrderDao {
	public Order save(Order o);

	public List<Order> getAllOrders();

	public Order getOrder(Long id);

	public void updateStatus(Long id, Status status);

	public void updateTotal(Long id, Double total);

	public void deleteOrder(Long id);

}
