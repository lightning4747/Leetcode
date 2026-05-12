class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        recursion(0, nums.length, nums, list);
        return list;
    }

    private void recursion(int start, int end, int[] nums, List<List<Integer>> list) {
        if(start == end) {
            List<Integer> currentPermutation = new ArrayList<>();
            for (int num : nums) currentPermutation.add(num);
            list.add(currentPermutation);
            return;
        }

        for(int i = start; i<end;i++) {
            int temp = nums[start];
            nums[start] = nums[i];
            nums[i] = temp;

            recursion(start+1, end, nums, list);

            temp = nums[start];
            nums[start] = nums[i];
            nums[i] = temp;
        }
    }
}