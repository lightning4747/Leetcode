class Solution {
    public boolean isGood(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length -1;
        if(nums[n]+1 != nums.length) return false;
        
        for(int i =1;i<nums.length;i++) {
            if(i != nums[i-1]){ System.out.println(i + " " + nums[i]); return false;}
        }

        if(nums[n-1] != nums[n]) return false;

        return true;
    }
}