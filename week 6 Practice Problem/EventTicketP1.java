public class EventTicketP1 {
    private String attendeeId;
    protected double basePrice;
    protected double balanceDue;

    public EventTicketP1(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.attendeeId = attendeeId.trim();
        this.basePrice = basePrice;
        this.balanceDue = basePrice;
    }

    public void pay(double amount) {
        this.balanceDue -= amount;
    }

    public double getBalanceDue() {
        return this.balanceDue;
    }

    public static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0;
        int rejected = 0;
        for (String id : attendeeIds) {
            try {
                new EventTicketP1(id, basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        try {
            new EventTicketP1("ST1", 500);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        WorkshopTicketP1 w = new WorkshopTicketP1("STU2", 1200, "AI/ML");
        w.pay(500);
        System.out.println(w.getBalanceDue());

        String[] batch = {"STU1", "ST1", "STU2", "STU3"};
        System.out.println(registerBatch(batch, 500));
    }
}

class WorkshopTicketP1 extends EventTicketP1 {
    private String track;

    public WorkshopTicketP1(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }
}