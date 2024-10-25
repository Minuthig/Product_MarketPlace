package persistancee;

import model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {
    protected void checkProduct(String name, String description, String category, double price, String producer, Product product) {
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(category, product.getCategory());
        assertEquals(price, product.getPrice());
        assertEquals(producer, product.getProducerName());
    }

}
