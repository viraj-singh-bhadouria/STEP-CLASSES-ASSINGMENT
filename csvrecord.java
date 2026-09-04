public class csvrecord {
    public static void parsestudentrecord(String csvline) {
        String[] line = csvline.split(","); 
        if (line.length != 3) { 
            System.out.println("invalid record");
        } else {
            System.out.println("Name: " + line[0] + " | Roll no: "  + line[1] + " | Dept: " + line[2]);
        }
    }

    public static void main(String[] args) {
        String txt = "Viraj, RA2511026010050, CSE ";
        String line1 = "Viraj, CSE";        
        parsestudentrecord(txt);
        parsestudentrecord(line1);
    }
}