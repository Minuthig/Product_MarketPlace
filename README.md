# Product Marketplace Platform

## Project Proposal

The application I propose to develop is a marketplace platform that bridges producers and customers, allowing producers to list the items they are selling while enabling customers to rate and review these products based on set criteria. 

Producers will be able to create detailed product listings, including *descriptions, prices, and categories,* while customers can *browse the available items, provide feedback, and rate them based on factors like quality, value for money, and delivery speed.* The application will feature a filtering system, allowing users to sort and search products by price, rating, and category, making it easier to find the best-rated or most affordable products.

This application is designed for two key user groups: 
- **producers** looking for a simple way to list and promote their products, 
- and **customers** who want a reliable platform where they can make informed decisions based on real user reviews. 

*Whether it's small businesses looking to reach a wider audience or consumers wanting to compare products before purchasing, the platform will cater to a broad audience. It’s a tool that not only facilitates commerce but also builds trust by offering transparent feedback through a detailed rating system.*

This project is of particular interest to me because it combines elements of e-commerce, user engagement, and data management, offering a unique challenge in balancing the needs of both producers and customers. I am excited to design an application that goes beyond a simple product listing by incorporating dynamic features like customer ratings and reviews. This will require me to implement non-trivial logic, such as calculating average ratings, managing review data, and filtering products based on various criteria, making the project both technically interesting and creatively fulfilling.

#### Class X: Product
Represents an item being sold. Each product could have attributes like:
     - name
     - description
     - price
     - category
     - producer information

#### Class Y: Product Marketplace
- Represents the collection of products that are listed by different producers.
   - Could manage actions like:
     - Adding a new product.
     - Removing a product.
     - Searching for products by name, category, or producer.
     - Sorting products based on ratings or price.


## User Stories
There are two types of users for this platform.
-	Customers
-	Producers

In a *customer's* point of view;
- I want to be able to view a list of all products available in the marketplace, including their name, price, and category so I can browse for items to purchase.
- I want to be able to submit a review for a product and specify a rating and a comment, so that others can see my feedback and make informed decisions.

- I want to be able to save the entire state of the marketplace, including all products and reviews, to a file, so I can resume from where I left off later.
- As a user, when I start the application, I want to be given the option to load the saved state of the marketplace from a file, so I can pick up exactly where I left off previously.

In a *producer's* point of view;
- I want to be able to add a product to the marketplace and specify its name, description, price, and category so that customers can view it.
- I want to be able to remove a product from the marketplace so that it is no longer visible to customers.

- I want to be able to save my product listings, including all details like name, price, description, and reviews, to a file, so I can ensure my items are stored and can be retrieved later if I exit the marketplace.
- As a producer, I want the application to periodically autosave any changes to my product listings and reviews, so that even if the program crashes or I forget to save manually, my work is protected.

#### Instructions for End User
- You can add a product to the marketplace by clicking on the "Add Product" button located at the top of the screen.
- You can remove a product from the marketplace by clicking on the "Remove Product" button located at the top of the screen.
- You can locate my visual component when loading the marketplace.
- You can save the state of my application by clicking on the "Save Marketplace" button located at the top of the screen.
- You can reload the state of my application by clicking on the "Load Marketplace" button llocated at the top of the screen.