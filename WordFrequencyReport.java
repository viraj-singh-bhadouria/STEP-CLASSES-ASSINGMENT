public class WordFrequencyReport {

    public static void printFilteredWordFrequency(String feedback) {
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        String cleaned = feedback.toLowerCase().replace(".", "").replace(",", "");

        String[] words = cleaned.split("\\s+");

        String[] uniqueWords = new String[words.length];
        int[] counts = new int[words.length];
        int uniqueCount = 0;

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            if (word.isEmpty()) {
                continue;
            }
            boolean isStopWord = false;
            for (int j = 0; j < stopWords.length; j++) {
                if (word.equals(stopWords[j])) {
                    isStopWord = true;
                    break;
                }
            }

            if (isStopWord) {
                continue;
            }

            int foundIndex = -1;
            for (int j = 0; j < uniqueCount; j++) {
                if (uniqueWords[j].equals(word)) {
                    foundIndex = j;
                    break;
                }
            }

            if (foundIndex != -1) {
                counts[foundIndex]++;
            } else {
                uniqueWords[uniqueCount] = word;
                counts[uniqueCount] = 1;
                uniqueCount++;
            }
        }
        for (int i = 0; i < uniqueCount - 1; i++) {
            for (int j = 0; j < uniqueCount - 1 - i; j++) {
                if (counts[j] < counts[j + 1]) {
                    int tempCount = counts[j];
                    counts[j] = counts[j + 1];
                    counts[j + 1] = tempCount;
                    String tempWord = uniqueWords[j];
                    uniqueWords[j] = uniqueWords[j + 1];
                    uniqueWords[j + 1] = tempWord;
                }
            }
        }
        for (int i = 0; i < uniqueCount; i++) {
            System.out.println(uniqueWords[i] + ": " + counts[i]);
        }
    }

    public static void main(String[] args) {
        String sampleFeedback = "The mentor was great, the session was great and clear.";
        printFilteredWordFrequency(sampleFeedback);
    }
}