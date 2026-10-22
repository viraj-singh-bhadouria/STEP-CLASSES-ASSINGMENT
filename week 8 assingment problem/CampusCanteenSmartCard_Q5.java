import java.util.*;

interface PricingPlan_Q5 {
    double calculatePrice(double fullPrice);
    String getName();
}

class DayScholarPlan_Q5 implements PricingPlan_Q5 {
    @Override
    public double calculatePrice(double fullPrice) {
        return fullPrice;
    }

    @Override
    public String getName() { return "Day Scholar"; }
}

class HostellerPlan_Q5 implements PricingPlan_Q5 {
    @Override
    public double calculatePrice(double fullPrice) {
        return fullPrice * 0.90;
    }

    @Override
    public String getName() { return "Hosteller"; }
}

class StaffPlan_Q5 implements PricingPlan_Q5 {
    @Override
    public double calculatePrice(double fullPrice) {
        return fullPrice * 0.80;
    }

    @Override
    public String getName() { return "Staff"; }
}

class Transaction_Q5 {
    private String id;
    private String description;
    private double amount;
    private boolean isRefunded;

    public Transaction_Q5(String id, String description, double amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.isRefunded = false;
    }

    public String getId() { return id; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public boolean isRefunded() { return isRefunded; }
    public void setRefunded(boolean refunded) { isRefunded = refunded; }
}

class SmartCard_Q5 {
    private String cardId;
    private PricingPlan_Q5 pricingPlan;
    private double balance = 0.0;
    private boolean isBlocked = false;
    private List<Transaction_Q5> transactions = new ArrayList<>();

    public SmartCard_Q5(String cardId, PricingPlan_Q5 pricingPlan) {
        this.cardId = cardId;
        this.pricingPlan = pricingPlan;
    }

    public String getCardId() { return cardId; }
    public double getBalance() { return balance; }

    public void topUp(double amount) {
        if (isBlocked) {
            System.out.println("Operation rejected: Card is blocked.");
            return;
        }
        if (amount < 100) {
            System.out.println("Top-up failed: Minimum top-up amount is 100.");
            return;
        }
        if (balance + amount > 5000) {
            System.out.println("Top-up failed: Balance cannot exceed 5000.");
            return;
        }

        balance += amount;
        transactions.add(new Transaction_Q5("TXN-" + (transactions.size() + 1), "Top-up", amount));
        System.out.printf("%s topped up with %.2f. Balance: ₹%.2f.%n", cardId, amount, balance);
    }

    public Transaction_Q5 purchase(String item, double fullPrice) {
        if (isBlocked) {
            System.out.println("Operation rejected: Card is blocked.");
            return null;
        }

        double finalPrice = pricingPlan.calculatePrice(fullPrice);
        if (balance < finalPrice) {
            System.out.printf("Purchase failed: Insufficient balance (required %.2f, available %.2f).%n", finalPrice, balance);
            return null;
        }

        balance -= finalPrice;
        Transaction_Q5 txn = new Transaction_Q5(item, item, -finalPrice);
        transactions.add(txn);
        System.out.printf("%s purchased for %.2f. Balance: ₹%.2f.%n", item, finalPrice, balance);
        return txn;
    }

    public boolean refund(Transaction_Q5 txn) {
        if (isBlocked) {
            System.out.println("Operation rejected: Card is blocked.");
            return false;
        }

        if (txn == null || !transactions.contains(txn)) {
            System.out.println("Refund rejected: Transaction not found.");
            return false;
        }

        if (txn.isRefunded()) {
            System.out.printf("Refund rejected: %s has already been refunded.%n", txn.getDescription());
            return false;
        }

        double refundAmount = Math.abs(txn.getAmount());
        balance += refundAmount;
        txn.setRefunded(true);
        transactions.add(new Transaction_Q5("REF-" + (transactions.size() + 1), "Refund " + txn.getDescription(), refundAmount));

        System.out.printf("Refund of %.2f for %s processed. Balance: ₹%.2f.%n", refundAmount, txn.getDescription(), balance);
        return true;
    }

    public void printMiniStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Mini-statement for %s: ", cardId));

        for (int i = 0; i < transactions.size(); i++) {
            Transaction_Q5 t = transactions.get(i);
            if (t.getAmount() > 0) {
                sb.append(String.format("+%.2f", t.getAmount()));
            } else {
                sb.append(String.format("%.2f", t.getAmount()));
            }

            if (i < transactions.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(String.format(" = %.2f", balance));
        System.out.println(sb.toString());
    }
}

public class CampusCanteenSmartCard_Q5 {
    public static void main(String[] args) {
        SmartCard_Q5 card = new SmartCard_Q5("C-2045", new HostellerPlan_Q5());

        card.topUp(500);
        Transaction_Q5 vegThali = card.purchase("Veg Thali", 120);
        Transaction_Q5 coldCoffee = card.purchase("Cold Coffee", 60);

        card.purchase("Items", 400);

        card.refund(vegThali);
        card.refund(vegThali);

        card.printMiniStatement();
    }
}
