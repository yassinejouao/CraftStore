package metier.entities;

public class Product {
    private Long idProduct;
    private String nameProduct;
    private Double priceProduct;
    private Long stock;

    public Product() {
    }

    public Product(String nameProduct, Double priceProduct, Long stock) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.stock = stock;
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

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", nameProduct='" + nameProduct + '\'' +
                ", priceProduct=" + priceProduct +
                ", stock=" + stock +
                '}';
    }
}
