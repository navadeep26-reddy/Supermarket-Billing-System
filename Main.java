import java.util.ArrayList;
import java.util.Scanner;

// Product class
class Product {
    int id;
    String name;
    double price;

    // Constructor
    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

// Main class
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Product list
        ArrayList<Product> products = new ArrayList<>();

        // Default products
        products.add(new Product(1, "Rice", 50));
        products.add(new Product(2, "Milk", 30));
        products.add(new Product(3, "Bread", 25));
        products.add(new Product(4, "Eggs", 6));
        products.add(new Product(5, "Oil", 120));

        double total = 0;

        System.out.println("===== SUPERMARKET BILLING SYSTEM =====");

        while (true) {

            // Menu
            System.out.println("\n1. Add Custom Product");
            System.out.println("2. Buy Product");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            // Input validation
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();

            // Add custom product
            if (choice == 1) {
                System.out.print("Enter Product ID: ");
                int newId = sc.nextInt();

                // Check duplicate ID
                boolean exists = false;
                for (Product p : products) {
                    if (p.id == newId) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println("Product ID already exists!");
                    continue;
                }

                System.out.print("Enter Product Name: ");
                sc.nextLine(); // clear buffer
                String newName = sc.nextLine();

                System.out.print("Enter Product Price: ");
                double newPrice = sc.nextDouble();

                products.add(new Product(newId, newName, newPrice));

                System.out.println("Custom product added successfully!");
            }

            // Buy product
            else if (choice == 2) {

                System.out.println("\nAvailable Products:");
                for (Product p : products) {
                    System.out.println(p.id + ". " + p.name + " - Rs." + p.price);
                }

                System.out.print("\nEnter Product ID (0 to go back): ");
                int id = sc.nextInt();

                if (id == 0) continue;

                // Find product
                Product selectedProduct = null;
                for (Product p : products) {
                    if (p.id == id) {
                        selectedProduct = p;
                        break;
                    }
                }

                if (selectedProduct == null) {
                    System.out.println("Invalid Product ID!");
                    continue;
                }

                System.out.print("Enter Quantity: ");
                int quantity = sc.nextInt();

                if (quantity <= 0) {
                    System.out.println("Quantity must be greater than 0.");
                    continue;
                }

                double cost = selectedProduct.price * quantity;
                total += cost;

                System.out.println(selectedProduct.name + " added. Cost = Rs." + cost);
            }

            // Exit
            else if (choice == 0) {
                break;
            }

            else {
                System.out.println("Invalid choice!");
            }
        }

        // Bill Summary
        System.out.println("\n========== BILL ==========");
        System.out.printf("Total Amount = Rs. %.2f\n", total);

        double gst = total * 0.05;
        double finalAmount = total + gst;

        System.out.printf("GST (5%%) = Rs. %.2f\n", gst);
        System.out.printf("Final Amount = Rs. %.2f\n", finalAmount);

        System.out.println("==========================");
        System.out.println("Thank You! Visit Again.");

        sc.close();
    }
}