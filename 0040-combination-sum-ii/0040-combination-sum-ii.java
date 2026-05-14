class Solution {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        recursion(0, 0, nums, target, list, new ArrayList<>());
        return list;
    }

    private void recursion(int start, int sum,int[] nums, int target, List<List<Integer>> list, List<Integer> res) {
        if(sum > target) return;
        if(sum == target) {
            list.add(new ArrayList<>(res));
            return;
        }

        for(int i =start; i<nums.length;i++) {
            if( i > start && nums[i-1] == nums[i]) continue;
            if(nums[i] > target) break;

            res.add(nums[i]);

            recursion(i+1, sum+nums[i],nums, target, list, res);

            res.remove(res.size() -1);
        }
    }
}