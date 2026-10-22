import java.util.*;

abstract class Question_Q1 {
    protected int questionId;
    protected String prompt;
    protected String correctAnswer;

    public Question_Q1(int questionId, String prompt, String correctAnswer) {
        this.questionId = questionId;
        this.prompt = prompt;
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionId() { return questionId; }
    public abstract boolean evaluateAnswer(String studentAnswer);
}

class MultipleChoiceQuestion_Q1 extends Question_Q1 {
    public MultipleChoiceQuestion_Q1(int questionId, String prompt, String correctAnswer) {
        super(questionId, prompt, correctAnswer);
    }

    @Override
    public boolean evaluateAnswer(String studentAnswer) {
        return this.correctAnswer.trim().equalsIgnoreCase(studentAnswer.trim());
    }
}

class TrueFalseQuestion_Q1 extends Question_Q1 {
    public TrueFalseQuestion_Q1(int questionId, String prompt, String correctAnswer) {
        super(questionId, prompt, correctAnswer);
    }

    @Override
    public boolean evaluateAnswer(String studentAnswer) {
        return this.correctAnswer.trim().equalsIgnoreCase(studentAnswer.trim());
    }
}

class Student_Q1 {
    private String studentId;
    private String name;

    public Student_Q1(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getName() { return name; }
}

class Examination_Q1 {
    private String title;
    private List<Question_Q1> questions;

    public Examination_Q1(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question_Q1 question) {
        questions.add(question);
    }

    public String getTitle() { return title; }
    public List<Question_Q1> getQuestions() { return questions; }
}

class Attempt_Q1 {
    private Student_Q1 student;
    private Examination_Q1 examination;
    private Map<Integer, String> answers;
    private String state;

    public Attempt_Q1(Student_Q1 student, Examination_Q1 examination) {
        this.student = student;
        this.examination = examination;
        this.answers = new HashMap<>();
        this.state = "IN_PROGRESS";
        System.out.println("Examination '" + examination.getTitle() + "' started by Student.");
    }

    public void answerQuestion(int questionId, String answer) {
        if ("SUBMITTED".equals(this.state)) {
            throw new IllegalStateException("Cannot alter answers for a submitted examination.");
        }
        answers.put(questionId, answer);
        System.out.println("Question " + questionId + " answered with '" + answer + "'.");
    }

    public String submit() {
        if ("SUBMITTED".equals(this.state)) {
            return "Attempt already submitted.";
        }
        this.state = "SUBMITTED";
        int correctCount = 0;
        List<Question_Q1> questions = examination.getQuestions();

        for (Question_Q1 q : questions) {
            String studentAns = answers.getOrDefault(q.getQuestionId(), "");
            if (q.evaluateAnswer(studentAns)) {
                correctCount++;
            }
        }

        System.out.println("Examination '" + examination.getTitle() + "' submitted successfully.");
        return "Result for '" + examination.getTitle() + "' attempt: " + correctCount + "/" + questions.size() + " correct";
    }
}

public class OnlineExamSystem_Q1 {
    public static void main(String[] args) {
        Student_Q1 student = new Student_Q1("S101", "Student");
        Examination_Q1 exam = new Examination_Q1("Math Quiz");

        exam.addQuestion(new MultipleChoiceQuestion_Q1(1, "Question 1", "A"));
        exam.addQuestion(new MultipleChoiceQuestion_Q1(2, "Question 2", "B"));

        Attempt_Q1 attempt = new Attempt_Q1(student, exam);
        attempt.answerQuestion(1, "A");
        attempt.answerQuestion(2, "C");

        String result = attempt.submit();
        System.out.println(result);
    }
}