class Solution {
    public int maxArea(int[] height) {
        int i = 0,j = height.length - 1;
        int min = 0;
        int max = 0;
        while(i<j) {
            min = Math.min(height[i], height[j]);
            int area = (j - i) * min;
            max = Math.max(max, area);
           if(height[i] < height[j]) i++;
           else j--;
        }
        return max;
    }
}