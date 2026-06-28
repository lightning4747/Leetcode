class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] nums) {
        Arrays.sort(nums);
        nums[0] = 1;
        for(int i =1;i<nums.length;i++) {
            nums[i] = Math.min(nums[i], nums[i-1] + 1);
        }
        return nums[nums.length - 1];
    }
}