package metier.entities;

import java.util.Date;

public class Order {
	private Long idOrder;
	private Long idClient;
	private Date dateOrder;
	private Double totalOrder;
	private Status statusOrder;

	public Order() {
	}

	public Order(Long idClient, Date dateOrder, Double totalOrder, Status statusOrder) {
		this.idClient = idClient;
		this.dateOrder = dateOrder;
		this.totalOrder = totalOrder;
		this.statusOrder = statusOrder;
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public Double getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(Double totalOrder) {
		this.totalOrder = totalOrder;
	}

	public Status getStatusOrder() {
		return statusOrder;
	}

	public void setStatusOrder(Status statusOrder) {
		this.statusOrder = statusOrder;
	}

}
