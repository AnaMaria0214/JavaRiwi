import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
    private ArrayList<Product> productList;

    public Inventory(){
        this.productList = new ArrayList<>();
    }

    //methods
    // add product
    public void addProduct (Scanner objScan){
        System.out.println("Enter the id product to add");
        int id = objScan.nextInt();
        System.out.println("Enter the name product to add");
        String name = objScan.next();
        System.out.println("Enter the price product to add");
        double price = objScan.nextDouble();

        this.productList.add(new Product(id,name,price));
    }

    // delete product
    public boolean deleteProduct (int id){
        return productList.removeIf(Product -> Product.getId() == id);
    }

    //list products
    public void ProductList(){
        for (Product product: this.productList){
            System.out.println("ID: "+product.getId()+"Name: "+product.getName()+"Price: "+product.getPrice());
        }
    }

    //Search for name
    public Product searchForName(String searchName){
        for (Product objProduct: this.productList){
            if (objProduct.getName().equalsIgnoreCase(searchName)){
                return objProduct;
            }
        }
        return null;
    }

}
