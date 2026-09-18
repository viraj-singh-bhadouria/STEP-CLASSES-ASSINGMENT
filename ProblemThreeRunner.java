import java.util.Arrays;

public class ProblemThreeRunner {
    static class RaceEntry {
        private String bibNumber;
        private double entryFee;
        private double amountPaid;
        private double[] lateFeeHistory = new double[10];
        private int lateFeeCount = 0;

        public RaceEntry(String bibNumber, double entryFee) {
            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
            this.amountPaid = 0.0;
        }

        public void pay(double amount) {
            this.amountPaid += amount;
        }

        protected void applyLateFee(double amount) {
            this.entryFee += amount;
            if (lateFeeCount < lateFeeHistory.length) {
                lateFeeHistory[lateFeeCount++] = amount;
            }
        }

        public double[] getLateFeeHistory() {
            double[] copy = new double[lateFeeCount];
            System.arraycopy(lateFeeHistory, 0, copy, 0, lateFeeCount);
            return copy;
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }
    }

    static class RunnerEntry extends RaceEntry {
        private String category;

        public RunnerEntry(String bibNumber, double entryFee, String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }

        @Override
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        r.applyLateFee(20);
        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();
        history[0] = 999;
        System.out.println(Arrays.toString(r.getLateFeeHistory()));
    }
}