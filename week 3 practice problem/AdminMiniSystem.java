public class AdminMiniSystem {

    public static class HostelFeeAccount {
        private String regNo;
        private double totalFee;
        private double amountPaid;

        public HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
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
    }

    public static class HostelRoom {
        private String roomNo;
        private int capacity;
        private int occupied;

        public HostelRoom(String roomNo, int capacity, int occupied) {
            this.roomNo = roomNo;
            this.capacity = capacity;
            this.occupied = occupied;
        }

        public boolean allot() {
            if (occupied < capacity) {
                occupied++;
                return true;
            }
            return false;
        }

        public String getRoomNo() {
            return roomNo;
        }
    }

    public static class SrmStudent {
        private String name;
        private String regNo;
        private HostelFeeAccount feeAccount;
        private HostelRoom room;

        public static int totalStudents = 0;

        public SrmStudent(String name, String regNo, HostelFeeAccount feeAccount, HostelRoom room) {
            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;
            this.room = room;
            totalStudents++;
        }

        public String fullStatus() {
            String roomDisplay = (room != null) ? room.getRoomNo() : "unallotted";
            double due = (feeAccount != null) ? feeAccount.getDue() : 0.0;
            return name + " | Due: Rs " + due + " | Room: " + roomDisplay;
        }
    }

    public static void main(String[] args) {
        HostelFeeAccount fee1 = new HostelFeeAccount("RA01", 200000, 60000);
        HostelFeeAccount fee2 = new HostelFeeAccount("RA02", 180000, 0);
        HostelFeeAccount fee3 = new HostelFeeAccount("RA03", 200000, 0);

        HostelRoom room1 = new HostelRoom("C-214", 3, 2);
        HostelRoom room2 = new HostelRoom("C-507", 2, 1);

        room1.allot();
        room2.allot();

        SrmStudent s1 = new SrmStudent("Ravi", "RA01", fee1, room1);
        SrmStudent s2 = new SrmStudent("Anitha", "RA02", fee2, room2);
        SrmStudent s3 = new SrmStudent("Karthik", "RA03", fee3, null);

        fee2.pay(-5000);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}