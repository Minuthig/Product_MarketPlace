package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

/*
 * Code is taken and modified from the JsonSerializationDemo code.
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

// Represents a reader that reads a marketplace from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads marketplace from file and returns it;
    // throws IOException if an error occurs reading data from the file
    public Marketplace read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMarketplace(jsonObject);
    }

    // EFFECTS: reads source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses marketplace from a JSON object and returns it
    private Marketplace parseMarketplace(JSONObject jsonObject) {
        Marketplace marketplace = new Marketplace();
        addProducts(marketplace, jsonObject);
        return marketplace;
    }

    // MODIFIES: marketplace
    // EFFECTS: parses products from a JSON object and adds them to the marketplace
    private void addProducts(Marketplace marketplace, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("products");
        for (Object json : jsonArray) {
            JSONObject nextProduct = (JSONObject) json;
            addProduct(marketplace, nextProduct);
        }
    }

    // MODIFIES: marketplace
    // EFFECTS: parses a product from a JSON object and adds it to the marketplace
    private void addProduct(Marketplace marketplace, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        String category = jsonObject.getString("category");
        double price = jsonObject.getDouble("price");
        String producerName = jsonObject.getString("producerName");

        Product product = new Product(name, description, category, price, producerName);
        marketplace.addProduct(product);

        addReviews(product, jsonObject);
    }

    // MODIFIES: product
    // EFFECTS: parses reviews from a JSON object and adds them to the product
    private void addReviews(Product product, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("reviews");
        for (Object json : jsonArray) {
            JSONObject nextReview = (JSONObject) json;
            addReview(product, nextReview);
        }
    }

    // MODIFIES: product
    // EFFECTS: parses a review from a JSON object and adds it to the product
    private void addReview(Product product, JSONObject jsonObject) {
        int rating = jsonObject.getInt("rating");
        String comment = jsonObject.getString("comment");
        product.addReview(rating, comment);
    }

}
