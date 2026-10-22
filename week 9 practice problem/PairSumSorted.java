
public class PairSumSorted {
    public static int[] pairSumSorted(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int currentSum = nums[left] + nums[right];
            if (currentSum == target) {
                return new int[]{nums[left], nums[right]};
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums1 = {-4, -1, 3, 5, 8, 9};
        int target1 = 4;
        int[] result1 = pairSumSorted(nums1, target1);
        if (result1 != null) {
            System.out.println("(" + result1[0] + ", " + result1[1] + ")");
        } else {
            System.out.println("Not Found");
        }

        int[] nums2 = {1, 2, 3};
        int target2 = 100;
        int[] result2 = pairSumSorted(nums2, target2);
        if (result2 != null) {
            System.out.println("(" + result2[0] + ", " + result2[1] + ")");
        } else {
            System.out.println("Not Found");
        }
    }
}
