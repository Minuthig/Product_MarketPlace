package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MarketplaceTest {
    private Marketplace testMarketplace;
    private Product laptop;
    private Product dress;
    private Product lipstick;


    @BeforeEach
    void runBefore() {
        testMarketplace = new Marketplace();
        laptop = new Product("Laptop", "HP x360 Spectre", "electronics", 1999.99, "HP");
        dress = new Product("Dress", "Blue,formal,knee-length, A-line dress", "clothing", 40.00, "Aritzia");
        lipstick = new Product("Lipstick", "Red, 426, Raisin Rage", "cosmetics", 20.99, "Revlon");
    }

    @Test
    void testAddProduct() {
        testMarketplace.addProduct(laptop);
        List<Product> products = testMarketplace.getAllProducts();
        assertTrue(products.contains(laptop));
    }

    @Test
    void testAddMultipleProduct() {
        testMarketplace.addProduct(laptop);
        testMarketplace.addProduct(dress);
        testMarketplace.addProduct(lipstick);
        List<Product> products = testMarketplace.getAllProducts();
        assertTrue(products.contains(dress));
    }

    @Test
    void testRemoveProduct() {
        testMarketplace.addProduct(laptop);
        testMarketplace.addProduct(dress);
        testMarketplace.removeProduct(laptop);

        List<Product> products = testMarketplace.getAllProducts();
        
        assertFalse(products.contains(laptop));
        assertTrue(products.contains(dress));
    }

}