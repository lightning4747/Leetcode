class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int j = 0; int k = nums.length - 1;
        for(int i =0 ;i<nums.length;i++) {
            if(i>0 && nums[i] == nums[i-1]) continue;
                j = i+1;
                k = nums.length - 1;

                while(j<k) {
                    int total = nums[i] + nums[j] + nums[k];
                    if(total > 0) k--;
                    else if(total < 0) j++;
                    else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        j++;k--;
                        while(j<k && nums[j] == nums[j-1]) j++;
                        while(k>=0 && nums[k] == nums[k+1]) k--;
                    }
                }
        }

        return res;
    }
}