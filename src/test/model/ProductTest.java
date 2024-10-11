package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ProductTest {
    private Product testProduct;

    @BeforeEach
    void runBefore() {
        testProduct = new Product("Laptop", "HP x360 Spectre", "electronics", 1999.99, "HP");
    }

    @Test
    void testConstructor() {
        assertEquals("Laptop", testProduct.getName());
        assertEquals("HP x360 Spectre", testProduct.getDescription());
        assertEquals("electronics", testProduct.getCategory());
        assertEquals(1999.99, testProduct.getPrice());
        assertEquals("HP", testProduct.getProducerName());
    }

    @Test
    void testSetPrice() {
        testProduct.setPrice(1699.99);
        assertEquals(1699.99, testProduct.getPrice());

        testProduct.setPrice(-99.99);
        assertNotEquals(-99.99, testProduct.getPrice());
        assertEquals(1699.99, testProduct.getPrice());

    }

    @Test
    void testToString() {
        assertTrue( testProduct.toString().contains("Product: Laptop, Description: HP x360 Spectre, Price: $1999.99, Category: electronics, Producer: HP"));
    }


}
