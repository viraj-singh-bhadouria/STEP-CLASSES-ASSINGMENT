public class DeliveryAccount {
    private final String studentId;
    private final double orderValue;
    private static final SurgeFeeCalculator SURGE_CALCULATOR;

    static {
        SURGE_CALCULATOR = new SurgeFeeCalculator(1.0);
    }

    public DeliveryAccount(String studentId, double orderValue) {
        if (studentId == null || studentId.trim().length() == 0) {
            throw new IllegalArgumentException();
        }
        if (orderValue < 0) {
            throw new IllegalArgumentException();
        }
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public static class Premium extends DeliveryAccount {
        public Premium(String studentId, double orderValue) {
            super(studentId, orderValue);
        }

        public Premium(String studentId) {
            super(studentId);
        }
    }

    final double calculateSurgeFee(int delayMinutes) {
        return SURGE_CALCULATOR.calculateSurgeFee(this.orderValue, delayMinutes);
    }

    void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
    }

    static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        if (accounts == null || amounts == null || delayMinutesArray == null) {
            throw new IllegalArgumentException();
        }
        if (accounts.length != amounts.length || accounts.length != delayMinutesArray.length) {
            throw new IllegalArgumentException();
        }

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double grandTotalSurgeFees = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            DeliveryAccount acc = accounts[i];
            if (acc == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (acc instanceof Premium) {
                premium++;
            } else {
                regular++;
            }

            grandTotalSurgeFees += acc.calculateSurgeFee(delayMinutesArray[i]);
        }

        System.out.println(processed + " processed | " + nullSkipped + " null skipped | " + premium + " premium | " + regular + " regular | grand total surge fees = " + grandTotalSurgeFees);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new Premium("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };
        double[] amounts = {500, 400, 300};
        int[] delayMinutesArray = {10, 5, 0};

        processBatch(accounts, amounts, delayMinutesArray);
    }
}