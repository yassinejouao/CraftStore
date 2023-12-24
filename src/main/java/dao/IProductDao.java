package dao;

import java.util.List;

import metier.entities.Product;

public interface IProductDao {
	public Product save(Product p);

	public List<Product> searchProductByKeyWord(String keyword);

	public Product getProduct(Long id);

	public Product getProductByName(String name);

	public Product updateProduct(Product p);

	public void deleteProduct(Long id);
}
