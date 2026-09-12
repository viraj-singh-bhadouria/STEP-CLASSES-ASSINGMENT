public class EventTicketP5 {
    private static int totalIssued = 0;
    private final String ticketId;
    protected double basePrice;
    protected double balanceDue;

    public EventTicketP5(double basePrice) {
        totalIssued++;
        this.ticketId = "TCK-" + (1000 + totalIssued);
        this.basePrice = basePrice;
        this.balanceDue = basePrice;
    }

    public String getTicketId() {
        return this.ticketId;
    }

    public double getBalanceDue() {
        return this.balanceDue;
    }

    public void pay(double amount) {
        this.balanceDue -= amount;
    }

    public void pay(double amount, String mode) {
        System.out.print(mode + " payment: ");
        this.pay(amount);
    }

    public static int getTicketsIssued() {
        return totalIssued;
    }

    public static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }
        if (code.charAt(0) != 'F') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || 
            !Character.isDigit(code.charAt(2)) || 
            !Character.isDigit(code.charAt(3))) {
            return false;
        }
        if (!Character.isUpperCase(code.charAt(4))) {
            return false;
        }
        return true;
    }

    public static String processNightlySettlement(EventTicketP5[] tickets) {
        if (tickets == null) return "";
        
        int processed = 0, nullSkipped = 0, group = 0, individual = 0;
        for (EventTicketP5 ticket : tickets) {
            if (ticket == null) {
                nullSkipped++;
            } else {
                processed++;
                if (ticket instanceof GroupTicketP5) {
                    group++;
                } else {
                    individual++;
                }
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + group + " group | " + individual + " individual";
    }

    public static void main(String[] args) {
        EventTicketP5 t1 = new EventTicketP5(500);
        System.out.println(t1.getTicketId());
        System.out.println(EventTicketP5.getTicketsIssued());

        System.out.println(EventTicketP5.isValidPromoCode("F123A"));
        System.out.println(EventTicketP5.isValidPromoCode("F12A"));
        System.out.println(EventTicketP5.isValidPromoCode("X123A"));

        t1.pay(200);
        t1.pay(200, "UPI");
        System.out.println("\n" + t1.getBalanceDue());

        EventTicketP5[] settlementBatch = {
            new GroupTicketP5(2000, 5),
            null,
            new EventTicketP5(500)
        };
        System.out.println(processNightlySettlement(settlementBatch));
    }
}

class GroupTicketP5 extends EventTicketP5 {
    private int groupSize;

    public GroupTicketP5(double basePrice, int groupSize) {
        super(basePrice);
        this.groupSize = groupSize;
    }
}
