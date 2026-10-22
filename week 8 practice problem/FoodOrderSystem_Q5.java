import java.util.*;

interface IPaymentMethod_Q5 {
    boolean pay(double amount);
}

class CreditCardPayment_Q5 implements IPaymentMethod_Q5 {
    @Override
    public boolean pay(double amount) {
        System.out.println("Payment via Credit Card successful.");
        return true;
    }
}

class DigitalWalletPayment_Q5 implements IPaymentMethod_Q5 {
    private boolean simulateSuccess;

    public DigitalWalletPayment_Q5(boolean simulateSuccess) {
        this.simulateSuccess = simulateSuccess;
    }

    @Override
    public boolean pay(double amount) {
        if (simulateSuccess) {
            System.out.println("Payment via Digital Wallet successful.");
            return true;
        } else {
            System.out.println("Payment via Digital Wallet failed.");
            return false;
        }
    }
}

class LineItem_Q5 {
    private String itemName;
    private int quantity;
    private double pricePerUnit;

    public LineItem_Q5(String itemName, int quantity, double pricePerUnit) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
    public double getTotal() { return quantity * pricePerUnit; }
}

class Order_Q5 {
    private static int idCounter = 123;
    private int orderId;
    private List<LineItem_Q5> lineItems;
    private String status;

    public Order_Q5() {
        this.orderId = idCounter++;
        this.lineItems = new ArrayList<>();
        this.status = "Pending Payment";
        System.out.println("Order created.");
    }

    public void addItem(LineItem_Q5 item) {
        lineItems.add(item);
        System.out.printf("Added %s (Qty %d)%n", item.getItemName(), item.getQuantity());
    }

    public void placeAndPay(IPaymentMethod_Q5 paymentMethod) {
        if (lineItems.isEmpty()) {
            System.out.println("Cannot place order: Order must contain at least one item.");
            return;
        }

        System.out.println("Order placed successfully.");
        double totalAmount = 0.0;
        for (LineItem_Q5 item : lineItems) {
            totalAmount += item.getTotal();
        }

        boolean success = paymentMethod.pay(totalAmount);

        if (success) {
            this.status = "Paid";
            System.out.printf("Order status: Paid. Notification: Order #%d placed and paid.%n", orderId);
        } else {
            this.status = "Pending Payment";
            System.out.printf("Order status: Pending Payment. Notification: Order #%d placed, awaiting payment.%n", orderId);
        }
    }
}

public class FoodOrderSystem_Q5 {
    public static void main(String[] args) {
        Order_Q5 order1 = new Order_Q5();
        order1.addItem(new LineItem_Q5("Pizza", 2, 12.0));
        order1.addItem(new LineItem_Q5("Soda", 1, 3.0));

        Order_Q5 emptyOrder = new Order_Q5();
        emptyOrder.placeAndPay(new CreditCardPayment_Q5());

        order1.placeAndPay(new CreditCardPayment_Q5());

        Order_Q5 order2 = new Order_Q5();
        order2.addItem(new LineItem_Q5("Burger", 1, 8.0));
        order2.placeAndPay(new DigitalWalletPayment_Q5(false));
    }
}
