class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] answer = new int[n];
        int a =0;

        for(int i =0;i<n;i++) {
            if (i < 1) left[0] = 0; 
            else left[i] = left[i-1] + nums[i-1];
        }

        for(int i =n-1;i>=0;i--) {
            if(i == n-1) right[i] = 0;
            else right[i] = right[i+1] + nums[i+1];
        }

        for(int i =0;i<n;i++) {
            answer[i] = Math.abs(left[i] - right[i]);
        }

        return answer;
    }
}