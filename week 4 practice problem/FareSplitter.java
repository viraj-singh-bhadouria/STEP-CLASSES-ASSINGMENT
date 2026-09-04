public class FareSplitter {
    private final String tripId;
    private final double totalFare;
    private final int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (tripId == null || tripId.trim().length() == 0) {
            throw new IllegalArgumentException("Trip ID must not be empty.");
        }
        if (totalFare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative.");
        }
        if (passengerCount <= 0) {
            throw new IllegalArgumentException("Passenger count must be positive.");
        }
        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0);
    }

    double[] fareBreakdown() {
        long totalPaisa = Math.round(this.totalFare * 100.0);
        long baseShare = totalPaisa / this.passengerCount;
        long remainder = totalPaisa % this.passengerCount;

        double[] breakdown = new double[this.passengerCount];
        int remainderStartIndex = this.passengerCount - (int) remainder;

        for (int i = 0; i < this.passengerCount; i++) {
            long share = baseShare + (i >= remainderStartIndex ? 1 : 0);
            breakdown[i] = share / 100.0;
        }
        return breakdown;
    }

    boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }
    public static void main(String[] args) {
        FareSplitter split1 = new FareSplitter("TRIP001", 100000, 3);
        double[] shares1 = split1.fareBreakdown();
        System.out.print("[");
        for (int i = 0; i < shares1.length; i++) {
            System.out.print(shares1[i] + (i < shares1.length - 1 ? ", " : ""));
        }
        System.out.println("]");

        FareSplitter split2 = new FareSplitter("TRIP003");
        double[] shares2 = split2.fareBreakdown();
        System.out.print("[");
        for (int i = 0; i < shares2.length; i++) {
            System.out.print(shares2[i] + (i < shares2.length - 1 ? ", " : ""));
        }
        System.out.println("]");

        System.out.println("Is overdue: " + split1.isConfirmationOverdue(2, 3));
    }
}
