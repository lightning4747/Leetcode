class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n+1];
        for(int i=0; i<=n;i++) {
            res[i] = getcount(i);
        }

        return res;
    }

    public int getcount(int n) {
        int count = 0;

        while(n!=0) {
            count += n%2;
            n/=2;
        }
        return count;
    }
}