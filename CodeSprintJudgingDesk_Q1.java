import java.util.*;

abstract class Track_Q1 {
    private String name;

    public Track_Q1(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public abstract double calculateScore(double idea, double execution, double presentation);
}

class InnovationTrack_Q1 extends Track_Q1 {
    public InnovationTrack_Q1() {
        super("Innovation");
    }

    @Override
    public double calculateScore(double idea, double execution, double presentation) {
        return (idea * 0.5) + (execution * 0.3) + (presentation * 0.2);
    }
}

class OpenTrack_Q1 extends Track_Q1 {
    public OpenTrack_Q1() {
        super("Open");
    }

    @Override
    public double calculateScore(double idea, double execution, double presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}

class Student_Q1 {
    private String name;

    public Student_Q1(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Project_Q1 {
    private String title;
    private Double ideaScore;
    private Double executionScore;
    private Double presentationScore;
    private Double finalScore;

    public Project_Q1(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }

    public void setScores(double idea, double execution, double presentation, Track_Q1 track) {
        this.ideaScore = idea;
        this.executionScore = execution;
        this.presentationScore = presentation;
        this.finalScore = track.calculateScore(idea, execution, presentation);
    }

    public Double getFinalScore() { return finalScore; }
}

class Team_Q1 {
    private String name;
    private Track_Q1 track;
    private List<Student_Q1> members;
    private Project_Q1 project;

    public Team_Q1(String name, Track_Q1 track, List<Student_Q1> members) {
        if (members.size() < 2 || members.size() > 4) {
            throw new IllegalArgumentException("A team must have 2 to 4 members.");
        }
        this.name = name;
        this.track = track;
        this.members = members;
    }

    public String getName() { return name; }
    public Track_Q1 getTrack() { return track; }
    public List<Student_Q1> getMembers() { return members; }
    public Project_Q1 getProject() { return project; }

    public void submitProject(Project_Q1 project) {
        this.project = project;
    }
}

class Hackathon_Q1 {
    private String name;
    private Map<String, Team_Q1> teams = new HashMap<>();
    private Set<String> registeredStudents = new HashSet<>();
    private String state = "Open";

    public Hackathon_Q1(String name) {
        this.name = name;
    }

    public void registerTeam(String teamName, Track_Q1 track, List<Student_Q1> members) {
        if (members.size() < 2 || members.size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return;
        }
        for (Student_Q1 student : members) {
            if (registeredStudents.contains(student.getName())) {
                System.out.println("Registration failed: Student " + student.getName() + " already belongs to a team.");
                return;
            }
        }
        Team_Q1 team = new Team_Q1(teamName, track, members);
        teams.put(teamName, team);
        for (Student_Q1 student : members) {
            registeredStudents.add(student.getName());
        }
        System.out.printf("Team %s registered (%d members, %s track).%n", teamName, members.size(), track.getName());
    }

    public void submitProject(String teamName, String projectTitle) {
        Team_Q1 team = teams.get(teamName);
        if (team != null) {
            Project_Q1 project = new Project_Q1(projectTitle);
            team.submitProject(project);
            System.out.printf("Project '%s' submitted by %s.%n", projectTitle, teamName);
        }
    }

    public void scoreProject(String teamName, double idea, double execution, double presentation) {
        if ("Published".equals(state)) {
            System.out.println("Rescore rejected: Results have already been published.");
            return;
        }
        Team_Q1 team = teams.get(teamName);
        if (team != null && team.getProject() != null) {
            team.getProject().setScores(idea, execution, presentation, team.getTrack());
            System.out.printf("Score recorded for '%s'. Final score: %.2f.%n", team.getProject().getTitle(), team.getProject().getFinalScore());
        }
    }

    public void publishResults() {
        this.state = "Published";
        System.out.println("Results published.");
    }
}

public class CodeSprintJudgingDesk_Q1 {
    public static void main(String[] args) {
        Hackathon_Q1 hackathon = new Hackathon_Q1("Code Sprint");

        List<Student_Q1> members1 = Arrays.asList(
            new Student_Q1("Asha"),
            new Student_Q1("Ravi"),
            new Student_Q1("Neha")
        );
        hackathon.registerTeam("ByteBusters", new InnovationTrack_Q1(), members1);

        List<Student_Q1> members2 = Collections.singletonList(new Student_Q1("Kiran"));
        hackathon.registerTeam("SoloCoder", new OpenTrack_Q1(), members2);

        hackathon.submitProject("ByteBusters", "SmartAttend");
        hackathon.scoreProject("ByteBusters", 8, 7, 9);
        hackathon.publishResults();
        hackathon.scoreProject("ByteBusters", 10, 7, 9);
    }
}