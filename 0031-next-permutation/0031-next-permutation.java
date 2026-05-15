class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;

        while(i > 0 && nums[i - 1] >= nums[i]) i--;

        if(i == 0) {
            reverse(nums,i);
            return;
        }
        int j = nums.length-1;
        while( j >= i && nums[j] <= nums[i-1]) {
            j--;
        }

        swap(i-1,j,nums);
        reverse(nums,i);
    }

    void swap(int i , int j , int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    void reverse(int[] nums, int i) {
        int j = nums.length - 1;

        while(i < j) {
            swap(i, j , nums);
            i++;j--;
        }
    }
}