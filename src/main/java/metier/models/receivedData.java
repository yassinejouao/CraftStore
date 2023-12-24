package metier.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class receivedData {

	@JsonProperty("idClient")

	private String idClient;
	@JsonProperty("data")

	private List<orderData> data;

	public String getIdClient() {
		return idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	public List<orderData> getOrder() {
		return data;
	}

	public void setOrder(List<orderData> order) {
		this.data = order;
	}

	public static class orderData {
		private String productName;
		private String quantity;

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getQuantity() {
			return quantity;
		}

		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}

	}
}
