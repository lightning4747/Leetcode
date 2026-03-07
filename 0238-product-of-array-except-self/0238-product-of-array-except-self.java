class Solution {
    public int[] productExceptSelf(int[] nums) {
        int left = 1;
        int right = 1;
        int n = nums.length;
        int[] arr = new int[n];
        arr[0] = 1;
        for(int i=1;i<nums.length;i++) {
            arr[i] = arr[i-1] * nums[i-1];
        }

        for(int j = nums.length-1;j>=0;j--) {
            arr[j] *= right;
            right *= nums[j];
        }
        return arr;
    }
}