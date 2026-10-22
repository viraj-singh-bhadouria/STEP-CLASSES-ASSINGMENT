import java.util.*;

public class StudentMarksGrid {
    public static void main(String[] args) {
        String[] subjects = {"Math", "Sci", "Eng"};

        Map<String, Integer[]> studentMarks = new LinkedHashMap<>();
        studentMarks.put("Anu", new Integer[]{80, 90, 70});
        studentMarks.put("Ravi", new Integer[]{60, 85, null});
        studentMarks.put("Meena", new Integer[]{95, 75, 88});

        List<String> totals = new ArrayList<>();
        for (Map.Entry<String, Integer[]> entry : studentMarks.entrySet()) {
            int sum = 0;
            for (Integer mark : entry.getValue()) {
                if (mark != null) {
                    sum += mark;
                }
            }
            totals.add(entry.getKey() + " " + sum);
        }

        System.out.println("Totals " + String.join(", ", totals));

        List<String> toppers = new ArrayList<>();
        for (int j = 0; j < subjects.length; j++) {
            String topperName = "";
            int maxMark = -1;

            for (Map.Entry<String, Integer[]> entry : studentMarks.entrySet()) {
                Integer mark = entry.getValue()[j];
                if (mark != null && mark > maxMark) {
                    maxMark = mark;
                    topperName = entry.getKey();
                }
            }

            toppers.add(subjects[j] + " " + topperName + " " + maxMark);
        }

        System.out.println("toppers " + String.join(", ", toppers));
    }
}
