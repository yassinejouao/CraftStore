package metier.models;

import java.util.ArrayList;
import java.util.List;

import metier.entities.Client;
import metier.entities.Product;

public class AddOrderModel {
	private List<Product> products = new ArrayList<Product>();
	private List<Client> clients = new ArrayList<Client>();

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
}
