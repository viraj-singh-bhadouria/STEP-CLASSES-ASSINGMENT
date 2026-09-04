public class LibraryMemberDesign {

    public static class BrokenLibraryMember {
        public static String name;
        public static String memberId;
        public static int booksIssued;

        public BrokenLibraryMember(String n, String m, int b) {
            name = n;
            memberId = m;
            booksIssued = b;
        }
    }

    public static class FixedLibraryMember {
        private String name;
        private String memberId;
        private int booksIssued;

        public static String libraryName = "Central Library";
        public static int memberCount = 1000;

        public FixedLibraryMember(String name, int booksIssued) {
            this.name = name;
            this.booksIssued = booksIssued;
            memberCount++;
            this.memberId = "LM-" + memberCount;
        }

        public void printMemberCard() {
            System.out.println(name + " " + memberId);
        }

        public static void printTotalMembers() {
            int countSoFar = memberCount - 1000;
            System.out.println("Total members: " + countSoFar);
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenLibraryMember b1 = new BrokenLibraryMember("Aditi", "LM-1001", 2);
        BrokenLibraryMember b2 = new BrokenLibraryMember("Rohan", "LM-1002", 1);
        System.out.println(BrokenLibraryMember.name);
        System.out.println(BrokenLibraryMember.name);

        System.out.println("\nFixed version:");
        FixedLibraryMember f1 = new FixedLibraryMember("Aditi", 2);
        FixedLibraryMember f2 = new FixedLibraryMember("Rohan", 1);

        f1.printMemberCard();
        f2.printMemberCard();
        FixedLibraryMember.printTotalMembers();
    }
}