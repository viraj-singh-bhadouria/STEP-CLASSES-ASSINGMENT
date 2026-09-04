public class LibraryFineSystem {

    public static class BookIssue {
        private String title;
        private String borrowerName;
        private int daysOverdue;

        public BookIssue(String title, String borrowerName, int daysOverdue) {
            this.title = title;
            this.borrowerName = borrowerName;
            this.daysOverdue = daysOverdue;
        }

        public double fineAmount() {
            if (daysOverdue > 0) {
                return daysOverdue * 5.0;
            }
            return 0.0;
        }

        public boolean isSeverelyOverdue() {
            return daysOverdue > 14;
        }

        public String getTitle() {
            return title;
        }

        public int getDaysOverdue() {
            return daysOverdue;
        }

        public static double totalFineCollected(BookIssue[] issues) {
            if (issues == null || issues.length == 0) {
                return 0.0;
            }
            double total = 0.0;
            for (int i = 0; i < issues.length; i++) {
                if (issues[i] != null) {
                    total += issues[i].fineAmount();
                }
            }
            return total;
        }
    }

    public static void main(String[] args) {
        BookIssue[] issues = new BookIssue[] {
            new BookIssue("Clean Code", "User1", 18),
            new BookIssue("Effective Java", "User2", 5),
            new BookIssue("Refactoring", "User3", 0),
            new BookIssue("DSA Handbook", "User4", 21),
            new BookIssue("Design Patterns", "User5", 9)
        };

        for (int i = 0; i < issues.length; i++) {
            BookIssue book = issues[i];
            String status = book.isSeverelyOverdue() ? "Severely overdue" : "OK";
            System.out.println(book.getTitle() + " " + book.getDaysOverdue() + " days - " + status);
        }

        double totalFine = BookIssue.totalFineCollected(issues);
        System.out.println("Total fine collected: Rs " + totalFine);
    }
}
