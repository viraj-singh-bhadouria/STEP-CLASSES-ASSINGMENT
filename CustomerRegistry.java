import java.util.*;

class Customer {
    private int id;
    private String name;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

public class CustomerRegistry {
    private Set<Customer> registry = new HashSet<>();

    public boolean addCustomer(Customer customer) {
        boolean added = registry.add(customer);
        if (added) {
            System.out.println("true");
        } else {
            System.out.println("false (duplicate rejected)");
        }
        return added;
    }

    public boolean containsCustomer(Customer customer) {
        boolean exists = registry.contains(customer);
        System.out.println("contains: " + exists);
        return exists;
    }

    public int getUniqueCount() {
        int count = registry.size();
        System.out.println("unique count " + count);
        return count;
    }

    public static void main(String[] args) {
        CustomerRegistry system = new CustomerRegistry();

        Customer c1 = new Customer(101, "Asha");
        Customer c2 = new Customer(101, "Asha");
        Customer c3 = new Customer(102, "Ravi");

        system.addCustomer(c1);
        system.addCustomer(c2);
        system.addCustomer(c3);
        system.getUniqueCount();
        system.containsCustomer(c1);
    }
}
