public class FeeAccountSystem {

    public static class FeeAccount {
        private String regNo;
        private double totalFee;
        private double amountPaid;

        public FeeAccount(String regNo, double totalFee, double amountPaid) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            this.amountPaid = amountPaid;
        }

        public void pay(double amount) {
            if (amount <= 0) {
                System.out.println("Payment rejected: Amount must be positive.");
                return;
            }
            this.amountPaid += amount;
        }

        public double getDue() {
            return totalFee - amountPaid;
        }

        public String getRegNo() {
            return regNo;
        }
    }

    public static class HostelFeeAccount extends FeeAccount {
        public HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
            super(regNo, totalFee, amountPaid);
        }

        public void payInTwoInstallments(double amount) {
            if (amount > 0) {
                pay(amount / 2);
                pay(amount / 2);
            }
        }
    }

    public static class ScholarshipFeeAccount extends FeeAccount {
        private double scholarshipPercent;

        public ScholarshipFeeAccount(String regNo, double totalFee, double amountPaid, double scholarshipPercent) {
            super(regNo, totalFee, amountPaid);
            this.scholarshipPercent = scholarshipPercent;
        }

        public double effectiveDue() {
            double rawDue = getDue();
            return rawDue - (rawDue * (scholarshipPercent / 100.0));
        }
    }

    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("REG101", 150000, 150000);
        HostelFeeAccount hostel = new HostelFeeAccount("REG102", 200000, 60000);
        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount("REG103", 180000, 0, 20.0);

        FeeAccount[] accounts = new FeeAccount[] { plain, hostel, scholarship };

        for (int i = 0; i < accounts.length; i++) {
            FeeAccount acc = accounts[i];
            if (acc instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount sAcc = (ScholarshipFeeAccount) acc;
                System.out.println("Scholarship account effective due: Rs " + sAcc.effectiveDue());
            } else if (acc instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs " + acc.getDue());
            } else if (acc instanceof FeeAccount) {
                System.out.println("Plain account due: Rs " + acc.getDue());
            }
        }
    }
}