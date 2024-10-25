package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ReviewTest {
    private Review testReview;

    @BeforeEach
    void setUp() {
        testReview = new Review(4, "Great product!");
    }

    @Test
    void testConstructorValidInputs() {
        Review newReview = new Review(5, "Excellent!");
        assertEquals(5, newReview.getRating());
        assertEquals("Excellent!", newReview.getComment());
    }

    @Test
    void testGetRating() {
        assertEquals(4, testReview.getRating());
    }

    @Test
    void testGetComment() {
        assertEquals("Great product!", testReview.getComment());
    }

    @Test
    void testSetRatingValid() {
        testReview.setRating(3);
        assertEquals(3, testReview.getRating());
    }

    @Test
    void testSetComment() {
        testReview.setComment("Updated comment");
        assertEquals("Updated comment", testReview.getComment());
    }



 
}
