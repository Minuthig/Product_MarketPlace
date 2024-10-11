package model;

import java.util.ArrayList;
import java.util.List;


// Represents a list of products displayed by several producers.
public class Marketplace {
    private List<Product> products;

    // EFFECTS: initializes an empty list of products
    public void marketplace() {
        this.products = new ArrayList<>();
    }

    // REQUIRES: product != null
    // MODIFIES: this
    // EFFECTS: adds a product into the list of products
    public void addProduct(Product product) {
        this.products.add(product);
    }

    // REQUIRES: product != null
    // MODIFIES: this
    // EFFECTS: removes a product into the list of products
    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    // EFFECTS: Gets the list of all products.
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

}
