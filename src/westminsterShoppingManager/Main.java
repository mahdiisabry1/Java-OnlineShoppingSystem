package westminsterShoppingManager;

import westminsterShoppingCart.CartMain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings({"unused", "MismatchedQueryAndUpdateOfCollection"})
public class Main {
    static CartMain cart = new CartMain();
    static WestminsterShoppingManager manager = new WestminsterShoppingManager();
    static List<Product> loadedList = manager.loadProductsFromFile("productsFile.txt");
    static List<Electronics> loadedList2 = manager.loadElectronicsFromFile("electronicList.txt.txt");
    static List<Clothing> loadedList3 = manager.loadClothingFromFile("Clothing.txt.txt");

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        // Accessing WestminsterShoppingManager

        File file = new File("productsFile.txt");


        System.out.println(" --------------------------------------------------");
        System.out.println("|     Welcome to Westminster Shopping Manager      |");
        System.out.println(" --------------------------------------------------");

        Scanner enter = new Scanner(System.in);
        boolean go = true; // Initialize stop variable

        while (go) {

            // Reading the file to see saved products

            manager.displayMenu();
            System.out.print("Enter the number: ");
            String choice = enter.next();

            switch (choice) {
                case "1" -> manager.addProductsToSystem();
                case "2" -> manager.deleteProductFromCurrentSystem();
                case "3" -> manager.printListOfProducts();
                case "4" -> manager.saveProductsToFile();
                case "5" -> deleteProductFromFile();
                case "6" -> CartMain.main(args);
                case "7" -> {
                    System.out.println();
                    System.out.println("Save the changes you made to a file before proceeding to prevent complications");
                    System.out.println();
                    System.out.print("Do you want to terminate the system? ('Y' for yes, Any key for no) : ");
                    String term = enter.next();
                    String upper = term.toUpperCase();
                    if (upper.equals("Y")) {
                        System.out.println(
                                "---------------------------------------------------------------------------------------");
                        System.out.println(
                                "-----------------------------------System terminated-----------------------------------");
                        System.out.println(
                                "---------------------------------------------------------------------------------------");
                        go = false; // Exit the loop
                    } else if (upper.equals("N")) {
                    } else {
                        System.out.println();
                        System.out.println("continuing");
                    }
                }
                default -> System.out.println("Invalid input try again");
            }
        }
    }
    public static void deleteProductFromFile() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Product ID : ");
        String productID = in.next();
        String productid = productID.toUpperCase();
        try {
            // Read from the file
            BufferedReader reader = new BufferedReader(new FileReader("productsFile.txt"));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        }catch (Exception e) {
            System.out.println("invalid input");
        }
        boolean found = false;
        for (Product product : loadedList) {
            if (product.getProductId().equals(productid)) {
                loadedList.remove(product);
                System.out.println("Product Deleted");
                break;
            }else {
                // System.out.println("Product not found");
            }
        }
    }
}
