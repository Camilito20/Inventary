import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeManager {
    final ArrayList<Employee> employees = new ArrayList<>();

    public EmployeeManager(){}

    public void addEmployee(Employee e){
        if(e == null) throw new IllegalArgumentException("Employee data is missing");

        for(int i = 0; i < employees.size(); i++){
            if (employees.get(i).getId() == e.getId()) {
                throw new IllegalArgumentException("Another employee is already using this ID");
            }
            if (employees.get(i).getEmail().equalsIgnoreCase(e.getEmail())) {
                throw new IllegalArgumentException("Another employee is already using this email");
            }
        }

        employees.add(e);
        addEmployeeJson(e);
    }

    public void removeEmployee(Integer id) throws IndexOutOfBoundsException{
        if(id == null) throw new IllegalArgumentException("The employee haven't data");
        boolean search = false;

        int i = 0;
        for(Employee empl: employees){
            if(id == empl.getId()){
                search = true;
                System.out.println(empl);
                System.out.println("Removed");
                break;
            }
            i++;
        }

        if(!search) throw new IllegalArgumentException("The employee is not in the system");


        employees.remove(i);
    }

    public Employee searchEmployee(Integer idEmployee){
        if(idEmployee == null) throw new IllegalArgumentException("Must write id employee for search he");

        for(int i = 0; i < employees.size(); i++){
            if (employees.get(i).getId() == idEmployee){
                return employees.get(i);
            }
        }

        throw new IllegalArgumentException("The employee is not in the system");
    }

    public boolean thereAreEmployee(){
        //Si no hay nada en el array retorna verdadero y si es falso retorna false
        return employees.isEmpty() ? false : true;
    }

    private void addEmployeeJson(Employee emp){
        try {
            File file = new File("Employees.json");

            JSONArray productsJSON = new JSONArray();

            if(file.exists()){
                try(FileReader reader = new FileReader(file)) {
                    JSONTokener tokener = new JSONTokener(reader);
                    productsJSON = new JSONArray(tokener);
                }
            }

            JSONObject employee = new JSONObject();

            employee.put("Name", emp.getName());
            employee.put("Email", emp.getEmail());
            employee.put("Status", emp.getStatus());
            employee.put("Age", emp.getAge());
            employee.put("ID", emp.getId());

            productsJSON.put(employee);

            try (FileWriter writer = new FileWriter(file)){
                writer.write(productsJSON.toString(4));
            }

        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void readEmployeeJson() {
        File file = new File("Employees.json");

        if (!file.exists()) return; // Evita error si el archivo no existe

        try (FileReader reader = new FileReader(file)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray employeesJSON = new JSONArray(tokener);

            for (int i = 0; i < employeesJSON.length(); i++) {
                JSONObject object = employeesJSON.getJSONObject(i);

                String name = object.getString("Name");
                String email = object.getString("Email");
                String status = object.getString("Status");
                int age = object.getInt("Age");
                int id = object.getInt("ID");

                Employee employee = new Employee(name, email, status, age, id);
                employees.add(employee);
            }
        } catch (IOException e) {
            System.out.println("Error reading products: " + e.getMessage());
        }
    }


}
