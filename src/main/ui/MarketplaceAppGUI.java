package ui;

import model.Marketplace;
import model.Product;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MarketplaceAppGUI extends JFrame {
    private static final String JSON_STORE = "./data/marketplace.json";
    private Marketplace marketplace;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JPanel productPanel;

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

        showSplashScreen();
    }

    // EFFECTS: Creates a control panel with buttons for actions
    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Add Product");
        JButton removeButton = new JButton("Remove Product");
        JButton loadButton = new JButton("Load Marketplace");
        JButton saveButton = new JButton("Save Marketplace");
        // JButton reviewButton = new JButton("Submit Review");

        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        controlPanel.add(loadButton);
        controlPanel.add(saveButton);
        // controlPanel.add(reviewButton);

        addButton.addActionListener(e -> addProduct());
        removeButton.addActionListener(e -> removeProduct());
        loadButton.addActionListener(e -> loadMarketplace());
        saveButton.addActionListener(e -> saveMarketplace());
        // saveButton.addActionListener(e -> submitReview());

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

    // EFFECTS: Displays all the products in the marketplace
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
            refreshProductPanel();
            JOptionPane.showMessageDialog(this, "Product removed successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Product not found.");
        }
    }

    // EFFECTS: Loads the marketplace from a file
    private void loadMarketplace() {
        try {
            marketplace = jsonReader.read();
            refreshProductPanel();
            JOptionPane.showMessageDialog(this, "Marketplace loaded successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to load marketplace.");
        }
    }

    // EFFECTS: Saves the marketplace to a file
    private void saveMarketplace() {
        try {
            jsonWriter.open();
            jsonWriter.write(marketplace);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Marketplace saved successfully.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Failed to save marketplace.");
        }
    }

    // // EFFECTS: Method to submit a review for a product
    // @SuppressWarnings("methodlength")
    // private void submitReview() {
    //     String productName = JOptionPane.showInputDialog(this, "Enter the name of the product to review:");
    //     List<Product> productsFound = marketplace.searchByName(productName);
    //     if (!productsFound.isEmpty()) {
    //         Product product = productsFound.get(0);
    //         JTextField ratingField = new JTextField(10);
    //         JTextField commentField = new JTextField(10);
    //         JPanel inputPanel = new JPanel(new GridLayout(2, 2));
    //         inputPanel.add(new JLabel("Rating (1-5):"));
    //         inputPanel.add(ratingField);
    //         inputPanel.add(new JLabel("Comment:"));
    //         inputPanel.add(commentField);
    //         int result = JOptionPane.showConfirmDialog(this, inputPanel,
    //                 "Submit Review", JOptionPane.OK_CANCEL_OPTION);
    //         if (result == JOptionPane.OK_OPTION) {
    //             try {
    //                 int rating = Integer.parseInt(ratingField.getText());
    //                 String comment = commentField.getText();
    //                 if (rating >= 1 && rating <= 5) {
    //                     product.addReview(rating, comment);
    //                     refreshProductPanel();
    //                     JOptionPane.showMessageDialog(this, "Review submitted successfully!");
    //                 } else {
    //                     JOptionPane.showMessageDialog(this, "Rating must be between 1 and 5.");
    //                 }
    //             } catch (NumberFormatException e) {
    //                 JOptionPane.showMessageDialog(this, "Invalid rating. Please enter a number between 1 and 5.");
    //             }
    //         }
    //     } else {
    //         JOptionPane.showMessageDialog(this, "Product not found.");
    //     }
    // }

    // EFFECTS: Displays a splash screen with the image on startup
    private void showSplashScreen() {
        JLabel splashLabel = new JLabel(new ImageIcon("StartScreen_Pic.jpeg"));
        JOptionPane.showMessageDialog(this, splashLabel, "Welcome to the Marketplace", JOptionPane.PLAIN_MESSAGE);
    }

    // Main method to launch the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MarketplaceAppGUI gui = new MarketplaceAppGUI();
            gui.setVisible(true);
        });
    }

}
