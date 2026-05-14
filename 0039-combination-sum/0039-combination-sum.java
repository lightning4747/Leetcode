class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        recursion(0,0, nums, target, list, new ArrayList<>());
        return list;
    }

    private void recursion(int start,int sum, int[] nums, int target, List<List<Integer>> list, List<Integer> res) {
        if(sum > target) return;
        if(sum == target) list.add(new ArrayList<>(res));

        for(int i = start;i<nums.length;i++) {
            res.add(nums[i]);
            recursion(i, sum + nums[i], nums, target, list, res);
            res.remove(res.size() -1);
        }

    }
}