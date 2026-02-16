class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int index=0;
        for(int i =0; i<height.length;i++) {
            if(height[i] > height[index]) index = i;
            int area = (i - index) * height[i];
            max = Math.max(max, area);
        }
        return max;
    }
}