public class PhoneNumber {
    public static void formatPhoneNumber(String phone) { 
        if (phone.length() != 10) {
            System.out.println("Invalid phone number");
        } else { 
            StringBuilder sb = new StringBuilder(phone);
            String mask = "xxxxx"; 
            sb.replace(0, 5, mask);
            sb.insert(5, "-");
            System.out.println(sb.toString());
        }
    }
    public static void main(String[] args) {
        String st = "1234567890";
        String st1 = "12345";        
        formatPhoneNumber(st);
        formatPhoneNumber(st1);
    }
}