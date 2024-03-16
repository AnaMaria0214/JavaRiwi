import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Instances
        Inventory objInventory = new Inventory();
        Scanner objScan = new Scanner(System.in);

        int option = 0;
        do {
            System.out.println("""
                    MENU
                    Select the option:
                    1. Search a product.
                    2. Add a new product.
                    3. Delete a product.
                    4. list a inventory.
                    5. Exit.
                    """);
            option = objScan.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter the name product");
                    String name = objScan.next();
                    Product product = objInventory.searchForName(name);
                    if (product == null) {
                        System.out.println("No product with this name was found");
                    } else {
                        System.out.println(product);
                    }
                    break;

                case 2:
                    objInventory.addProduct(objScan);
                    break;

                case 3:
                    objInventory.ProductList();

                    System.out.println("Enter which product ID do you want to delete ");
                    int id = objScan.nextInt();
                    if (objInventory.deleteProduct(id)) {
                        System.out.println("Product deleted");
                    } else {
                        System.out.println("ID not valid");
                    }
                    break;
                case 4:
                    objInventory.ProductList();
                    break;
            }

        } while (option != 5);

    }
}