public class fileextensionValidator {
    public static void fileExtension(String filename) {
        int i = filename.lastIndexOf('.');
        if (i > 0 && i < filename.length() - 1) {
            String txt = filename.substring(i + 1).trim().toLowerCase();
            if (txt.equals("pdf") || txt.equals("docx") || txt.equals("zip")) {
                System.out.println("Accepted");
            } else {
                System.out.println("Rejected - invalid file type");
            }
        } else {
            System.out.println("Rejected - invalid file type");
        }
    }
    public static void main(String[] args) {
        String line = "Assignment 1.PDF "; 
        String line1 = "notes.txt";        

        fileExtension(line);
        fileExtension(line1);
    }
}