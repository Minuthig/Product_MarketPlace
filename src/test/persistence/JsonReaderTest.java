package persistence;

import model.Product;
import persistence.JsonReader;
import model.Marketplace;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Marketplace marketplace = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // @Test
    // void testReaderEmptyMarketplace() {
    // JsonReader reader = new JsonReader("./data/testReaderEmptyMarketplace.json");
    // try {
    // Marketplace marketplace = reader.read();
    // assertEquals(0, marketplace.getAllProducts().size());
    // } catch (IOException e) {
    // fail("Couldn't read from file");
    // }
    // }

    @Test
    void testReaderEmptyMarketplace() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMarketplace.json");
        try {
            Marketplace marketplace = reader.read();
            assertEquals(0, marketplace.getAllProducts().size(), "Expected an empty marketplace.");
        } catch (IOException e) {
            fail("Couldn't read from file: " + e.getMessage());
        }
    }

    @Test
    void testReaderGeneralMarketplace() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMarketplace.json");
        try {
            Marketplace marketplace = reader.read();
            List<Product> products = marketplace.getAllProducts();
            assertEquals(2, products.size());
            checkProduct("Laptop", "High-end gaming laptop", "Electronics", 1200.0, "TechCo", products.get(0));
            checkProduct("Chair", "Comfortable office chair", "Furniture", 150.0, "FurniCo", products.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // Helper method to check that the product matches expected values
    private void checkProduct(String n, String d, String c, double price, String producer, Product product) {
        assertEquals(n, product.getName());
        assertEquals(d, product.getDescription());
        assertEquals(c, product.getCategory());
        assertEquals(price, product.getPrice());
        assertEquals(producer, product.getProducerName());
    }

}