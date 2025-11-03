import java.util.ArrayList;
import org.json.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;;

public class Inventory {
    final ArrayList<Product> products = new ArrayList<>();

    public Inventory(){}

    public void addProduct(Product p){
        if(p == null) throw new IllegalArgumentException("Product cannot be null");
        for(Product prod : products){
            if(p.getCode() == prod.getCode()) throw new IllegalArgumentException("The code existing in another product");
        }

        products.add(p);
        addProductJson(p);
    }

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
        else if(productToBeRemove != null) products.remove(productToBeRemove);
        updateProductJson();

    }
    public void removeProduct(Integer code){

        if (code == null) throw new IllegalArgumentException("Must write the code of the product");

        String stringCode = String.valueOf(code);

        if(stringCode.length() != 6) throw new IllegalArgumentException("The code product must have 6 digits");

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
        else if(productToBeRemove != null) products.remove(productToBeRemove);
        updateProductJson();
    }

    public Product searchProduct(Integer code){
        if(code == null) throw new IllegalArgumentException("Must write the code of the product");

        for(Product p : products){
            if(p.getCode() == code) {
                return p;
            }
        }

        throw new IllegalArgumentException("Product wasn't find");

    }
    public Product searchProduct(String name){
        if(name == null) throw new IllegalArgumentException("Must write the name of the product");

        for(Product p : products){
            if(p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Product wasn't find");
    }

    public void sellProduct(int code, int numPoductSold){
        Product product = searchProduct(code);

        if (numPoductSold > product.getStock()) throw new IllegalArgumentException("It only has " +  product.getStock() + " " + product.getName() + " in stock");

        product.setStock(product.getStock() - numPoductSold);
        updateProductJson();
        System.out.print("Now there are " + product.getStock() + " products in stock");
    }

    //Este metodo es para agregar un nuevo objeto tipo producto al JSON
    private void addProductJson(Product p){
        try {
            File file = new File("Products.json");

            JSONArray productsJSON = new JSONArray();

            if(file.exists()){
                try(FileReader reader = new FileReader(file)) {
                    JSONTokener tokener = new JSONTokener(reader);
                    productsJSON = new JSONArray(tokener);
                    }
            }

            JSONObject product = new JSONObject();
            product.put("Name", p.getName());
            product.put("Price", p.getPrice());
            product.put("Code", p.getCode());
            product.put("Stock", p.getStock());

            productsJSON.put(product);

            try (FileWriter writer = new FileWriter(file)){
                writer.write(productsJSON.toString(4));
            }

        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateProductJson(){
        try {
            File file = new File("Products.json");

            if (!file.exists()) return; // Evita error si el archivo no existe

            JSONArray productsJSON = new JSONArray();

            for(Product p : products) {
                JSONObject product = new JSONObject();
                product.put("Name", p.getName());
                product.put("Price", p.getPrice());
                product.put("Code", p.getCode());
                product.put("Stock", p.getStock());

                productsJSON.put(product);
            }

            try (FileWriter writer = new FileWriter(file)){
                writer.write(productsJSON.toString(4));
            }

        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Este metodo es para leer el archivo JSON apenas se inicie el programa y cargarlos en el Array
    public void readProductJson() {
        products.clear();
        File file = new File("Products.json");

        if (!file.exists()) return; // Evita error si el archivo no existe

        try (FileReader reader = new FileReader(file)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray productsJSON = new JSONArray(tokener);

            for (int i = 0; i < productsJSON.length(); i++) {
                JSONObject object = productsJSON.getJSONObject(i);

                String name = object.getString("Name");
                double price = object.getDouble("Price");
                int code = object.getInt("Code");
                int stock = object.getInt("Stock");

                Product product = new Product(name, price, code, stock);
                products.add(product);
            }
        } catch (IOException e) {
            System.out.println("Error reading products: " + e.getMessage());
        }
    }
}


