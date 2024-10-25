package model;

public class Review {
    private int rating;
    private String comment;


    // REQUIRES: rating >= 1 && rating <= 5
    // MODIFIES: this
    // EFFECTS: initializes the review with specified rating and comment
    public Review(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
