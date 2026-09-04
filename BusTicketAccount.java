public class BusTicketAccount {
    private final String bookingId;
    private final double ticketFare;
    private static final BoardingPenaltyCalculator CALCULATOR;

    static {
        CALCULATOR = new BoardingPenaltyCalculator(1.0);
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        if (bookingId == null || bookingId.trim().length() == 0) {
            throw new IllegalArgumentException("Booking ID cannot be null or empty.");
        }
        if (ticketFare < 0) {
            throw new IllegalArgumentException("Ticket fare cannot be negative.");
        }
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public static class Sleeper extends BusTicketAccount {
        public Sleeper(String bookingId, double ticketFare) {
            super(bookingId, ticketFare);
        }

        public Sleeper(String bookingId) {
            super(bookingId);
        }
    }

    final double calculatePenalty(int minutesLate) {
        return CALCULATOR.calculatePenalty(this.ticketFare, minutesLate);
    }

    void processAccount(BusTicketAccount account, double amount, int minutesLate) {
    }

    static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        if (accounts == null || amounts == null || minutesLateArray == null) {
            throw new IllegalArgumentException("Batch arrays cannot be null.");
        }
        if (accounts.length != amounts.length || accounts.length != minutesLateArray.length) {
            throw new IllegalArgumentException("Array lengths must match.");
        }

        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;
        double grandTotalPenalties = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            BusTicketAccount account = accounts[i];
            if (account == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (account instanceof Sleeper) {
                sleeper++;
            } else {
                regular++;
            }

            grandTotalPenalties += account.calculatePenalty(minutesLateArray[i]);
        }

        System.out.println(processed + " processed | " + nullSkipped + " null skipped | " + sleeper + " sleeper | " + regular + " regular | grand total penalties = " + grandTotalPenalties);
    }
    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new Sleeper("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {1200, 900, 700};
        int[] minutesLateArray = {10, 5, 0};

        processBatch(accounts, amounts, minutesLateArray);

        BusTicketAccount demoAccount = new BusTicketAccount("BK003", 1000);
        demoAccount.processAccount(demoAccount, 1000, 5);
    }
}
