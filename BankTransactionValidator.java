public class BankTransactionValidator {
    public static String normalizeReference(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() >= 3) {
            return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
        }        
        return trimmed.toUpperCase();
    }
    public static String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }
        String bankCode = reference.substring(0, 3);
        String dateStr = reference.substring(3, 9); 
        String seqStr = reference.substring(9, 14);
        String formattedDate = dateStr.substring(0, 2) + "/" + dateStr.substring(2, 4) + "/" + dateStr.substring(4, 6);
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] DATE: ").append(formattedDate).append(" | SEQ: ").append(seqStr);
        return sb.toString();
    }
    public static void main(String[] args) {
        String raw1 = " hdf03022600042 ";
        String normalized1 = normalizeReference(raw1);
        System.out.println(validateAndFormat(normalized1));
        String raw2 = "12F03022600042";
        String normalized2 = normalizeReference(raw2);
        System.out.println(validateAndFormat(normalized2));
    }
}