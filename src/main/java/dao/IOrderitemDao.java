package dao;

import java.util.List;

import metier.entities.Orderitem;
import metier.models.orderDetailsModel;

public interface IOrderitemDao {
	public void save(Orderitem oi);

	public List<orderDetailsModel> getOrderItemsByIdOrder(Long id);

	public void deleteOrderItems(Long id);
}
