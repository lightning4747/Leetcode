class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums);
        recursion(0, nums, list, res);
        return list;
    }

    private void recursion(int idx, int[] nums,List<List<Integer>> list, List<Integer> res) {
        list.add(new ArrayList<>(res));

        for(int i = idx;i<nums.length;i++) {
            if(i > idx && nums[i] == nums[i-1]) continue;
            res.add(nums[i]);
            recursion(i+1, nums,list,res);
            res.remove(res.size() - 1);
        }
    }
}