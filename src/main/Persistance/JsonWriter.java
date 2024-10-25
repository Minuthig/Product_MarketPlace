package Persistance;

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


    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {

    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {

    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of marketplace to file
    public void write(Marketplace marketplace) {

    }

    // EFFECTS: converts marketplace to JSON object
    private JSONObject marketplaceToJson(Marketplace marketplace) {
        return null;
    }

    // EFFECTS: converts products in the marketplace to a JSON array
    private JSONArray productsToJson(Marketplace marketplace) {
        return null;
    }

    // EFFECTS: converts a product to a JSON object
    private JSONObject productToJson(Product product) {
        return null;
    }

    // EFFECTS: converts reviews of a product to a JSON array
    private JSONArray reviewsToJson(Product product) {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        //writer.print(json);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        //writer.close();
    }


}
