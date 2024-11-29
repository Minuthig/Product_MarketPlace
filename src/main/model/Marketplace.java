package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a list of products displayed by several producers.
public class Marketplace implements Writable {
    private List<Product> products;

    // EFFECTS: initializes an empty list of products
    public Marketplace() {
        this.products = new ArrayList<>();
    }

    // REQUIRES: product != null
    // MODIFIES: this
    // EFFECTS: adds a product into the list of products
    public void addProduct(Product product) {
        this.products.add(product);
        EventLog.getInstance().logEvent(new Event("Product added: " + product.getName()));
    }

    // REQUIRES: product != null
    // MODIFIES: this
    // EFFECTS: removes a product into the list of products
    public void removeProduct(Product product) {
        this.products.remove(product);
        EventLog.getInstance().logEvent(new Event("Product added: " + product.getName()));
    }

    // EFFECTS: Gets the list of all products.
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    // EFFECTS: Clears the Marketplace.
    public void clearMarketplace() {
        products.clear();
        EventLog.getInstance().logEvent(new Event("Marketplace cleared."));
    }

    // EFFECTS: Searches for the name entered and returns a list of products
    // matching the name
    public List<Product> searchByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("products", productsToJson());
        return json;
    }

    // EFFECTS: returns products as a JSON array
    private JSONArray productsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Product p : products) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: Searches for the category entered and returns a list of products
    // matching the category
    public List<Product> searchByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }
}