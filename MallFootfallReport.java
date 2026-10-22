import java.util.Arrays;

public class MallFootfallReport {
    public static int[] footfallReport(int[] visitors, int[][] queries) {
        int n = visitors.length;
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + visitors[i];
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            result[i] = (int) (prefixSum[end + 1] - prefixSum[start]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] visitors = {12, 7, 3, 9, 15, 4, 8};
        int[][] queries = {{0, 2}, {2, 5}, {4, 6}, {3, 3}};
        
        int[] result = footfallReport(visitors, queries);
        System.out.println(Arrays.toString(result));
    }
}