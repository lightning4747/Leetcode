class Solution {
    public int longestBalanced(String s) {
        int max = 0;
        int unique = 0;
        int mx = 0;
        for(int i =0; i<s.length();i++) {
            int[] arr = new int[26];
            mx =0; unique = 0;
            for(int j = i ;j<s.length();j++) {
                int c = s.charAt(j) - 'a';
                arr[c]++;
                if(arr[c] == 1) unique++;
                mx = Math.max(mx, arr[c]);
                if(mx * unique == j - i +1) {
                    max = Math.max(max,mx * unique);
                }

            }
        }
        return max;
    }
}