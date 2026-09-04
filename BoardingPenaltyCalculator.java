public final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        if (minimumPenaltyPercent < 0) {
            throw new IllegalArgumentException("Minimum penalty percent cannot be negative.");
        }
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("Fare and minutes late must be non-negative.");
        }
        if (minutesLate == 0) {
            return 0.0;
        }

        int tier1Minutes = minutesLate;
        if (tier1Minutes > 5) {
            tier1Minutes = 5;
        }

        int tier2Minutes = 0;
        if (minutesLate > 5) {
            tier2Minutes = minutesLate - 5;
            if (tier2Minutes > 10) {
                tier2Minutes = 10;
            }
        }

        int tier3Minutes = 0;
        if (minutesLate > 15) {
            tier3Minutes = minutesLate - 15;
        }

        double tieredPenalty = ticketFare * (tier1Minutes * 0.005 + tier2Minutes * 0.010 + tier3Minutes * 0.020);
        double floorPenalty = ticketFare * (this.minimumPenaltyPercent / 100.0);

        if (tieredPenalty > floorPenalty) {
            return tieredPenalty;
        } else {
            return floorPenalty;
        }
    }
    public static void main(String[] args) {
        BoardingPenaltyCalculator calc = new BoardingPenaltyCalculator(1.0);

        System.out.println("Rs " + calc.calculatePenalty(1000, 0));
        System.out.println("Rs " + calc.calculatePenalty(1000, 1));
        System.out.println("Rs " + calc.calculatePenalty(1000, 16));
    }
}
