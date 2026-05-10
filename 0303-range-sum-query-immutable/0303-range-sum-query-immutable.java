class NumArray {
    int[] nums;
    public NumArray(int[] nums) {
        this.nums = new int[nums.length];
        int a =0;
        for(int num : nums) {
            this.nums[a++] = num; 
        }
    }
    
    public int sumRange(int left, int right) {
        int sum = nums[left];
        left++;
        for(int i = left;i<= right;i++) {
            sum+= nums[i];
        }
        return sum;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */