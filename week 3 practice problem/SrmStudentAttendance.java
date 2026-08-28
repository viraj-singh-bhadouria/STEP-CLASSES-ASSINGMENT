public class SrmStudentAttendance {

    public static class SrmStudent {
        private String name;
        private String regNo;
        private int attendance;

        public SrmStudent(String name, String regNo, int attendance) {
            this.name = name;
            this.regNo = regNo;
            this.attendance = attendance;
        }

        public void addAttendanceUpdate(int newAttendance) {
            this.attendance = newAttendance;
        }

        public boolean isEligible() {
            return this.attendance >= 75;
        }

        public String getName() {
            return name;
        }

        public int getAttendance() {
            return attendance;
        }

        public static double classAverage(SrmStudent[] students) {
            if (students == null || students.length == 0) {
                return 0.0;
            }
            int sum = 0;
            for (int i = 0; i < students.length; i++) {
                sum += students[i].getAttendance();
            }
            return (double) sum / students.length;
        }
    }

    public static void main(String[] args) {
        SrmStudent[] students = new SrmStudent[] {
            new SrmStudent("Ravi", "REG01", 82),
            new SrmStudent("Anitha", "REG02", 68),
            new SrmStudent("Karthik", "REG03", 91),
            new SrmStudent("Meera", "REG04", 74),
            new SrmStudent("Suresh", "REG05", 60)
        };

        for (int i = 0; i < students.length; i++) {
            SrmStudent s = students[i];
            String status = s.isEligible() ? "Eligible" : "Detained";
            System.out.println(s.getName() + " " + s.getAttendance() + "% " + status);
        }

        double avg = SrmStudent.classAverage(students);
        System.out.printf("Class average: %.1f%%\n", avg);
    }
}