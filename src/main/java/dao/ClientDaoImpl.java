package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import metier.entities.Client;

public class ClientDaoImpl implements IClientDao {
	private static final Logger LOGGER = Logger.getLogger(ClientDaoImpl.class);

	@Override
	public Client save(Client c) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("INSERT INTO clients (nameClient,emailClient) VALUES (?,?)");
			pStatement.setString(1, c.getNameClient());
			pStatement.setString(2, c.getEmailClient());
			pStatement.executeUpdate();
			pStatement.close();
			PreparedStatement ps = connection.prepareStatement("SELECT MAX(idClient) AS MAX_ID FROM clients");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c.setIdClient(rs.getLong("MAX_ID"));
			}
			LOGGER.info("ADD client to database");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
			return null;
		}
		return c;
	}

	@Override
	public List<Client> searchClientByKeyword(String keyword) {
		List<Client> clients = new ArrayList<Client>();
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM clients WHERE nameClient LIKE ?");
			ps.setString(1, keyword);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Client c = new Client();
				c.setIdClient(rs.getLong("idClient"));
				c.setEmailClient(rs.getString("emailClient"));
				c.setNameClient(rs.getString("nameClient"));
				clients.add(c);

			}
			ps.close();
			LOGGER.info("search client by keyword");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}

		return clients;
	}

	@Override
	public Client getClient(Long id) {
		Client c = null;
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM clients WHERE idClient=?");
			pStatement.setLong(1, id);
			ResultSet rs = pStatement.executeQuery();
			if (rs.next()) {
				c = new Client();
				c.setIdClient(rs.getLong("idClient"));
				c.setNameClient(rs.getString("nameClient"));
				c.setEmailClient(rs.getString("emailClient"));
			}
			pStatement.close();
			LOGGER.info("get client by id");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public Client updateClient(Client c) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement Ps = connection
					.prepareStatement("UPDATE clients SET nameClient = ?,emailClient=? WHERE idClient = ?");
			Ps.setString(1, c.getNameClient());
			Ps.setString(2, c.getEmailClient());
			Ps.setLong(3, c.getIdClient());
			Ps.executeUpdate();
			Ps.close();
			LOGGER.info("update client");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}

		return c;
	}

	@Override
	public boolean deleteClient(Long id) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement Ps = connection.prepareStatement("DELETE FROM clients WHERE idClient = ?");
			Ps.setLong(1, id);
			int deleted = Ps.executeUpdate();
			Ps.close();
			LOGGER.info("delete client by id");
			return deleted > 0;
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
			return false;
		}
	}

}
