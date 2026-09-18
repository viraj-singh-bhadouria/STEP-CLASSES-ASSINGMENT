public class ProblemFiveRunner {
    static class RaceEntry {
        private static int bibCounter = 0;
        private final String entryCode;
        protected String bibNumber;
        protected double entryFee;
        protected double amountPaid;

        public RaceEntry(String bibNumber, double entryFee) {
            bibCounter++;
            this.entryCode = "ENTRY" + bibCounter;
            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
            this.amountPaid = 0.0;
        }

        public static int getBibCounter() {
            return bibCounter;
        }

        public String getEntryCode() {
            return entryCode;
        }

        public void pay(double amount) {
            this.amountPaid += amount;
        }

        public void pay(double amount, String mode) {
            System.out.println("Paying via " + mode);
            pay(amount);
        }

        public static boolean isValidDiscountCode(String code) {
            if (code == null || code.length() != 5) {
                return false;
            }
            if (code.charAt(0) != 'M') {
                return false;
            }
            for (int i = 1; i <= 3; i++) {
                if (!Character.isDigit(code.charAt(i))) {
                    return false;
                }
            }
            return Character.isUpperCase(code.charAt(4));
        }

        public static String settleNight(RaceEntry[] entries) {
            int processed = 0;
            int nullSkipped = 0;
            int relay = 0;
            int individual = 0;

            for (RaceEntry entry : entries) {
                if (entry == null) {
                    nullSkipped++;
                    continue;
                }
                processed++;
                if (entry instanceof RelayTeamEntry) {
                    relay++;
                } else {
                    individual++;
                }
            }
            return processed + " processed | " + nullSkipped + " null skipped | " + relay + " relay | " + individual + " individual";
        }
    }

    static class RunnerEntry extends RaceEntry {
        public RunnerEntry(String bibNumber, double entryFee) {
            super(bibNumber, entryFee);
        }
    }

    static class EliteRunnerEntry extends RunnerEntry {
        public EliteRunnerEntry(String bibNumber, double entryFee) {
            super(bibNumber, entryFee);
        }
    }

    static class RelayTeamEntry extends RaceEntry {
        public RelayTeamEntry(String bibNumber, double entryFee) {
            super(bibNumber, entryFee);
        }
    }

    public static void main(String[] args) {
        System.out.println(RaceEntry.isValidDiscountCode("M123A"));
        System.out.println(RaceEntry.isValidDiscountCode("M12A"));
        System.out.println(RaceEntry.isValidDiscountCode("X123A"));

        EliteRunnerEntry eliteEntry = new EliteRunnerEntry("BIB3001", 150);
        RelayTeamEntry relayEntry = new RelayTeamEntry("BIB4001", 300);

        eliteEntry.pay(10, "UPI");

        RaceEntry[] entries = { eliteEntry, null, relayEntry };
        System.out.println(RaceEntry.settleNight(entries));
        System.out.println(RaceEntry.getBibCounter());
    }
}
