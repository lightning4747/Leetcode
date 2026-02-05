class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int shift = nums[i];

            if (shift == 0) {
                res[i] = nums[i];
            } else {
                int newIndex = (i + shift) % n;
                if (newIndex < 0) newIndex += n; // fix negative wrap
                res[i] = nums[newIndex];
            }
        }
        return res;
    }
}
