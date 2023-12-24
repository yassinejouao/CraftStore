package metier.entities;

public class Client {
	private Long idClient;
	private String nameClient;
	private String emailClient;
	
	public Client() {
		
	}
	public Client(String nameClient,String emailClient) {
		this.nameClient=nameClient;
		this.emailClient=emailClient;
	}
	public Long getIdClient() {
		return idClient;
	}
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	public String getNameClient() {
		return nameClient;
	}
	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}
	public String getEmailClient() {
		return emailClient;
	}
	public void setEmailClient(String emailClient) {
		this.emailClient = emailClient;
	}
	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nameClient=" + nameClient + ", emailClient=" + emailClient + "]";
	}

}
