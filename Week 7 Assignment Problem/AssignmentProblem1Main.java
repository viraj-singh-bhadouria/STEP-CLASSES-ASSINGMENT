abstract class Shape {
    private static int counter = 1001;
    private final String shapeId;

    public Shape() {
        this.shapeId = "SHP-" + (counter++);
    }

    public abstract double calculateArea();
    public abstract void scale(double factor);
    public abstract void scale(double xFactor, double yFactor);

    public String getShapeId() {
        return shapeId;
    }
}

class CircleShape extends Shape {
    private double radius;

    public CircleShape(double radius) {
        super();
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double factor) {
        this.radius *= factor;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        scale(xFactor);
    }
}

class SquareShape extends Shape {
    private double side;

    public SquareShape(double side) {
        super();
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double factor) {
        this.side *= factor;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        scale(xFactor);
    }
}

public class AssignmentProblem1Main {
    public static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }

    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        System.out.printf("%.2f\n", c.calculateArea());

        SquareShape sq = new SquareShape(4.0);
        System.out.println(sq.calculateArea());

        sq.scale(2.0);
        System.out.println(sq.calculateArea());

        printArea(c);
    }
}
