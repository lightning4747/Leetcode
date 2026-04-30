class Solution {
    public int minSubArrayLen(int target, int[] nums) {
         int sum =0 ;
         int min = Integer.MAX_VALUE;
         int count = 0;
         int i =0;

         for(int j =0 ;j<nums.length;j++) {
            sum+= nums[j];

            while(sum >= target) {
                min = Math.min(min, j - i+1);
                sum-=nums[i];
                i++;
            }
         }
         if(min == Integer.MAX_VALUE) return 0;

         return min;
    }
}