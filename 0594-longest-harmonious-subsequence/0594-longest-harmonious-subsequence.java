class Solution {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int res = 0;
        int right = 0;

        while(right < nums.length) {
            int diff = nums[right] - nums[left];
            if(diff == 1) res = Math.max(res, right - left + 1);
            if(diff <= 1) right++;
            else left++; 
        }

        return res;
    }
}