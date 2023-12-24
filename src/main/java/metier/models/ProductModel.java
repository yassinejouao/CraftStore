package metier.models;

import java.util.ArrayList;
import java.util.List;

import metier.entities.Product;

public class ProductModel {
	private String keyword;
	private List<Product> products = new ArrayList<Product>();

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
