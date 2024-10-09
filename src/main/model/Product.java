package model;

// Represents a Product with a name, description, category, price and producer name
public class Product {
    private String name;            // name of the product
    private String description;     // description of the product
    private String category;        // the category (Eg: Clothing, Electronics, Furniture) of the product
    private double price;           // the price of the product
    private String producerName;    // the name of the producer of the product

    // REQUIRES: The name, description, category and producer name of the product must not be empty,
    //           the price should be >= 0.
    // EFFECTS: creates a product with a name, description, category, price and producer name
    public Product(String name, String description, String category, double price, String producerName) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.producerName = producerName;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getProducerName() {
        return producerName;
    }
    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }


}
