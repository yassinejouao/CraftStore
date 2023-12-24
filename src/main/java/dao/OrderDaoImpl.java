package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import metier.entities.Order;
import metier.entities.Status;

public class OrderDaoImpl implements IOrderDao {
	private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

	@Override
	public Order save(Order o) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO orders (idClient,dateOrder,totalOrder,statusOrder) VALUES(?,?,?,?)");
			ps.setLong(1, o.getIdClient());
			Date utilDate = o.getDateOrder();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			ps.setDate(2, sqlDate);
			ps.setDouble(3, o.getTotalOrder());
			ps.setString(4, o.getStatusOrder().name());
			ps.executeUpdate();
			ps.close();
			PreparedStatement PsGetProductId = connection.prepareStatement("SELECT MAX(idOrder) AS MAX_ID FROM orders");
			ResultSet rs = PsGetProductId.executeQuery();
			if (rs.next()) {
				o.setIdOrder(rs.getLong("MAX_ID"));
			}
			LOGGER.info("save order");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
			return null;
		}
		return o;
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<Order>();
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order o = new Order();
				o.setIdOrder(rs.getLong("idOrder"));
				o.setIdClient(rs.getLong("idClient"));
				o.setDateOrder(rs.getDate("dateOrder"));
				o.setTotalOrder(rs.getDouble("totalOrder"));
				String status = rs.getString("statusOrder");
				o.setStatusOrder(Status.valueOf(status));
				orders.add(o);
			}
			ps.close();
			LOGGER.info("get all orders");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public Order getOrder(Long id) {
		Order o = null;
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement Ps = connection.prepareStatement("SELECT * FROM orders WHERE idOrder=?");
			Ps.setLong(1, id);
			ResultSet rs = Ps.executeQuery();
			if (rs.next()) {
				o = new Order();
				o.setIdOrder(rs.getLong("idOrder"));
				o.setIdClient(rs.getLong("idClient"));
				o.setDateOrder(rs.getDate("dateOrder"));
				o.setTotalOrder(rs.getDouble("totalOrder"));
				String status = rs.getString("statusOrder");
				o.setStatusOrder(Status.valueOf(status));
			}
			Ps.close();
			LOGGER.info("get order by id");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}

		return o;
	}

	@Override
	public void updateStatus(Long id, Status status) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement Ps = connection.prepareStatement("UPDATE orders set statusOrder = ? WHERE idOrder=?");
			Ps.setString(1, status.name());
			Ps.setLong(2, id);
			Ps.executeUpdate();
			Ps.close();
			LOGGER.info("update status order");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}
	}

	@Override
	public void updateTotal(Long id, Double total) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement Ps = connection.prepareStatement("UPDATE orders set totalOrder = ? WHERE idOrder=?");
			Ps.setDouble(1, total);
			Ps.setLong(2, id);
			Ps.executeUpdate();
			Ps.close();
			LOGGER.info("update total by id");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}

	}

	@Override
	public void deleteOrder(Long id) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement PsInsertProduct = connection.prepareStatement("DELETE FROM orders WHERE idOrder = ?");
			PsInsertProduct.setLong(1, id);
			PsInsertProduct.executeUpdate();
			PsInsertProduct.close();
			LOGGER.info("delete order by id");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}

	}

}
