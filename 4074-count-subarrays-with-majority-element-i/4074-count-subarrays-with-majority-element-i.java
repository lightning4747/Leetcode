class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        int[] pre = new int[2 * n + 1];

        pre[n] = 1;

        int cnt = n;
        int presum = 0;
        int ans = 0;

        for (int x : nums) {
            if (x == target) {
                presum += pre[cnt];

                cnt++;
                pre[cnt]++;
            } else {
                cnt--;

                presum -= pre[cnt];
                pre[cnt]++;
            }

            ans += presum;
        }

        return ans;
    }
}