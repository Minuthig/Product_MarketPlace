package persistence;

import model.Marketplace;
import model.Product;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*
 * Code is taken and modified from the JsonSerializationDemo code.
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

 // Represents a writer that writes JSON representation of marketplace to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;


    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of marketplace to file
    public void write(Marketplace marketplace) {
        JSONObject json = marketplaceToJson(marketplace);
        saveToFile(json.toString(TAB));
    }

    // EFFECTS: converts marketplace to JSON object
    private JSONObject marketplaceToJson(Marketplace marketplace) {
        JSONObject json = new JSONObject();
        json.put("products", productsToJson(marketplace));
        return json;
    }

    // EFFECTS: converts products in the marketplace to a JSON array
    private JSONArray productsToJson(Marketplace marketplace) {
        JSONArray jsonArray = new JSONArray();
        for (Product product : marketplace.getAllProducts()) {
            jsonArray.put(productToJson(product));
        }
        return jsonArray;
    }

    // EFFECTS: converts a product to a JSON object
    private JSONObject productToJson(Product product) {
        JSONObject json = new JSONObject();
        json.put("name", product.getName());
        json.put("description", product.getDescription());
        json.put("category", product.getCategory());
        json.put("price", product.getPrice());
        json.put("producerName", product.getProducerName());
        json.put("reviews", reviewsToJson(product));
        return json;
    }

    // EFFECTS: converts reviews of a product to a JSON array
    private JSONArray reviewsToJson(Product product) {
        JSONArray jsonArray = new JSONArray();
        product.getReviews().forEach(review -> {
            JSONObject json = new JSONObject();
            json.put("rating", review.getRating());
            json.put("comment", review.getComment());
            jsonArray.put(json);
        });
        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }


}
