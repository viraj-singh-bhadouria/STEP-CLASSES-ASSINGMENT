class LibraryMember {
    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    private LibraryMember() {
    }

    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (membershipId == null || membershipId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.membershipId = membershipId.trim();
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}
public class AccessChecker {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }
        if (accessorContext.equals("SAME_CLASS")) {
            return "ALLOWED";
        }
        if (fieldModifier.equals("private")) {
            return "DENIED";
        }
        if (accessorContext.equals("SAME_PACKAGE")) {
            return "ALLOWED";
        }
        return "DENIED";
    }

    public static String summarizeByModifier(String[][] attempts) {
        int privA = 0, privD = 0, defA = 0, defD = 0, protA = 0, protD = 0, pubA = 0, pubD = 0;
        
        for (String[] attempt : attempts) {
            String mod = attempt[0];
            String res = classifyAccess(mod, attempt[1]);
            
            if (mod.equals("private")) { 
                if (res.equals("ALLOWED")) privA++; else privD++; 
            } else if (mod.equals("default")) { 
                if (res.equals("ALLOWED")) defA++; else defD++; 
            } else if (mod.equals("protected")) { 
                if (res.equals("ALLOWED")) protA++; else protD++; 
            } else if (mod.equals("public")) { 
                if (res.equals("ALLOWED")) pubA++; else pubD++; 
            }
        }
        
        return String.format("private: %d allowed / %d denied | default: %d allowed / %d denied | protected: %d allowed / %d denied | public: %d allowed / %d denied", 
                             privA, privD, defA, defD, protA, protD, pubA, pubD);
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE"));
        
        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeByModifier(attempts));
        
        try {
            new LibraryMember("LB9", "BR1", 0, "Priya Nair");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

