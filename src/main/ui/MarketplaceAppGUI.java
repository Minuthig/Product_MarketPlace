package ui;

import model.Marketplace;
import model.Product;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MarketplaceAppGUI extends JFrame {
    private static final String JSON_STORE = "./data/marketplace.json";
    private Marketplace marketplace;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JPanel productPanel;

    // Constructor for the GUI application
    public MarketplaceAppGUI() {
        super("Marketplace Application");
        marketplace = new Marketplace();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Add components to the GUI
        add(createControlPanel(), BorderLayout.NORTH);
        add(createProductPanel(), BorderLayout.CENTER);

        // Show splash screen on start
        showSplashScreen();
    }

    // Creates a control panel with buttons for actions
    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Add Product");
        JButton loadButton = new JButton("Load Marketplace");
        JButton saveButton = new JButton("Save Marketplace");

        controlPanel.add(addButton);
        controlPanel.add(loadButton);
        controlPanel.add(saveButton);

        // Add action listeners
        addButton.addActionListener(e -> addProduct());
        loadButton.addActionListener(e -> loadMarketplace());
        saveButton.addActionListener(e -> saveMarketplace());

        return controlPanel;
    }


    // Creates the product display panel
    private JScrollPane createProductPanel() {
         productPanel = new JPanel();
         productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
         JScrollPane scrollPane = new JScrollPane(productPanel);
         scrollPane.setPreferredSize(new Dimension(780, 400));
         return scrollPane;
    }

    // Displays all products in the marketplace
    private void refreshProductPanel() {
        productPanel.removeAll();
        for (Product product : marketplace.getAllProducts()) {
            JLabel productLabel = new JLabel(product.toString());
            productPanel.add(productLabel);
        }
        productPanel.revalidate();
        productPanel.repaint();
    }

    // Opens a dialog to add a product
    private void addProduct() {
        JTextField nameField = new JTextField(10);
        JTextField descriptionField = new JTextField(10);
        JTextField priceField = new JTextField(10);
        JTextField categoryField = new JTextField(10);
        JTextField producerField = new JTextField(10);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Producer:"));
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

    // Loads the marketplace from a file
    private void loadMarketplace() {
        try {
            marketplace = jsonReader.read();
            refreshProductPanel();
            JOptionPane.showMessageDialog(this, "Marketplace loaded successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to load marketplace.");
        }
    }

    // Saves the marketplace to a file
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

    // Displays a splash screen on startup
    private void showSplashScreen() {
        JLabel splashLabel = new JLabel(new ImageIcon("./data/splash.jpg")); // Adjust the image path
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
