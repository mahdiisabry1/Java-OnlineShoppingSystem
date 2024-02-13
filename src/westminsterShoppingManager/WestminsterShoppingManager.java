package westminsterShoppingManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {

    // To store the list of clothing and Electronics both (Everything)
    private List<Product> productList;
    // To Store the list of Electronic products
    private List<Electronics> electronicList;
    // To Store the list of clothing products
    private List<Clothing> clothingList;
    // Initiating only 50 products can exist
    private static final int MaxProducts = 50;
    // Getting the file paths
    File file = new File("productsFile.txt");
    File fileE = new File("electronicList.txt.txt");
    File fileC = new File("Clothing.txt.txt");
    // Creating an instance to save the file
    ObjectOutputStream oos = null;
    //	List<Product> loadedList = loadProductsFromFile("productsFile.txt");

    public WestminsterShoppingManager() {
        this.setProductList(new ArrayList<>());
        this.setElectronicList(new ArrayList<>());
        this.setClothingList(new ArrayList<>());
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Clothing> getClothingList() {
        return clothingList;
    }

    public void setClothingList(List<Clothing> clothingList) {
        this.clothingList = clothingList;
    }

    public List<Electronics> getElectronicList() {
        return electronicList;
    }

    public void setElectronicList(List<Electronics> electronicList) {
        this.electronicList = electronicList;
    }

    @SuppressWarnings("unchecked")
    public List<Product> loadProductsFromFile(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            productList = (List<Product>) inputStream.readObject();
            // System.out.println("Products loaded from file: " + filename);
        } catch (FileNotFoundException e) {
            // System.out.println("File not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            // System.out.println("Error loading products: " + e.getMessage());
        }
        return productList;
    }

    @SuppressWarnings("unchecked")
    public List<Electronics> loadElectronicsFromFile(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            electronicList = (List<Electronics>) inputStream.readObject();
            // System.out.println("Products loaded from file: " + filename);
        } catch (FileNotFoundException e) {
            // System.out.println("File not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            // System.out.println("Error loading products: " + e.getMessage());
        }
        return electronicList;
    }

    @SuppressWarnings("unchecked")
    public List<Clothing> loadClothingFromFile(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            clothingList = (List<Clothing>) inputStream.readObject();
            // System.out.println("Products loaded from file: " + filename);
        } catch (FileNotFoundException e) {
            // System.out.println("File not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            // System.out.println("Error loading products: " + e.getMessage());
        }
        return clothingList;
    }

    public void displayMenu() {
        System.out.println();
        System.out.println();
        System.out.println("     |----Main Menu----|");
        System.out.println();
        System.out.println("1. Enter 1 to Add an Item \n\n" + "2. Enter 2 to Remove an Item \n\n"
                + "3. Enter 3 to Print the list of products \n\n"
                + "4. Enter 4 to Save the changes you made to a file \n\n"
                + "5. Enter 5 to Delete the product from the file history \n\n"
                + "6. Enter 6 to view the Shopping Cart\n\n" + "7. Enter 7 to exit \n");
    }

    Scanner in = new Scanner(System.in);

    public void addProductsToSystem() {
        // TODO Auto-generated method stub
        // Implementing logic to add products (electronics or clothing)

        int option;
        System.out.println();
        System.out.println("The Maximum Products can be added is only 50\n");
        if (productList.size() <= MaxProducts) {
            int availableSpace = MaxProducts - productList.size();
            System.out.println(availableSpace + " Slots available");
            System.out.println();
            try {
                String electronicId;
                System.out.print("Enter 1 to add Electronic\nEnter 2 to add a clothing Product : ");
                option = in.nextInt();
                if (option == 1) {
                    System.out.println();
                    String electronicid;
                    do {
                        System.out.println();
                        System.out.print("Enter ProductId : ");
                        electronicId = in.next();

                        // Validate electronicId
                        if (isValidProductId(electronicId)) {
                            // Your code to proceed with the valid electronicId
                            electronicid = electronicId.toUpperCase();
                            // Continue with the rest of your code...
                            break; // Exit the loop since a valid electronicId is provided
                        } else {
                            System.out.println("Invalid ProductId. Please make sure it starts with a letter and has at least 4 characters.");
                        }
                    } while (true);
                    System.out.print("Enter Product Name : ");
                    String electronicName = in.next();
                    System.out.print("Enter no of items available : ");
                    int noOfItem = in.nextInt();
                    System.out.print("Enter Price : ");
                    double price = in.nextDouble();
                    System.out.print("Enter brand name : ");
                    String brand = in.next();
                    System.out.print("Enter Warrenty period : ");
                    int warrenty = in.nextInt();

                    Electronics electro = new Electronics(electronicid, electronicName, noOfItem, price, brand,
                            warrenty);
                    electronicList.add(electro);
                    productList.add(electro);
                    System.out.println(electronicid + " successfully added");

                    oos = new ObjectOutputStream(new FileOutputStream(fileE));
                    oos.writeObject(electronicList);
                    oos.close();
                    System.out.println("Saved changes to the file ");

                } else if (option == 2) {
                    System.out.println();
                    System.out.print("Enter ProductId : ");
                    String clothingId = in.next();
                    String clothingid = clothingId.toUpperCase();
                    System.out.print("Enter Product Name : ");
                    String clothingName = in.next();
                    System.out.print("Enter no of items available : ");
                    int noOfItem = in.nextInt();
                    System.out.print("Enter Price : ");
                    double price = in.nextDouble();
                    System.out.print("Enter size (S - Small, M - Medium, L - Large) : ");
                    String size = in.next();
                    System.out.print("Enter color  : ");
                    String color = in.next();

                    Clothing clothing = new Clothing(clothingid, clothingName, noOfItem, price, size, color);
                    clothingList.add(clothing);
                    productList.add(clothing);

                    System.out.println(clothingid + " successfully added");

                    oos = new ObjectOutputStream(new FileOutputStream(fileC));
                    oos.writeObject(clothingList);
                    oos.close();
                    System.out.println("Saved changes to the file ");

                } else {
                    System.out.println("Invalid input");
                    addProductsToSystem();
                }

            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        } else {
            System.out.println("Max Products reached");
        }

        System.out.print("\nDo you want to keep on adding\nIf yes type 'Y'\nEnter any key for main manu: \n");
        String cont = in.next();
        String upperCont = cont.toUpperCase();
        if (upperCont.equals("Y")) {
            addProductsToSystem();
        } else {
            System.out.println("\nRedirected to main manu\n");
        }

    }

    private static boolean isValidProductId(String electronicId) {
        // Check if the id starts with a letter and has at least 4 characters
        return electronicId.matches("[a-zA-Z].{3,}");
    }

    public void deleteProductFromCurrentSystem() {
        // TODO Auto-generated method stub
        System.out.println("Go to option 5 If you have entered the product before\n");
        System.out.println("Enter Product ID : ");
        String productID = in.next();
        String productid = productID.toUpperCase();
        for (Product product : productList) {
            if (product.getProductId().equals(productid)) {
                productList.remove(product);
                System.out.println("Product " + productid + " removed ");
                break;
            } else {
                System.out.println("Product doesnt exist\nTry Opton 5 to delete");
                break;
            }
        }

    }

    public void printListOfProducts() {
        // TODO Auto-generated method stub

        productList.sort(Comparator.comparing(Product::getProductId));

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("%15s %20s %20s %10s %15s", "Product Id", "Prodoct Name", "No of Available", "Price",
                "category");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Product product : productList) {
            if (product instanceof Electronics) {
                System.out.format("%15s %20s %12s %18s %15s", product.getProductId(), product.getProductName(),
                        product.getNoOfAvailableItems(), product.getPrice(), "Electronics");
                System.out.println();
            } else if (product instanceof Clothing) {
                System.out.format("%15s %20s %12s %18s %15s", product.getProductId(), product.getProductName(),
                        product.getNoOfAvailableItems(), product.getPrice(), "clothing");
                System.out.println();
            }

        }

        System.out.println("-----------------------------------------------------------------------------------------");

    }

    public void saveProductsToFile() throws IOException {
        // TODO Auto-generated method
        oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(productList);
        oos.close();
        System.out.println("Saved changes to the file ");

    }
}