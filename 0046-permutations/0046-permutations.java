class Solution {
    public List<List<Integer>> permute(int[] nums) {
       List<List<Integer>> list = new ArrayList<>();
       recursion(0, nums, list);
       return list; 
    }

    private void recursion(int start, int[] nums, List<List<Integer>> list) {
        if(start == nums.length) {
            List<Integer> res = new ArrayList<>();
            for(int num : nums) res.add(num);
            list.add(new ArrayList<>(res));
            return;
        }

        for(int i = start;i<nums.length;i++) {
            int temp = nums[i];
            nums[i] = nums[start];
            nums[start] = temp;

            recursion(start+1, nums, list);

            nums[start] = nums[i];
            nums[i] = temp;
        }
    }
}