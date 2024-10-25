package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import persistence.Writable;

// Represents a Product with a name, description, category, price and producer name
public class Product implements Writable {
    private String name; // name of the product
    private String description; // description of the product
    private String category; // the category (Eg: Clothing, Electronics, Furniture) of the product
    private double price; // the price of the product
    private String producerName; // the name of the producer of the product
    private List<Review> reviews;

    // REQUIRES: The name, description, category and producer name of the product
    // must not be empty,
    // the price should be >= 0.
    // EFFECTS: creates a product with a name, description, category, price and
    // producer name
    public Product(String name, String description, String category, double price, String producerName) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.producerName = producerName;
        this.reviews = new ArrayList<>();
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

    // REQUIRES: price must be >= 0.0
    public void setPrice(double price) {
        if (price >= 0.0) {
            this.price = price;
        }     
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    // REQUIRES: rating >= 1 && rating <= 5
    // MODIFIES: this
    // EFFECTS: adds a review to the product
    public void addReview(int rating, String comment) {
        reviews.add(new Review(rating, comment));
    }


     // EFFECTS: returns the number of reviews
    public int getReviewCount() {
        return reviews.size();
    }

    public List<Review> getReviews() {
        return reviews;
    }


    /*
     * EFFECTS: returns a string representation of product
     */
    @Override
    public String toString() {
        return "Product: " + name + ", Description: " + description + ", Price: $" + price + ", Category: " + category
                + ", Producer: " + producerName;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", description);
        json.put("category", category);
        json.put("price", price);
        json.put("producerName", producerName);
        return json;
    }

    

}
