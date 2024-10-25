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
    void testSetters() {
        testProduct.setName("Gaming Laptop");
        assertEquals("Gaming Laptop", testProduct.getName());

        testProduct.setDescription("Gaming Laptop for all ages");
        assertEquals("Gaming Laptop for all ages", testProduct.getDescription());

        testProduct.setCategory("Gaming");
        assertEquals("Gaming", testProduct.getCategory());

        testProduct.setProducerName("ASUS");
        assertEquals("ASUS", testProduct.getProducerName());

    }

    @Test
    void testAddReviewValid() {
        testProduct.addReview(5, "Excellent!");
        assertEquals(1, testProduct.getReviewCount());
        assertEquals(5, testProduct.getReviews().get(0).getRating());
        assertEquals("Excellent!", testProduct.getReviews().get(0).getComment());
    }

    @Test
    void testAddMultipleReviews() {
        testProduct.addReview(4, "Very good");
        testProduct.addReview(3, "Average");
        assertEquals(2, testProduct.getReviewCount());
        assertEquals(4, testProduct.getReviews().get(0).getRating());
        assertEquals("Very good", testProduct.getReviews().get(0).getComment());
        assertEquals(3, testProduct.getReviews().get(1).getRating());
        assertEquals("Average", testProduct.getReviews().get(1).getComment());
    }

    @Test
    void testToString() {
        assertTrue(testProduct.toString().contains(
                "Product: Laptop, Description: HP x360 Spectre, Price: $1999.99, Category: electronics, Producer: HP"));
    }

}