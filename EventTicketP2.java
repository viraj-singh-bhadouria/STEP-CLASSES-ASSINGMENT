public class EventTicketP2 {
    protected String attendeeId;
    protected double basePrice;
    protected double balanceDue;

    public EventTicketP2(String attendeeId, double basePrice) {
        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.balanceDue = basePrice;
    }

    public double getBalanceDue() {
        return this.balanceDue;
    }

    public String printTicket() {
        return "Standard Event Ticket | Balance Due: " + this.getBalanceDue();
    }

    public static String classifyGeneration(EventTicketP2 ticket) {
        if (ticket instanceof PremiumWorkshopTicketP2) {
            return "Multilevel descendant (3 generations deep)";
        } else if (ticket instanceof HackathonTicketP2) {
            return "Hierarchical sibling (independent branch)";
        }
        return "";
    }

    public static double getTotalBalanceDue(EventTicketP2[] tickets) {
        double total = 0;
        for (EventTicketP2 ticket : tickets) {
            total += ticket.getBalanceDue();
        }
        return total;
    }

    public static void main(String[] args) {
        EventTicketP2 standardTicket = new EventTicketP2("STU1", 500);
        System.out.println(standardTicket.printTicket());

        WorkshopTicketP2 workshopTicket = new WorkshopTicketP2("STU2", 1200, "AI/ML");
        System.out.println(workshopTicket.printTicket());

        PremiumWorkshopTicketP2 premiumTicket = new PremiumWorkshopTicketP2("STU3", 2000, "Cloud Native", 300);
        System.out.println(premiumTicket.printTicket());

        HackathonTicketP2 hackathonTicket = new HackathonTicketP2("STU4", 800, "Byte Force");
        System.out.println(hackathonTicket.printTicket());

        System.out.println(classifyGeneration(premiumTicket));
        System.out.println(classifyGeneration(hackathonTicket));

        EventTicketP2[] batch = {standardTicket, workshopTicket, premiumTicket, hackathonTicket};
        System.out.println(getTotalBalanceDue(batch));
    }
}

class WorkshopTicketP2 extends EventTicketP2 {
    protected String track;

    public WorkshopTicketP2(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }

    @Override
    public String printTicket() {
        return "Workshop Ticket | Track: " + this.track + " | Balance Due: " + this.getBalanceDue();
    }
}

class PremiumWorkshopTicketP2 extends WorkshopTicketP2 {
    private double kitFee;

    public PremiumWorkshopTicketP2(String attendeeId, double basePrice, String track, double kitFee) {
        super(attendeeId, basePrice, track);
        this.kitFee = kitFee;
    }

    @Override
    public String printTicket() {
        return "Premium Workshop Ticket | Track: " + this.track + " | Kit Fee: " + this.kitFee + " | Balance Due: " + this.getBalanceDue();
    }
}

class HackathonTicketP2 extends EventTicketP2 {
    private String teamName;

    public HackathonTicketP2(String attendeeId, double basePrice, String teamName) {
        super(attendeeId, basePrice);
        this.teamName = teamName;
    }

    @Override
    public String printTicket() {
        return "Hackathon Ticket | Team: " + this.teamName + " | Balance Due: " + this.getBalanceDue();
    }
}
