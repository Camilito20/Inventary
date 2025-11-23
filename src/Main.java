import Inventory.ProductManager;
import Product_and_Employee.Product;

import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        productManager.reloadProduct();
        Product removeBeProduct = new Product("Pan", 40.0, 1, 600);
        Product newProduct = new Product("Pan", 40.0, 2, 600);
        productManager.addProduct(removeBeProduct);

    }
}
