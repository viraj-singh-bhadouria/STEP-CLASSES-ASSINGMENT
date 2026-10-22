import java.util.ArrayList;
import java.util.List;

public class BrowserHistory {
    public static class Node {
        public String url;
        public Node prev;
        public Node next;

        public Node(String url) {
            this.url = url;
            this.prev = null;
            this.next = null;
        }
    }

    private Node current;

    public BrowserHistory(String homepage) {
        this.current = new Node(homepage);
    }

    public void visit(String url) {
        Node newNode = new Node(url);
        current.next = newNode;
        newNode.prev = current;
        current = newNode;
    }

    public String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }
        return current.url;
    }

    public String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }
        return current.url;
    }

    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory("srm.edu");
        browser.visit("a.com");
        browser.visit("b.com");
        browser.visit("c.com");

        List<String> output = new ArrayList<>();
        output.add(browser.back(1));
        output.add(browser.back(1));
        output.add(browser.forward(1));

        browser.visit("d.com");

        output.add(browser.forward(2));
        output.add(browser.back(2));
        output.add(browser.back(7));

        System.out.println(String.join(", ", output));
    }
}
