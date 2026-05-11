class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        recursion(1,n,k,list, new ArrayList<>());
        return list;
    }

    private void recursion(int start,int n , int k , List<List<Integer>> list, List<Integer> res) {
        if(res.size() == k) {
            list.add(new ArrayList<>(res));
            return;
        }

        for(int i = start; i<= n;i++) {
            res.add(i);
            recursion(i+1,n ,k, list, res);
            res.remove(res.size()-1);
        }
    }
}