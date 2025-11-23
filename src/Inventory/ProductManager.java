package Inventory;

import Product_and_Employee.Product;
import org.json.*;

import java.io.*;
import java.util.ArrayList;

public class ProductManager {
    final ArrayList<Product> products = new ArrayList<>();

    public ProductManager(){}

    //Agrega productos al Array
    public void addProduct(Product p){
        if(p == null) throw new IllegalArgumentException("Product_and_Employee.Product cannot be null");
        for(Product prod : products){
            if(p.getCode() == prod.getCode()) throw new IllegalArgumentException("The code existing in another product");
        }

        products.add(p);
        addProductJson(p);
    }

    public ArrayList<Product> allProduct(){
        return this.products;
    }
    //Remueven productos del array
    public void removeProduct(String nameProductToBeRemove){
        if(nameProductToBeRemove == null || nameProductToBeRemove.isEmpty()) throw new IllegalArgumentException("Must write the name of the product");

        nameProductToBeRemove = nameProductToBeRemove.trim();
        boolean search = false;
        Product productToBeRemove = null;

        for(Product p: products){
            if(p.getName().equalsIgnoreCase(nameProductToBeRemove)){
                productToBeRemove = p;
                System.out.println("The product were remove");
                search = true;
                break;
            }
        }

        if (!search) throw new IllegalArgumentException("The product wasn't find");
        products.remove(productToBeRemove);
        loadProductJson();

    }
    public void removeProduct(Integer code){

        if (code == null) throw new IllegalArgumentException("Must write the code of the product");

        String stringCode = String.valueOf(code);

       // if(stringCode.length() != 6) throw new IllegalArgumentException("The code product must have 6 digits");

        boolean search = false;
        Product productToBeRemove = null;

        for(Product p : products){
            if(p.getCode() == code){
                productToBeRemove = p;
                System.out.println("The product were remove");
                search = true;
                break;
            }
        }
        if (!search) throw new IllegalArgumentException("The product wasn't find");
        products.remove(productToBeRemove);
        loadProductJson();
    }

    //Buscan Productos del array
    public Product searchProduct(Integer code){
        if(code == null) throw new IllegalArgumentException("Must write the code of the product");

        for(Product p : products){
            if(p.getCode() == code) {
                return p;
            }
        }

        throw new IllegalArgumentException("Product_and_Employee.Product wasn't find");

    }
    public Product searchProduct(String name){
        if(name == null) throw new IllegalArgumentException("Must write the name of the product");

        for(Product p : products){
            if(p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Product_and_Employee.Product wasn't find");
    }

    //Bajan el stock de algun producto del Array cuando se vende
    public void sellProduct(int code, int numPoductSold){
        Product product = searchProduct(code);

        if (numPoductSold > product.getStock()) throw new IllegalArgumentException("It only has " +  product.getStock() + " " + product.getName() + " in stock");

        product.setStock(product.getStock() - numPoductSold);
        System.out.print("Now there are " + product.getStock() + " products in stock");
        loadProductJson();
    }

    //----------- Parte JSON -----------
    //Recarga el JSON
    public void reloadProduct(){
        reloadProductJson();
    }
    /*
    Aqui comienza implementacion de los productos en JOSN
     */
    private void addProductJson(Product product) {
        try{
            File file = new File("Products.json");

            JSONArray jsonArray = new JSONArray();

            if(file.exists()){
                try (FileReader reader = new FileReader(file)) {
                    JSONTokener tokener = new JSONTokener(reader);
                    jsonArray = new JSONArray(tokener);
                }
            }

            JSONObject p = new JSONObject();
            p.put("Name", product.getName());
            p.put("Price", product.getPrice());
            p.put("Code", product.getCode());
            p.put("Stock", product.getPrice());

            jsonArray.put(p);

            try (FileWriter writer = new FileWriter(file)){
                writer.write(jsonArray.toString(4));

            }
        }catch (IOException e){
            System.out.println("Error: "  + e.getMessage());
        }
    }

    private void loadProductJson(){
        try {
            File file = new File("Products.json");

            JSONArray jsonArray = new JSONArray();
            for(Product p : products){
                JSONObject object = new JSONObject();
                object.put("Name", p.getName());
                object.put("Price", p.getPrice());
                object.put("Code", p.getCode());
                object.put("Stock", p.getPrice());

                jsonArray.put(object);
            }

            try (FileWriter writer = new FileWriter(file)){
                writer.write(jsonArray.toString(4));
            }
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void reloadProductJson() {
        products.clear();

        File file = new File("Products.json");

        if (!file.exists()) return;

        try (FileReader reader = new FileReader(file)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray jsonArray = new JSONArray(tokener);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                String name = object.getString("Name");
                double price = object.getDouble("Price");
                int code = object.getInt("Code");
                int stock = object.getInt("Stock");

                Product p = new Product(name, price, code, stock);
                products.add(p);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
