package westminsterShoppingManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static westminsterShoppingManager.Main.loadedList;

class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    void testMainFlow() throws IOException {

        String simulatedUserInput = "1\nSomeProductName\nSomeProductDescription\nSomeCategory\n20.5\n10\n4\nN\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream);

        Main.main(new String[]{});

        assertTrue(outContent.toString().contains("System terminated"));

        System.setIn(originalIn);
    }

    @Test
    void testDeleteProductFromFile() {
        // Add necessary setup for loadedList (Product list) before testing
        String simulatedUserInput = "SomeProductID\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream);

        // Execute the deleteProductFromFile method
        Main.deleteProductFromFile();

        // Verify the product is removed from loadedList
        boolean found = false;
        for (Product product : loadedList) {
            if (product.getProductId().equals("SomeProductID")) {
                found = true;
                break;
            }
        }
        assertFalse(found, "Product should be removed");

        // Reset System.in to the original InputStream
        System.setIn(originalIn);
    }


    @Test
    void testInvalidInput() throws IOException {
        // Simulate user input for testing
        String simulatedUserInput = "InvalidChoice\n7\nY\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream);

        // Execute the main method
        Main.main(new String[]{});
        assertTrue(outContent.toString().contains("System terminated"));

        System.setIn(originalIn);
    }

    @Test
    void testSaveProductsToFile() throws IOException {

        Main.manager.saveProductsToFile();

    }


    @Test
    void testInvalidProductID() {
        // Simulate user input for testing
        String simulatedUserInput = "InvalidProductID\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream);
        Main.deleteProductFromFile();
        assertTrue(outContent.toString().contains("invalid input"));
        System.setIn(originalIn);
    }

    @Test
    void testInvalidFileRead() {
        // Simulate user input for testing
        String simulatedUserInput = "SomeProductID\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream);
        loadedList = null;
        Main.deleteProductFromFile();
        assertTrue(outContent.toString().contains("invalid input"));
        System.setIn(originalIn);
        loadedList = Main.manager.loadProductsFromFile("productsFile.txt");
    }

}
