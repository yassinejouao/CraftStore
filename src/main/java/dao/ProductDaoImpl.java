package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import metier.entities.Product;

public class ProductDaoImpl implements IProductDao {
	private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);

	@Override
	public Product save(Product p) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement PsInsertProduct = connection
					.prepareStatement("INSERT INTO products (nameProduct,priceProduct,stock) VALUES (?,?,?)");
			PsInsertProduct.setString(1, p.getNameProduct());
			PsInsertProduct.setDouble(2, p.getPriceProduct());
			PsInsertProduct.setLong(3, p.getStock());
			PsInsertProduct.executeUpdate();
			PsInsertProduct.close();
			PreparedStatement PsGetProductId = connection
					.prepareStatement("SELECT MAX(idProduct) AS MAX_ID FROM products");
			ResultSet rs = PsGetProductId.executeQuery();
			if (rs.next()) {
				p.setIdProduct(rs.getLong("MAX_ID"));
			}
			LOGGER.info("save product");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
			return null;
		}

		return p;
	}

	@Override
	public List<Product> searchProductByKeyWord(String keyword) {
		List<Product> products = new ArrayList<Product>();
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement PsSearchProduct = connection
					.prepareStatement("SELECT * FROM products WHERE nameProduct LIKE ?");
			PsSearchProduct.setString(1, keyword);
			ResultSet rs = PsSearchProduct.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setIdProduct(rs.getLong("idProduct"));
				p.setNameProduct(rs.getString("nameProduct"));
				p.setPriceProduct(rs.getDouble("priceProduct"));
				p.setStock(rs.getLong("stock"));
				products.add(p);

			}
			PsSearchProduct.close();
			LOGGER.info("search for product by keyword");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}

		return products;
	}

	@Override
	public Product getProduct(Long id) {
		Product p = null;
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement PsSearchProduct = connection.prepareStatement("SELECT * FROM products WHERE idProduct=?");
			PsSearchProduct.setLong(1, id);
			ResultSet rs = PsSearchProduct.executeQuery();
			if (rs.next()) {
				p = new Product();
				p.setIdProduct(rs.getLong("idProduct"));
				p.setNameProduct(rs.getString("nameProduct"));
				p.setPriceProduct(rs.getDouble("priceProduct"));
				p.setStock(rs.getLong("stock"));

			}
			PsSearchProduct.close();
			LOGGER.info("get product by id");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}

		return p;
	}

	@Override
	public Product getProductByName(String name) {
		Product p = null;
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement PsSearchProduct = connection
					.prepareStatement("SELECT * FROM products WHERE nameProduct LIKE ?");
			PsSearchProduct.setString(1, name);
			ResultSet rs = PsSearchProduct.executeQuery();
			if (rs.next()) {
				p = new Product();
				p.setIdProduct(rs.getLong("idProduct"));
				p.setNameProduct(rs.getString("nameProduct"));
				p.setPriceProduct(rs.getDouble("priceProduct"));
				p.setStock(rs.getLong("stock"));

			}
			PsSearchProduct.close();
			LOGGER.info("get product by name");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}

		return p;
	}

	@Override
	public Product updateProduct(Product p) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement PsInsertProduct = connection.prepareStatement(
					"UPDATE products SET nameProduct = ?,priceProduct=?,stock =? WHERE idProduct = ?");
			PsInsertProduct.setString(1, p.getNameProduct());
			PsInsertProduct.setDouble(2, p.getPriceProduct());
			PsInsertProduct.setLong(3, p.getStock());
			PsInsertProduct.setLong(4, p.getIdProduct());
			PsInsertProduct.executeUpdate();
			PsInsertProduct.close();
			LOGGER.info("update product");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}

		return p;
	}

	@Override
	public void deleteProduct(Long id) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement PsInsertProduct = connection.prepareStatement("DELETE FROM products WHERE idProduct = ?");
			PsInsertProduct.setLong(1, id);
			PsInsertProduct.executeUpdate();
			PsInsertProduct.close();
			LOGGER.info("delete product");
		} catch (SQLException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}
	}

}
