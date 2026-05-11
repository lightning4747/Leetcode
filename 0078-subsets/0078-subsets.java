class Solution {
    public List<List<Integer>> subsets(int[] nums) {
       List<List<Integer>> list = new ArrayList<>();
       recursion(0, nums, list, new ArrayList<>());
       return list; 
    }

    private void recursion(int i, int[] arr, List<List<Integer>> list, List<Integer> res) {
        if(i >= arr.length) {
            list.add(new ArrayList<>(res));return;
        }

        recursion(i+1, arr, list, res);

        res.add(arr[i]);

        recursion(i+1, arr, list, res);

        res.remove(res.size() - 1);
    }
}