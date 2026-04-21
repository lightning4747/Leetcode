class Solution {
    public int[] sortedSquares(int[] nums) {
        int i =0,j= nums.length-1;
        int[] res = new int[nums.length];
        for(int a = nums.length - 1;a >=0 ;a--) {
            int val =0;
            if(Math.abs(nums[i]) > Math.abs(nums[j])) {
                val = nums[i];
                i++;
            }
            else {
                val = nums[j];
                j--;
            }
            res[a] = val * val;
        }
        return res;
    }
}