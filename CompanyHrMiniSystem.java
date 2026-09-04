public class CompanyHrMiniSystem {

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

        public double getEffectivePay() {
            return salary;
        }
    }

    public static class ManagerEmployee extends Employee {
        private double teamBonus;

        public ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
            super(empId, empName, salary);
            this.teamBonus = teamBonus;
        }

        @Override
        public double getEffectivePay() {
            return getSalary() + teamBonus;
        }
    }

    public static class InternEmployee extends Employee {
        private double stipendCap;

        public InternEmployee(String empId, String empName, double salary, double stipendCap) {
            super(empId, empName, salary);
            this.stipendCap = stipendCap;
        }

        @Override
        public double getEffectivePay() {
            return Math.min(getSalary(), stipendCap);
        }
    }

    public static class ParkingSlot {
        private String slotNo;
        private int capacity;
        private int occupiedCount;

        public ParkingSlot(String slotNo, int capacity, int occupiedCount) {
            this.slotNo = slotNo;
            this.capacity = capacity;
            this.occupiedCount = occupiedCount;
        }

        public boolean allot() {
            if (occupiedCount < capacity) {
                occupiedCount++;
                return true;
            }
            return false;
        }

        public String getSlotNo() {
            return slotNo;
        }
    }

    public static class CompanyEmployeeRecord {
        private String name;
        private String empId;
        private Employee employee;
        private ParkingSlot slot;

        public static int totalRecords = 0;

        public CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
            this.name = name;
            this.empId = empId;
            this.employee = employee;
            this.slot = slot;
            totalRecords++;
        }

        public String fullProfile() {
            String slotDisplay = (slot != null) ? slot.getSlotNo() : "no parking assigned";
            double pay = (employee != null) ? employee.getEffectivePay() : 0.0;
            return name + " | Pay: Rs " + pay + " | Slot: " + slotDisplay;
        }
    }

    public static void main(String[] args) {
        ManagerEmployee manager = new ManagerEmployee("E101", "Divya", 70000, 8000);
        Employee plain = new Employee("E102", "Karan", 40000);
        InternEmployee intern = new InternEmployee("E103", "Meera", 12000, 10000);

        ParkingSlot slot1 = new ParkingSlot("A1", 4, 3);
        ParkingSlot slot2 = new ParkingSlot("A2", 5, 4);

        slot1.allot();
        slot2.allot();

        CompanyEmployeeRecord r1 = new CompanyEmployeeRecord("Divya", "E101", manager, slot1);
        CompanyEmployeeRecord r2 = new CompanyEmployeeRecord("Karan", "E102", plain, slot2);
        CompanyEmployeeRecord r3 = new CompanyEmployeeRecord("Meera", "E103", intern, null);

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
