package metier.entities;

public class Orderitem {
	private Long idOrder;
	private Long idProduct;
	private Long quantity;
	private Double total;

	public Orderitem() {
	}

	public Orderitem(Long idOrder, Long idProduct, Long quantity, Double total) {
		this.idOrder = idOrder;
		this.idProduct = idProduct;
		this.quantity = quantity;
		this.total = total;
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
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

}
