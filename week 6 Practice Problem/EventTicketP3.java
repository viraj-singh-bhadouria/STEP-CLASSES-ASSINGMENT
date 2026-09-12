public class EventTicketP3 {
    protected double basePrice;
    protected double balanceDue;
    private double[] lateFeeHistory;
    private int lateFeeCount;

    public EventTicketP3(double basePrice) {
        this.basePrice = basePrice;
        this.balanceDue = basePrice;
        this.lateFeeHistory = new double[10];
        this.lateFeeCount = 0;
    }

    public void pay(double amount) {
        this.balanceDue -= amount;
    }

    public double getBalanceDue() {
        return this.balanceDue;
    }

    protected void applyLateFee(double amount) {
        if (amount > 0 && this.lateFeeCount < this.lateFeeHistory.length) {
            this.lateFeeHistory[this.lateFeeCount++] = amount;
            this.balanceDue += amount;
        }
    }

    public double[] getLateFeeHistory() {
        double[] copy = new double[this.lateFeeCount];
        System.arraycopy(this.lateFeeHistory, 0, copy, 0, this.lateFeeCount);
        return copy;
    }

    public static void main(String[] args) {
        WorkshopTicketP3 w = new WorkshopTicketP3(1200);
        w.pay(1200);
        w.applyLateFee(100);
        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();
        System.out.println(java.util.Arrays.toString(history));
        
        history[0] = 999;
        System.out.println(java.util.Arrays.toString(w.getLateFeeHistory()));
    }
}

class WorkshopTicketP3 extends EventTicketP3 {
    public WorkshopTicketP3(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}
