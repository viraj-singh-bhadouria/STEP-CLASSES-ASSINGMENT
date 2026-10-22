public class ExamScoreBandCounter {
    private static int findLowerBound(int[] scores, int target) {
        int low = 0;
        int high = scores.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (scores[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static int findUpperBound(int[] scores, int target) {
        int low = 0;
        int high = scores.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (scores[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static int countInBand(int[] scores, int low, int high) {
        int lowerIdx = findLowerBound(scores, low);
        int upperIdx = findUpperBound(scores, high);
        return upperIdx - lowerIdx;
    }

    public static void main(String[] args) {
        int[] scores1 = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};
        System.out.println(countInBand(scores1, 42, 58));

        int[] scores2 = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};
        System.out.println(countInBand(scores2, 90, 100));
    }
}
