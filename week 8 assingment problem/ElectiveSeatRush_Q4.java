import java.util.*;

abstract class Student_Q4 {
    private String name;
    private int currentCredits;
    private int creditLimit;

    public Student_Q4(String name, int currentCredits, int creditLimit) {
        this.name = name;
        this.currentCredits = currentCredits;
        this.creditLimit = creditLimit;
    }

    public String getName() { return name; }
    public int getCurrentCredits() { return currentCredits; }
    public int getCreditLimit() { return creditLimit; }
    public abstract String getType();

    public boolean canAddCredits(int credits) {
        return (currentCredits + credits) <= creditLimit;
    }

    public void addCredits(int credits) {
        this.currentCredits += credits;
    }

    public void removeCredits(int credits) {
        this.currentCredits -= credits;
    }
}

class RegularStudent_Q4 extends Student_Q4 {
    public RegularStudent_Q4(String name, int currentCredits) {
        super(name, currentCredits, 24);
    }

    @Override
    public String getType() { return "Regular"; }
}

class HonorsStudent_Q4 extends Student_Q4 {
    public HonorsStudent_Q4(String name, int currentCredits) {
        super(name, currentCredits, 28);
    }

    @Override
    public String getType() { return "Honors"; }
}

class ExchangeStudent_Q4 extends Student_Q4 {
    public ExchangeStudent_Q4(String name, int currentCredits) {
        super(name, currentCredits, 20);
    }

    @Override
    public String getType() { return "Exchange"; }
}

class Elective_Q4 {
    private String name;
    private int credits;
    private int capacity;
    private List<Student_Q4> enrolledStudents = new ArrayList<>();
    private Queue<Student_Q4> waitlist = new LinkedList<>();

    public Elective_Q4(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    public String getName() { return name; }

    public void enrollStudent(Student_Q4 student) {
        if (!student.canAddCredits(credits)) {
            System.out.printf("Enrollment failed: %s would exceed the %s credit limit (%d/%d).%n",
                    student.getName(), student.getType(), student.getCurrentCredits() + credits, student.getCreditLimit());
            return;
        }

        if (enrolledStudents.contains(student) || waitlist.contains(student)) {
            System.out.printf("%s is already enrolled or waitlisted for %s.%n", student.getName(), name);
            return;
        }

        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(student);
            student.addCredits(credits);
            System.out.printf("%s enrolled in %s (credits: %d/%d).%n",
                    student.getName(), name, student.getCurrentCredits(), student.getCreditLimit());
        } else {
            waitlist.add(student);
            System.out.printf("%s is full. %s added to waitlist (position %d).%n", name, student.getName(), waitlist.size());
        }
    }

    public void dropStudent(Student_Q4 student) {
        if (enrolledStudents.remove(student)) {
            student.removeCredits(credits);
            System.out.printf("%s dropped %s (credits: %d/%d).%n",
                    student.getName(), name, student.getCurrentCredits(), student.getCreditLimit());

            promoteFromWaitlist();
        }
    }

    private void promoteFromWaitlist() {
        while (!waitlist.isEmpty()) {
            Student_Q4 candidate = waitlist.poll();
            if (candidate.canAddCredits(credits)) {
                enrolledStudents.add(candidate);
                candidate.addCredits(credits);
                System.out.printf("%s promoted from waitlist and enrolled in %s (credits: %d/%d).%n",
                        candidate.getName(), name, candidate.getCurrentCredits(), candidate.getCreditLimit());
                break;
            } else {
                System.out.printf("Promotion failed for %s: Credit limit exceeded.%n", candidate.getName());
            }
        }
    }
}

public class ElectiveSeatRush_Q4 {
    public static void main(String[] args) {
        Elective_Q4 elective = new Elective_Q4("Cloud Computing", 4, 2);

        Student_Q4 asha = new RegularStudent_Q4("Asha", 20);
        Student_Q4 ravi = new HonorsStudent_Q4("Ravi", 22);
        Student_Q4 neha = new ExchangeStudent_Q4("Neha", 12);
        Student_Q4 kiran = new RegularStudent_Q4("Kiran", 22);

        elective.enrollStudent(asha);
        elective.enrollStudent(ravi);
        elective.enrollStudent(neha);
        elective.enrollStudent(kiran);

        elective.dropStudent(asha);
    }
}
