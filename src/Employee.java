public class Employee {
    private String name;
    private String email;
    private String status;
    private int age;
    private int id;

    public Employee(){}

    public Employee(String name, String email,String status, int age, int id){
        setName(name);
        setEmail(email);
        setStatus(status);
        setAge(age);
        setId(id);
    }

    public void setId(int id) {
        String stringId = String.valueOf(id);

        if(stringId.length() != 6) throw new IllegalArgumentException("The employee ID must have 6 digits");

        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAge(int age) {
        if(age < 18 || age >= 75) throw new IllegalArgumentException("People over 80 years of age or under 18 years of age cannot work");

        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setStatus(String status) {
        if (status == null) throw new IllegalArgumentException("Must write employee status");

        else if (!("Manager".equalsIgnoreCase(status) || "Seller".equalsIgnoreCase(status))) {
            throw new IllegalArgumentException("Can only be a Manager or Seller");
        }

        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setEmail(String email) {
        if(email == null) throw new IllegalArgumentException("Must write employee email");

        String[] tipEmails = {
                "@gmail.com",
                "@hotmail.com",
                "@outlook.com",
                "@yandex.ru",
                "@icloud.com",
                "@me.com",
                "@mac.com"
        };

        boolean search = false;

        //Revisa el si el correo cumple con los requisitos
        for (String i : tipEmails){
            if(email.contains(i)) {
                search = true;
                break;
            }
        }


        if (!search) throw new IllegalArgumentException("Unsupported email domain");

        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("Must write employee name");

        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return "\nName Employee: " + getName() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Status: " + getStatus() + "\n" +
                "Age: " + getAge() + "\n" +
                "Id Employee: " + getId() + "\n";
    }
}
