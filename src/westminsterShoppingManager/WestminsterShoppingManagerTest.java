package westminsterShoppingManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterShoppingManagerTest {
    public void setInputForTesting(String... values) {
        String inputString = String.join(System.lineSeparator(), values);
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inputStream);
    }


    private WestminsterShoppingManager shoppingManager;
    private ByteArrayInputStream mockInput;

    @BeforeEach
    void setUp() {
        // Initialize necessary objects before each test
        shoppingManager = new WestminsterShoppingManager();
        mockInput = new ByteArrayInputStream("1\nABC123\nElectronicProduct\n10\n100.0\nBrand\n1\nY\n".getBytes());
        System.setIn(mockInput);
    }

    @AfterEach
    void tearDown() {
        // Clean up resources after each test
        System.setIn(System.in);
        File electronicFile = new File("electronic_products.txt");
        File clothingFile = new File("clothing_products.txt");
        electronicFile.delete();
        clothingFile.delete();
    }

    @Test
    void addProductsToSystem_ValidElectronicProduct_SuccessfullyAdded() {
        // Redirect standard input to mockInput
        System.setIn(mockInput);

        // Invoke the method
        shoppingManager.addProductsToSystem();

        // Check if the electronicList contains the added product
        List<Electronics> electronicList = shoppingManager.getElectronicList();
        assertEquals(1, electronicList.size());

        // Check if the productList contains the added product
        List<Product> productList = shoppingManager.getProductList();
        assertEquals(1, productList.size());

        // Add additional assertions as needed
    }

    @Test
    void addProductsToSystem_InvalidInput_Retry() {
        // Redirect standard input to mockInput
        System.setIn(new ByteArrayInputStream("invalid\n1\nABC123\nElectronicProduct\n10\n100.0\nBrand\n1\nY\n".getBytes()));

        // Invoke the method
        shoppingManager.addProductsToSystem();
    }

    @Test
    public void testDeleteProductFromCurrentSystem()  {

        // Create a mock for the ShoppingManager
        ShoppingManager managerMock = null;
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Set up the product list with a test product

        // Mock the in Scanner to provide input
        Scanner scannerMock = null;
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        when(scannerMock.next()).thenReturn("P001"); // simulate user entering product ID
//
//        // Set the mocked Scanner for the manager
//        managerMock.clone(scannerMock);
//
//        // Call the method to be tested
//        managerMock.deleteProductFromCurrentSystem();
//
//        // Verify that the product was removed
//        assertFalse(managerMock.addProductsToSystem().contains(testProduct));
//
//        // Verify that the Scanner was used
//        verify(scannerMock, times(1)).next();
    }

}