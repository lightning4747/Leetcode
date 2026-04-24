class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] nums) {
        int min = Integer.MAX_VALUE;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for(int i =0;i<nums.length-1;i++) {
            min = Math.min(min, Math.abs(nums[i]- nums[i+1]));
        }

        for(int i =0;i<nums.length - 1;i++) {
            if(Math.abs(nums[i]- nums[i+1]) == min) res.add(new ArrayList<>(List.of(nums[i], nums[i+1])));

        }

        return res;
    }
}