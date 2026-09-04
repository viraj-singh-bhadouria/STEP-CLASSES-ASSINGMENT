public class EmployeeSystem {

    public static class Employee {
        private String empId;
        private String empName;
        private double salary;

        public Employee(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
        }

        public double getSalary() {
            return salary;
        }

        public String getEmpId() {
            return empId;
        }

        public String getEmpName() {
            return empName;
        }
    }

    public static class ManagerEmployee extends Employee {
        private double teamBonus;

        public ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
            super(empId, empName, salary);
            this.teamBonus = teamBonus;
        }

        public double effectiveSalary() {
            return getSalary() + teamBonus;
        }
    }

    public static class InternEmployee extends Employee {
        private double stipendCap;

        public InternEmployee(String empId, String empName, double salary, double stipendCap) {
            super(empId, empName, salary);
            this.stipendCap = stipendCap;
        }

        public double effectiveSalary() {
            return Math.min(getSalary(), stipendCap);
        }
    }

    public static void main(String[] args) {
        Employee plain = new Employee("E101", "Plain Employee", 40000);
        ManagerEmployee manager = new ManagerEmployee("E102", "Manager Employee", 70000, 8000);
        InternEmployee intern = new InternEmployee("E103", "Intern Employee", 12000, 10000);

        Employee[] employees = new Employee[] { plain, manager, intern };

        for (int i = 0; i < employees.length; i++) {
            Employee emp = employees[i];
            if (emp instanceof ManagerEmployee) {
                ManagerEmployee m = (ManagerEmployee) emp;
                System.out.println("Manager effective pay: Rs " + m.effectiveSalary());
            } else if (emp instanceof InternEmployee) {
                InternEmployee in = (InternEmployee) emp;
                System.out.println("Intern effective pay: Rs " + in.effectiveSalary());
            } else if (emp instanceof Employee) {
                System.out.println("Plain employee pay: Rs " + emp.getSalary());
            }
        }
    }
}
