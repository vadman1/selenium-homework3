package org.example.framework.dataobject;


public class Product {

    private String name;

    private int price;

    private int priceWithGuarantee;

    private static int countProduct = 0;

    public Product(String name, int price, int priceWithGuarantee) {
        this.name = name;
        this.price = price;
        this.priceWithGuarantee = priceWithGuarantee;
    }

    public Product(String name, int price) {
        this(name, price, 0);
    }

    public Product(String name) {
        this(name, 0);
    }

    // удалить
    public Product() {}

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPriceWithGuarantee() {
        return priceWithGuarantee;
    }

    public void setPriceWithGuarantee(int priceWithGuarantee) {
        this.priceWithGuarantee = priceWithGuarantee;
    }

    public String getName() {
        return name;
    }

    public static int getCountProduct() {
        return countProduct;
    }

    public static void setCountProduct(int countProduct) {
        Product.countProduct = countProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", priceWithGuarantee=" + priceWithGuarantee +
                '}';
    }
}
