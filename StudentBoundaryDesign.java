public class StudentBoundaryDesign {

    public static class BrokenSrmStudent {
        public static String name;
        public static String regNo;
        public static int attendance;

        public BrokenSrmStudent(String n, String r, int a) {
            name = n;
            regNo = r;
            attendance = a;
        }
    }

    public static class FixedSrmStudent {
        private String name;
        private String regNo;
        private int attendance;

        public static String university = "SRM";
        public static int admissionCount = 1010;

        public FixedSrmStudent(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;
            admissionCount++;
            this.regNo = "RA23110030" + admissionCount;
        }

        public void printIdCard() {
            System.out.println(name + " | " + regNo);
        }

        public static void printTotalAdmissions() {
            int countSoFar = admissionCount - 1010;
            System.out.println("Students admitted so far: " + countSoFar);
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenSrmStudent b1 = new BrokenSrmStudent("Ravi", "RA01", 80);
        BrokenSrmStudent b2 = new BrokenSrmStudent("Meera", "RA02", 85);
        System.out.println(BrokenSrmStudent.name);
        System.out.println(BrokenSrmStudent.name);

        System.out.println("\nFixed version:");
        FixedSrmStudent f1 = new FixedSrmStudent("Ravi", 80);
        FixedSrmStudent f2 = new FixedSrmStudent("Meera", 85);
        
        f1.printIdCard();
        f2.printIdCard();
        FixedSrmStudent.printTotalAdmissions();
    }
}
