package Product_and_Employee;

public class Product {
    private String name;
    private double price;
    private Integer code;
    private int stock;

    public Product(String name, double price, Integer code, int stock){
        setName(name);
        setPrice(price);
        setCode(code);
        setStock(stock);
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if(stock < 0) throw new IllegalArgumentException("There is no more of this product in the inventory");
        this.stock = stock;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        if( code < 0) throw new IllegalArgumentException("The code can't be less than 0");
        this.code = code;

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price <= 0) throw new IllegalArgumentException("The price can't be less than 0");
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("Must write the name of the product");

        this.name = name.trim().substring(0, 1).toUpperCase() + name.trim().substring(1).toLowerCase();
    }

    @Override
    public String toString(){
        return " | " + getName() + "|" +
                " | " + getPrice() + " | " +
                " | " + getCode() + " | " +
                " | " + getStock() + " | ";
    }
}
