package metier.models;

import java.util.ArrayList;
import java.util.List;

import metier.entities.Client;

public class ClientModel {
	private String keyword;
	private List<Client> clients = new ArrayList<Client>();

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

}
