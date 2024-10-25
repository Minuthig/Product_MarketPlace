package persistence;

import model.Product;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.Marketplace;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
        }
    }

    @Test
    void testWriterEmptyMarketplace() {
        try {
            Marketplace marketplace = new Marketplace();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMarketplace.json");
            writer.open();
            writer.write(marketplace);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMarketplace.json");
            marketplace = reader.read();
            assertEquals(0, marketplace.getAllProducts().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMarketplace() {
        try {
            Marketplace marketplace = new Marketplace();
            marketplace.addProduct(new Product("Laptop", "High-end gaming laptop", "Electronics", 1200.0, "TechCo"));
            marketplace.addProduct(new Product("Chair", "Comfortable office chair", "Furniture", 150.0, "FurniCo"));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMarketplace.json");
            writer.open();
            writer.write(marketplace);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMarketplace.json");
            marketplace = reader.read();
            List<Product> products = marketplace.getAllProducts();
            assertEquals(2, products.size());
            checkProduct("Laptop", "High-end gaming laptop", "Electronics", 1200.0, "TechCo", products.get(0));
            checkProduct("Chair", "Comfortable office chair", "Furniture", 150.0, "FurniCo", products.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // Helper method to check that the product matches expected values
    private void checkProduct(String name, String description, String category, double price, String producer,
            Product product) {
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(category, product.getCategory());
        assertEquals(price, product.getPrice());
        assertEquals(producer, product.getProducerName());
    }
}
