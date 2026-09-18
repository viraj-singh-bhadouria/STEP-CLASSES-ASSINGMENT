public class ProblemFourRunner {
    static class RaceEntry {
        protected String bibNumber;
        protected double entryFee;
        protected double amountPaid;

        public RaceEntry(String bibNumber, double entryFee) {
            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
            this.amountPaid = 0.0;
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }

        public String announce() {
            return "Race Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue();
        }
    }

    static class RunnerEntry extends RaceEntry {
        private String category;

        public RunnerEntry(String bibNumber, double entryFee, String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }

        public void pay(double amount) {
            this.amountPaid += amount;
        }

        @Override
        public String announce() {
            return "Runner Entry | Bib: " + bibNumber + " | Category: " + category + " | Balance: " + getBalanceDue();
        }
    }

    static class RelayTeamEntry extends RaceEntry {
        private int teamSize;

        public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
            super(bibNumber, entryFee);
            this.teamSize = teamSize;
        }

        public int getTeamSize() {
            return teamSize;
        }

        @Override
        public String announce() {
            return "Relay Team | Bib: " + bibNumber + " | Team Size: " + teamSize + " | Balance: " + getBalanceDue();
        }
    }

    public static String announceAll(RaceEntry[] entries) {
        StringBuilder sb = new StringBuilder();
        for (RaceEntry entry : entries) {
            sb.append(entry.announce());
            if (entry instanceof RelayTeamEntry) {
                RelayTeamEntry relay = (RelayTeamEntry) entry;
                sb.append(" [Team size via downcast: ").append(relay.getTeamSize()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        RunnerEntry runnerEntry = new RunnerEntry("BIB2001", 80, "Open 10K");
        RelayTeamEntry relayEntry = new RelayTeamEntry("BIB4001", 300, 4);
        runnerEntry.pay(-10);

        RaceEntry[] fleet = { runnerEntry, relayEntry };
        System.out.println(announceAll(fleet));
    }
}
