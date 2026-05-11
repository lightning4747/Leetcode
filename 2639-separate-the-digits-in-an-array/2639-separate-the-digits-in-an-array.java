class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> res = new ArrayList<>();

        for(int i = 0;i<nums.length;i++) {
            List<Integer> temp = new ArrayList<>();
            int num = nums[i];
            while(num > 0) {
                temp.add(num%10);
                num/=10;
            }

            for (int j = temp.size() - 1; j >= 0; j--) {
                 res.add(temp.get(j));
            }   
        }
        int[] arr = new int[res.size()];

        for(int i =0;i<res.size();i++) arr[i] = res.get(i);

        return arr;
    }
}