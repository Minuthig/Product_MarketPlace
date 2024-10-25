package Persistance;

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

    // EFFECTS: constructs reader to read from the source file
    public JsonReader(String source) {

    }

    // EFFECTS: reads marketplace from file and returns it;
    // throws IOException if an error occurs reading data from the file
    public Marketplace read() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as a string and returns it
    private String readFile(String source) throws IOException {
        return null;
    }

    // EFFECTS: parses marketplace from a JSON object and returns it
    private Marketplace parseMarketplace(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: marketplace
    // EFFECTS: parses products from a JSON object and adds them to the marketplace
    private void addProducts(Marketplace marketplace, JSONObject jsonObject) {

    }

    // MODIFIES: marketplace
    // EFFECTS: parses a product from a JSON object and adds it to the marketplace
    private void addProduct(Marketplace marketplace, JSONObject jsonObject) {

    }

    // MODIFIES: product
    // EFFECTS: parses reviews from a JSON object and adds them to the product
    private void addReviews(Product product, JSONObject jsonObject) {

    }

    // MODIFIES: product
    // EFFECTS: parses a review from a JSON object and adds it to the product
    private void addReview(Product product, JSONObject jsonObject) {

    }

}
