import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner sc = new Scanner(System.in);
    static EmployeeManager employeeManager = new EmployeeManager();
    static Inventory inventory = new Inventory();
    static Employee employee;

    public static void main(String[] args) {
        inventory.readProductJson();
        employeeManager.readEmployeeJson();
        try {
            if (!employeeManager.thereAreEmployee()) {
                System.out.print(employeeManager.thereAreEmployee());
                employee = newEmployee();
                employeeManager.addEmployee(employee);
            }
        }catch (IllegalArgumentException e) {
            System.out.print("Error: " + e.getMessage());
        }


        while (true) {
            try {
                System.out.println("\n------- Menu -------");
                System.out.println("1. Ingresar: ");
                System.out.println("2. Salir");
                System.out.print("Elije una opcion: ");
                int option = sc.nextInt();
                sc.nextLine();

                switch (option){
                    case 1:
                        System.out.print("Ingresa tu ID: ");
                        int idEmployee = sc.nextInt();
                        employee = employeeManager.searchEmployee(idEmployee);

                        System.out.println("Hola " + employee.getName() + ", bienvenido");
                        if(employee.getStatus().equalsIgnoreCase("Manager")) menuManager();
                        else if (employee.getStatus().equalsIgnoreCase("Seller")) menuSeller();

                        return;
                    case 2: return;
                    default:
                        System.out.print("Elije una de las opciones");
                        break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + e.getLocalizedMessage());
            } catch (InputMismatchException e){
                System.out.println("Please enter a valid number");
                sc.nextLine();
                continue;
            }
        }
    }


    static void menuManager()throws IllegalArgumentException, InputMismatchException{
        boolean finalMenu = true;
        do {
            System.out.println("\n=-=-=-=-= Manager =-=-=-=-=");
            System.out.println("1. Add new employee");
            System.out.println("2. Add new product");
            System.out.println("3. Remove employee");
            System.out.println("4. Remove product");
            System.out.println("5. Sell product");
            System.out.println("6. Show product");
            System.out.println("7. Go back");
            System.out.print("Choos your option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    employeeManager.addEmployee(newEmployee());
                    break;

                case 2:
                    inventory.addProduct(newProduct());
                    break;

                case 3:
                    System.out.print("ID of the employee to be removed: ");
                    Integer id = sc.nextInt();
                    sc.nextLine();
                    employeeManager.removeEmployee(id);
                    break;

                case 4:
                    System.out.print("Writes Name or Code product: ");
                    String productToBeRemove = sc.nextLine();

                    if (productToBeRemove.matches("\\d+")) {
                        int codeProduct = Integer.parseInt(productToBeRemove);
                        inventory.removeProduct(codeProduct);
                        System.out.print("El Producto a sido eliminado por su codigo");
                    } else {
                        inventory.removeProduct(productToBeRemove);
                        System.out.print("El Producto a sido eliminado por su nombre");
                    }
                    break;

                case 5:
                    System.out.print("Code product: ");
                    int codeProduct = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Number of products sold: ");
                    int soldProduct = sc.nextInt();
                    sc.nextLine();

                    inventory.sellProduct(codeProduct, soldProduct);
                    break;

                case 6:
                    System.out.println("Which product do you want to see?");
                    System.out.print("Write code: ");
                    int search = sc.nextInt();
                    sc.nextLine();
                    Product show  = inventory.searchProduct(search);
                    System.out.println(show);
                    break;

                case 7:
                    System.out.print("Regresando.....");
                    finalMenu = false;
                    break;

                default:
                    System.out.print("Choos correct option");
                    break;

            }
        } while (finalMenu);
    }

    static void menuSeller() throws IllegalArgumentException, InputMismatchException{
        while (true){
            System.out.println("===== Seller =====");
            System.out.println("1. Sell product");
            System.out.println("2. Search product");
            System.out.println("3 Stock product");
            System.out.println("4. Go back");
            System.out.print("Choos one option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 1:
                    System.out.print("Code product: ");
                    int codeProduct = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Number of products sold: ");
                    int soldProduct = sc.nextInt();
                    sc.nextLine();

                    inventory.sellProduct(codeProduct, soldProduct);
                    break;
                case 2:
                    System.out.println("Which product do you want to see?");
                    System.out.print("Write code: ");
                    int search = sc.nextInt();
                    sc.nextLine();
                    Product show  = inventory.searchProduct(search);
                    System.out.println(show);
                    break;
                case 3:
                    System.out.print("Write code: ");
                    int searchStock = sc.nextInt();
                    sc.nextLine();
                    Product showStock  = inventory.searchProduct(searchStock);
                    System.out.println(showStock.getStock());
                    break;
                case 4:
                    System.out.println("Returning.....");
                    return;
                default:
                    System.out.println("Choos correct option");
                    break;

            }
        }
    }

    private static Employee newEmployee() throws IllegalArgumentException {

        System.out.println("----- New Employee -----");
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Status(Manager or Seller): ");
        String status = sc.nextLine();

        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        return new Employee(name, email, status, age, id);
    }

    private static Product newProduct() throws IllegalArgumentException {

        System.out.println("----- New Product -----");
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Price: ");
        double price = sc.nextDouble();

        System.out.print("Code: ");
        int code = sc.nextInt();

        System.out.print("Stock: ");
        int stock = sc.nextInt();
        sc.nextLine();


        return new Product(name, price, code, stock);
    }

}
