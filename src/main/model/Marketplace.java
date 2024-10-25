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

    // EFFECTS: Searches for the name entered and returns a list of products matching the name
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
}