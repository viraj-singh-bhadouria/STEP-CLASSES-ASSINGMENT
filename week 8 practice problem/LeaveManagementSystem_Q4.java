abstract class Employee_Q4 {
    private String empId;
    private String name;
    private String empType;

    public Employee_Q4(String empId, String name, String empType) {
        this.empId = empId;
        this.name = name;
        this.empType = empType;
    }

    public String getName() { return name; }
    public String getEmpType() { return empType; }
}

class FullTimeEmployee_Q4 extends Employee_Q4 {
    public FullTimeEmployee_Q4(String empId, String name) {
        super(empId, name, "Full-time");
    }
}

class PartTimeEmployee_Q4 extends Employee_Q4 {
    public PartTimeEmployee_Q4(String empId, String name) {
        super(empId, name, "Part-time");
    }
}

class LeaveRequest_Q4 {
    private Employee_Q4 employee;
    private String startDate;
    private String endDate;
    private String status;

    public LeaveRequest_Q4(Employee_Q4 employee, String startDate, String endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "Pending";
        System.out.printf("Leave request submitted by %s for %s to %s. Status: %s.%n", employee.getName(), startDate, endDate, status);
    }

    public boolean updateStatus(String newStatus) {
        if ("Approved".equalsIgnoreCase(this.status) || "Rejected".equalsIgnoreCase(this.status)) {
            System.out.printf("Cannot change status: %s request cannot revert to Pending.%n", this.status);
            return false;
        }
        this.status = newStatus;
        System.out.printf("Leave request for %s approved. Status: %s.%n", employee.getName(), this.status);
        return true;
    }

    public String getStatus() { return status; }
}

class LeaveManager_Q4 {
    public void reviewRequest(LeaveRequest_Q4 request, boolean approve) {
        String newStatus = approve ? "Approved" : "Rejected";
        request.updateStatus(newStatus);
    }
}

public class LeaveManagementSystem_Q4 {
    public static void main(String[] args) {
        LeaveManager_Q4 manager = new LeaveManager_Q4();

        Employee_Q4 john = new FullTimeEmployee_Q4("E001", "John Doe");
        LeaveRequest_Q4 req1 = new LeaveRequest_Q4(john, "2024-10-10", "2024-10-12");
        manager.reviewRequest(req1, true);

        Employee_Q4 jane = new PartTimeEmployee_Q4("E002", "Jane Smith");
        LeaveRequest_Q4 req2 = new LeaveRequest_Q4(jane, "2024-11-01", "2024-11-05");

        req1.updateStatus("Pending");
    }
}
