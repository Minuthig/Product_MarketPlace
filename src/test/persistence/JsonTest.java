package persistence;

import model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkProduct(String n, String d, String c, double price, String producer, Product product) {
        assertEquals(n, product.getName());
        assertEquals(d, product.getDescription());
        assertEquals(c, product.getCategory());
        assertEquals(price, product.getPrice());
        assertEquals(producer, product.getProducerName());
    }

}
