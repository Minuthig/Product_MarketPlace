package ui;

import java.util.List;
import java.util.Scanner;
import model.Product;
import model.Marketplace;

/**
 * Console-based user interface for the marketplace application.
 */
public class MarketplaceApp {
    private Marketplace marketplace;
    private Scanner scanner;

    /**
     * Initializes the application with a new marketplace and scanner for user
     * input.
     */
    public MarketplaceApp() {
        marketplace = new Marketplace();
        scanner = new Scanner(System.in);
    }

    /**
     * Main method to run the marketplace application.
     */
    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    viewAllProducts();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    submitReview();
                    break;
                case 4:
                    removeProduct();
                    break;
                case 0:
                    System.out.println("Thank you for using the marketplace!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    // EFFECTS: Prints the main menu options for the user.
    private void printMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1 - View all products");
        System.out.println("2 - Add a new product");
        System.out.println("3 - Submit Review");
        System.out.println("4 - Remove a product");
        System.out.println("0 - Exit");
        System.out.print("Select an option: ");
    }

    
    // EFFECTS: Displays all products in the marketplace.
    private void viewAllProducts() {
        if (marketplace.getAllProducts().isEmpty()) {
            System.out.println("No products available.");
        } else {
            marketplace.getAllProducts().forEach(System.out::println);
        }
    }

    // EFFECTS: Adds a product to the marketplace by gathering details from the user.
    private void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter product category: ");
        String category = scanner.nextLine();
        System.out.print("Enter producer name: ");
        String producer = scanner.nextLine();

        Product product = new Product(name, description, category, price, producer);
        marketplace.addProduct(product);
        System.out.println("Product added successfully.");
    }

    
    // Prompts the user to submit a review for a specific product.
     // 
    private void submitReview() {
        // effects: collects review details and adds the review to the specified product
        System.out.print("Enter product name to review: ");
        String productName = scanner.nextLine();
        System.out.print("Enter your rating (1-5): ");
        int rating = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter your review comment: ");
        String comment = scanner.nextLine();
        
        Product product = marketplace.searchByName(productName).get(0); 
        product.addReview(rating, comment);
        System.out.println("Review submitted successfully!");
    }


    // EFFECTS: Removes a product from the marketplace by name.
    private void removeProduct() {
        System.out.print("Enter the name of the product to remove: ");
        String name = scanner.nextLine();
        
        List<Product> productsFound = marketplace.searchByName(name);
        
        if (!productsFound.isEmpty()) {
            Product productToRemove = productsFound.get(0);
            marketplace.removeProduct(productToRemove); 
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }
}
