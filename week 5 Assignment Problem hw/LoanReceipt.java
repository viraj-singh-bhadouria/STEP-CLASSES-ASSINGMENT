public class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    static {
    }

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        
        if (bookIds == null) {
            throw new IllegalArgumentException("construction rejected");
        }
        
        String[] copiedIds = new String[bookIds.length];
        for (int i = 0; i < bookIds.length; i++) {
            if (bookIds[i] == null || !bookIds[i].matches("BK-\\d{3}")) {
                throw new IllegalArgumentException("construction rejected");
            }
            copiedIds[i] = bookIds[i];
        }
        this.bookIds = copiedIds;
    }

    public String[] getBookIds() {
        String[] copiedIds = new String[this.bookIds.length];
        System.arraycopy(this.bookIds, 0, copiedIds, 0, this.bookIds.length);
        return copiedIds;
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] newIds = this.getBookIds();
        newIds[index] = newId;
        if (this instanceof ReferenceOnlyLoanReceipt) {
            return new ReferenceOnlyLoanReceipt(this.memberId, newIds, ((ReferenceOnlyLoanReceipt)this).getRoomNumber());
        }
        return new LoanReceipt(this.memberId, newIds);
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        if (receipts == null) return "";
        
        int processed = 0, nullSkipped = 0, referenceOnly = 0, regular = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
            } else {
                processed++;
                if (receipt instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnly++;
                } else {
                    regular++;
                }
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + referenceOnly + " reference-only | " + regular + " regular";
    }

    public static void main(String[] args) {
        try {
            new LoanReceipt("LIB-8841", new String[]{"BK-100", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println(r.getBookIds()[0]);

        LoanReceipt[] receipts = {
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println(processNightlyCirculation(receipts));
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
    
    public String getRoomNumber() {
        return this.roomNumber;
    }
}