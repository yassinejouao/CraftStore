package dao;

import java.util.List;

import metier.entities.Client;

public interface IClientDao {
	public Client save(Client c);

	public List<Client> searchClientByKeyword(String keyword);

	public Client getClient(Long id);

	public Client updateClient(Client c);

	public boolean deleteClient(Long id);
}
