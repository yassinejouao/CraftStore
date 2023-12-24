package metier.models;

import java.util.Date;

public class orderDetailsModel {
	private Long idOrder;
	private Date dateOrder;
	private Double totalOrder;
	private Long idProduct;
	private String nameProduct;
	private Long quantity;
	private Double total;
	private Long idClient;
	private String nameClient;

	public orderDetailsModel() {
		super();
	}

	public orderDetailsModel(Long idOrder, Date dateOrder, Double totalOrder, Long idProduct, String nameProduct,
			Long quantity, Double total, Long idClient, String nameClient) {
		super();
		this.idOrder = idOrder;
		this.dateOrder = dateOrder;
		this.totalOrder = totalOrder;
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.quantity = quantity;
		this.total = total;
		this.idClient = idClient;
		this.nameClient = nameClient;
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
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

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
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

}
