import java.util.HashMap;
import java.util.Map;

public class NetBalancePeriodCounter {
    public static int countPeriods(int[] transactions, long k) {
        Map<Long, Integer> prefixSumCounts = new HashMap<>();
        prefixSumCounts.put(0L, 1);

        long currentSum = 0;
        int count = 0;

        for (int txn : transactions) {
            currentSum += txn;

            if (prefixSumCounts.containsKey(currentSum - k)) {
                count += prefixSumCounts.get(currentSum - k);
            }

            prefixSumCounts.put(currentSum, prefixSumCounts.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] transactions1 = {3, 4, -7, 1, 3, 3, 1, -4};
        int k1 = 7;
        System.out.println(countPeriods(transactions1, k1));

        int[] transactions2 = {1, 2, 3};
        int k2 = 10;
        System.out.println(countPeriods(transactions2, k2));
    }
}