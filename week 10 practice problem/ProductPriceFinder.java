import java.util.ArrayList;
import java.util.List;

public class ProductPriceFinder {

    private static int findLowerBound(int[] prices, int target) {
        int low = 0, high = prices.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (prices[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static int findUpperBound(int[] prices, int target) {
        int low = 0, high = prices.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (prices[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low - 1;
    }

    public static void countProductsInRange(int[] prices, int lowerRange, int upperRange) {
        int lowerIndex = findLowerBound(prices, lowerRange);
        int upperIndex = findUpperBound(prices, upperRange);

        System.out.println("Lower bound index " + lowerIndex);
        System.out.println("Upper bound index " + upperIndex);

        if (lowerIndex <= upperIndex && lowerIndex < prices.length && upperIndex >= 0) {
            int count = upperIndex - lowerIndex + 1;
            List<String> matched = new ArrayList<>();
            for (int i = lowerIndex; i <= upperIndex; i++) {
                matched.add(String.valueOf(prices[i]));
            }
            System.out.println("Count " + count + " (products with prices " + String.join(", ", matched) + ")");
        } else {
            System.out.println("Count 0");
        }
    }

    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};
        countProductsInRange(prices, 150, 300);
    }
}