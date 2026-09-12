public class EventTicketP4 {
    protected double basePrice;

    public EventTicketP4(double basePrice) {
        this.basePrice = basePrice;
    }

    public String printTicket() {
        return "Standard Balance: " + this.basePrice + " | ";
    }

    public static String batchPrint(EventTicketP4[] tickets) {
        StringBuilder sb = new StringBuilder();
        for (EventTicketP4 ticket : tickets) {
            sb.append(ticket.printTicket());
            if (ticket instanceof WorkshopTicketP4) {
                WorkshopTicketP4 wt = (WorkshopTicketP4) ticket;
                sb.append("[Track via downcast: ").append(wt.getTrack()).append("] ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        EventTicketP4[] tickets = {
            new EventTicketP4(500), 
            new WorkshopTicketP4(1200, "AI/ML")
        };
        System.out.println(batchPrint(tickets));

        try {
            EventTicketP4 plain = new EventTicketP4(500);
            WorkshopTicketP4 bad = (WorkshopTicketP4) plain;
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}

class WorkshopTicketP4 extends EventTicketP4 {
    private String track;

    public WorkshopTicketP4(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }

    public String getTrack() {
        return this.track;
    }

    @Override
    public String printTicket() {
        return "Workshop | Track: " + this.track + " Balance: " + this.basePrice + " ";
    }
}
