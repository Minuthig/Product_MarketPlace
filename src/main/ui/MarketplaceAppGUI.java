package ui;

import model.Event;
import model.EventLog;
import model.Marketplace;
import model.Product;
import model.Review;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// Method for the GUI application.
public class MarketplaceAppGUI extends JFrame {
    private static final String JSON_STORE = "./data/marketplace.json";
    private Marketplace marketplace;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JPanel productPanel;
    private JComboBox<String> categoryFilter;

    // EFFECTS: Constructor for the GUI application
    public MarketplaceAppGUI() {
        super("Marketplace Application");
        marketplace = new Marketplace();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLayout(new BorderLayout());

        add(createControlPanel(), BorderLayout.NORTH);
        add(createProductPanel(), BorderLayout.CENTER);
        setJMenuBar(createMenuBar());

        autoLoadMarketplace();
        showSplashScreen();

        // Auto-save when closing the application
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                autoSaveMarketplace();
                printEventLog();
            }
        });
    }

    // EFFECTS: Creates a control panel with buttons for actions
    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Add Product");
        JButton removeButton = new JButton("Remove Product");
        JButton reviewButton = new JButton("Submit/View Review");

        categoryFilter = new JComboBox<>(new String[] { "All", "Electronics", "Clothing", "Makeup", });
        categoryFilter.addActionListener(e -> filterProductsByCategory((String) categoryFilter.getSelectedItem()));

        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        controlPanel.add(reviewButton);
        controlPanel.add(new JLabel("Filter by Category:"));
        controlPanel.add(categoryFilter);

        addButton.addActionListener(e -> addProduct());
        removeButton.addActionListener(e -> removeProduct());
        reviewButton.addActionListener(e -> showReviewScreen());

        return controlPanel;
    }

    // EFFECTS: Creates the product display panel
    private JScrollPane createProductPanel() {
        productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.setPreferredSize(new Dimension(780, 400));
        return scrollPane;
    }

    // EFFECTS: Displays all products in the marketplace
    private void refreshProductPanel() {
        productPanel.removeAll();
        for (Product product : marketplace.getAllProducts()) {
            JLabel productLabel = new JLabel(product.toString());
            productPanel.add(productLabel);
        }
        productPanel.revalidate();
        productPanel.repaint();
    }

    // EFFECTS: Opens a dialog to add a product
    @SuppressWarnings("methodlength")
    private void addProduct() {
        JTextField nameField = new JTextField(10);
        JTextField descriptionField = new JTextField(20);
        JTextField priceField = new JTextField(10);
        JTextField categoryField = new JTextField(10);
        JTextField producerField = new JTextField(10);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Product Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Product Description:"));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Producer Name:"));
        inputPanel.add(producerField);

        int result = JOptionPane.showConfirmDialog(this, inputPanel,
                "Add New Product", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String description = descriptionField.getText();
            double price = Double.parseDouble(priceField.getText());
            String category = categoryField.getText();
            String producer = producerField.getText();

            Product product = new Product(name, description, category, price, producer);
            marketplace.addProduct(product);
            EventLog.getInstance().logEvent(new Event("Added product: " + name));
            refreshProductPanel();
        }
    }

    // EFFECTS: Removes a product from the marketplace
    private void removeProduct() {
        String name = JOptionPane.showInputDialog(this, "Enter the name of the product to remove:");
        List<Product> productsFound = marketplace.searchByName(name);

        if (!productsFound.isEmpty()) {
            Product productToRemove = productsFound.get(0);
            marketplace.removeProduct(productToRemove);
            EventLog.getInstance().logEvent(new Event("Removed product: " + name));
            refreshProductPanel();
            JOptionPane.showMessageDialog(this, "Product removed successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Product not found.");
        }
    }

    // // EFFECTS: Loads the marketplace from a file
    // private void loadMarketplace() {
    // try {
    // marketplace = jsonReader.read();
    // refreshProductPanel();
    // JOptionPane.showMessageDialog(this, "Marketplace loaded successfully.");
    // } catch (IOException e) {
    // JOptionPane.showMessageDialog(this, "Failed to load marketplace.");
    // }
    // }

    // // EFFECTS: Saves the marketplace to a file
    // private void saveMarketplace() {
    // try {
    // jsonWriter.open();
    // jsonWriter.write(marketplace);
    // jsonWriter.close();
    // JOptionPane.showMessageDialog(this, "Marketplace saved successfully.");
    // } catch (FileNotFoundException e) {
    // JOptionPane.showMessageDialog(this, "Failed to save marketplace.");
    // }
    // }

    // Automatically loads the marketplace on startup
    private void autoLoadMarketplace() {
        try {
            marketplace = jsonReader.read();
            refreshProductPanel();
        } catch (IOException e) {
            System.out.println("No saved data found.");
        }
    }

    // Automatically saves the marketplace on exit
    private void autoSaveMarketplace() {
        try {
            jsonWriter.open();
            jsonWriter.write(marketplace);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to save marketplace.");
        }
    }

    // EFFECTS: Searches for the product to add the review
    private void showReviewScreen() {
        String productName = JOptionPane.showInputDialog(this, "Enter the name of the product for reviews:");
        List<Product> productsFound = marketplace.searchByName(productName);

        if (!productsFound.isEmpty()) {
            Product product = productsFound.get(0);
            createReviewDialog(product);
        } else {
            JOptionPane.showMessageDialog(this, "Product not found.");
        }
    }

    // EFFECTS: Creates the dialog for viewing and submitting a review
    @SuppressWarnings("method length")
    private void createReviewDialog(Product product) {
        JDialog reviewDialog = new JDialog(this, "Submit/View Reviews for " + product.getName(), true);
        reviewDialog.setSize(500, 400);
        reviewDialog.setLayout(new BorderLayout());

        JPanel submitReviewPanel = new JPanel(new GridLayout(2, 2));
        JTextField ratingField = new JTextField(10);
        JTextField commentField = new JTextField(20);

        submitReviewPanel.add(new JLabel("Rating (1-5):"));
        submitReviewPanel.add(ratingField);
        submitReviewPanel.add(new JLabel("Comment:"));
        submitReviewPanel.add(commentField);

        JButton submitReviewButton = new JButton("Submit New Review");
        submitReviewButton.addActionListener(e -> {
            try {
                int rating = Integer.parseInt(ratingField.getText());
                String comment = commentField.getText();
                if (rating >= 1 && rating <= 5) {
                    product.addReview(rating, comment);
                    refreshReviewsPanel(product, reviewDialog);
                    JOptionPane.showMessageDialog(reviewDialog, "Review submitted successfully!");
                } else {
                    JOptionPane.showMessageDialog(reviewDialog, "Rating must be between 1 and 5.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(reviewDialog,
                        "Invalid rating. Please enter a number between 1 and 5.");
            }
        });

        JPanel reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(reviewsPanel);
        scrollPane.setPreferredSize(new Dimension(280, 200));

        reviewDialog.add(submitReviewPanel, BorderLayout.NORTH);
        reviewDialog.add(submitReviewButton, BorderLayout.CENTER);
        reviewDialog.add(scrollPane, BorderLayout.SOUTH);

        refreshReviewsPanel(product, reviewDialog);
        reviewDialog.setVisible(true);
    }

    // EFFECTS: Refreshes the reviews panel with existing reviews
    private void refreshReviewsPanel(Product product, JDialog reviewDialog) {
        JPanel reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
        for (Review review : product.getReviews()) {
            JLabel reviewLabel = new JLabel("Rating: " + review.getRating() + ", Comment: " + review.getComment());
            reviewsPanel.add(reviewLabel);
        }
        JScrollPane scrollPane = (JScrollPane) reviewDialog.getContentPane().getComponent(2);
        scrollPane.setViewportView(reviewsPanel);
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    // EFFECTS: Filters products by category
    private void filterProductsByCategory(String category) {
        productPanel.removeAll();
        for (Product product : marketplace.getAllProducts()) {
            if (category.equals("All") || product.getCategory().equalsIgnoreCase(category)) {
                productPanel.add(new JLabel(product.toString()));
            }
        }
        productPanel.revalidate();
        productPanel.repaint();
    }

    // EFFECTS: Displays a splash screen with the image on startup
    private void showSplashScreen() {
        JLabel splashLabel = new JLabel(new ImageIcon("StartScreen_Pic.jpeg"));
        JOptionPane.showMessageDialog(this, splashLabel, "Welcome to the Marketplace", JOptionPane.PLAIN_MESSAGE);
    }

    // Prints the event log
    private void printEventLog() {
        System.out.println("Event Log:");
        for (Event event : EventLog.getInstance()) {
            System.out.println(event);
        }
    }

    // Creates a help menu
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu helpMenu = new JMenu("Help");
        JMenuItem howToUseItem = new JMenuItem("How to Use");
        howToUseItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "1. Add Products: Click 'Add Product'.\n"
                        + "2. Remove Products: Click 'Remove Product'.\n"
                        + "3. Submit Reviews: Click 'Submit/View Review'.",
                "How to Use", JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(howToUseItem);
        menuBar.add(helpMenu);
        return menuBar;
    }

    // Main method to launch the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MarketplaceAppGUI gui = new MarketplaceAppGUI();
            gui.setVisible(true);
        });
    }

}
