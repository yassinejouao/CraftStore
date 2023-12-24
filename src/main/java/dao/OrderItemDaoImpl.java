package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import metier.entities.Orderitem;
import metier.models.orderDetailsModel;

public class OrderItemDaoImpl implements IOrderitemDao {
	private static final Logger LOGGER = Logger.getLogger(OrderItemDaoImpl.class);

	@Override
	public void save(Orderitem oi) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement psgetPriceProduct = connection
					.prepareStatement("SELECT * FROM products WHERE idProduct = ?");
			psgetPriceProduct.setLong(1, oi.getIdProduct());
			ResultSet rs = psgetPriceProduct.executeQuery();
			if (rs.next()) {
				PreparedStatement ps = connection
						.prepareStatement("INSERT INTO orderitems (idOrder,idProduct,quantity,total) VALUES (?,?,?,?)");
				ps.setLong(1, oi.getIdOrder());
				ps.setLong(2, oi.getIdProduct());
				ps.setLong(3, oi.getQuantity());
				ps.setDouble(4, (oi.getQuantity() * rs.getDouble("priceProduct")));
				ps.executeUpdate();
				ps.close();
				oi.setTotal((oi.getQuantity() * rs.getDouble("priceProduct")));
			}
			psgetPriceProduct.close();
			LOGGER.info("save orders items");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}
	}

	@Override
	public List<orderDetailsModel> getOrderItemsByIdOrder(Long id) {
		List<orderDetailsModel> orderitemsDetails = new ArrayList<orderDetailsModel>();
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement Ps = connection.prepareStatement(
					"SELECT o.idOrder, o.dateOrder,o.totalOrder,c.idClient,c.nameClient, p.idProduct, p.nameProduct, oi.quantity,oi.total FROM Orders o JOIN OrderItems oi ON o.idOrder = oi.idOrder JOIN Products p ON oi.idProduct = p.idProduct JOIN Clients c ON o.idClient = c.idClient WHERE o.idOrder =?");
			Ps.setLong(1, id);
			ResultSet rs = Ps.executeQuery();
			while (rs.next()) {
				orderDetailsModel od = new orderDetailsModel();
				od.setIdOrder(rs.getLong("idOrder"));
				od.setDateOrder(rs.getDate("dateOrder"));
				od.setTotalOrder(rs.getDouble("totalOrder"));
				od.setIdClient(rs.getLong("idClient"));
				od.setNameClient(rs.getString("nameClient"));
				od.setIdProduct(rs.getLong("idProduct"));
				od.setNameProduct(rs.getString("nameProduct"));
				od.setQuantity(rs.getLong("quantity"));
				od.setTotal(rs.getDouble("total"));
				orderitemsDetails.add(od);
			}
			Ps.close();
			LOGGER.info("get order items by id order");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}
		return orderitemsDetails;
	}

	@Override
	public void deleteOrderItems(Long id) {

		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM orderitems WHERE idOrder = ?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
			LOGGER.info("delete order items by order id");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}
	}

}
