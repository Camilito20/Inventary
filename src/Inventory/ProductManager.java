import java.util.ArrayList;

public class Inventory {
    final ArrayList<Product> products = new ArrayList<>();

    public Inventory(){}

    public void addProduct(Product p){
        if(p == null) throw new IllegalArgumentException("Product cannot be null");
        for(Product prod : products){
            if(p.getCode() == prod.getCode()) throw new IllegalArgumentException("The code existing in another product");
        }

        products.add(p);
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
        System.out.print("Now there are " + product.getStock() + " products in stock");
    }
}
