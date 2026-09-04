public class FirstNonRepeatingChar {
    public static char findFirstNonRepeatingChar(String text) {
        int[] frequency = new int[256];

        for (int i = 0; i < text.length(); i++) {
            frequency[text.charAt(i)]++;
        }
        for (int i = 0; i < text.length(); i++) {
            if (frequency[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }
        return '\0';
    }
    public static void processString(String text) {
        char result = findFirstNonRepeatingChar(text);
        if (result != '\0') {
            System.out.println("Input: \"" + text + "\" -> First Non-Repeating Character: '" + result + "'");
        } else {
            System.out.println("Input: \"" + text + "\" -> No Non-Repeating Character Found");
        }
    }
    public static void firstNonRepeatingCharMain(String[] args) {
        processString("swiss");
        processString("aabbcc");
    }
    public static void main(String[] args) {
        firstNonRepeatingCharMain(args);
    }
}
