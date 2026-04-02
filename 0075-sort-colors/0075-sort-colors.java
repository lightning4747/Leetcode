class Solution {
    public void sortColors(int[] nums) {
        int red=0, green =0, blue = 0;
        for(int i=0;i<nums.length;i++) {
            if(nums[i]==0) {
                red++;
            }
            else if(nums[i]==1) {
                green++;
            }
            else {
                blue++;
            }
        }
        int i =0;
        while(red>i) nums[i++] = 0;
        while(red + green > i) nums[i++] = 1;
        while(red + green + blue > i) nums[i++] = 2;
        
    }
}