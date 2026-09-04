public final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        if (minimumSurgePercent < 0) {
            throw new IllegalArgumentException();
        }
        this.minimumSurgePercent = minimumSurgePercent;
    }

    final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException();
        }
        if (delayMinutes == 0) {
            return 0.0;
        }

        int tier1Minutes = delayMinutes;
        if (tier1Minutes > 5) {
            tier1Minutes = 5;
        }

        int tier2Minutes = 0;
        if (delayMinutes > 5) {
            tier2Minutes = delayMinutes - 5;
            if (tier2Minutes > 10) {
                tier2Minutes = 10;
            }
        }

        int tier3Minutes = 0;
        if (delayMinutes > 15) {
            tier3Minutes = delayMinutes - 15;
        }

        double tieredFee = orderValue * (tier1Minutes * 0.005 + tier2Minutes * 0.010 + tier3Minutes * 0.020);
        double floorFee = orderValue * (minimumSurgePercent / 100.0);

        if (tieredFee > floorFee) {
            return tieredFee;
        } else {
            return floorFee;
        }
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calc = new SurgeFeeCalculator(1.0);
        System.out.println("Rs " + calc.calculateSurgeFee(500, 0));
        System.out.println("Rs " + calc.calculateSurgeFee(500, 1));
        System.out.println("Rs " + calc.calculateSurgeFee(500, 16));
    }
}