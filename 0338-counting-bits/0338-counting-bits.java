class Solution {
    public int[] countBits(int n) {
      int[] res = new int[n+1];

      for(int i =0;i<= n;i++) {
        int temp = i;
        int count = 0;
        while(temp !=0) {
            count+= temp%2;
            temp /=2;
        }
        res[i] = count;
      }  
    return res;
    }
}