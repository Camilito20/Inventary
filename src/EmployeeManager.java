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


}
