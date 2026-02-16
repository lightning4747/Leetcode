class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
     List<List<Integer>> res = new ArrayList<>();
     Arrays.sort(nums);
     for(int i =0;i<nums.length;i++) {
        if(i > 0 && nums[i-1] == nums[i]) continue;
        int j = i+1;
        int k = nums.length - 1;

        while(j<k) {
            int total = nums[k] + nums[j] + nums[i];

            if(total > 0) k--;
            else if (total < 0) j++;
            else{ res.add(Arrays.asList(nums[i], nums[j], nums[k])); j++;k--;

            while(nums[j] == nums[j-1] && j < k) {
                j++;
            }
            while(j < k && nums[k] == nums[k+1]) {
                        k--;
                    }
            }

        }
     }   
     return res;
    }
}