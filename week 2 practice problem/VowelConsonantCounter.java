public class VowelConsonantCounter {
    public static void countVowelsAndConsonants(String text) {
        int vowels = 0;
        int consonants = 0;
        String lowerCaseText = text.toLowerCase();
        for (int i = 0; i < lowerCaseText.length(); i++) {
            char ch = lowerCaseText.charAt(i);
            if (ch == ' ') {
                continue;
            }
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowels++;
            } 
            else if (ch >= 'a' && ch <= 'z') {
                consonants++;
            }
        }
        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }
    public static void main(String[] args) {
        countVowelsAndConsonants("Java Programming");
    }
}