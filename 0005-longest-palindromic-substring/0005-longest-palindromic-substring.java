class Solution {
    public String longestPalindrome(String s) {
        String ans = "";
        for(int i =0; i<s.length(); i++) {
            String a = helper(i,i,s);
            String b = helper(i,i+1,s);
            if(a.length() > ans.length()) ans = a;
            if(b.length() > ans.length()) ans = b;
        } 
        return ans;
    }
    private String helper(int i , int j, String s) {
        String common = "";
        String ans = "";

        while(i>= 0 && j <s.length()) {
            if(s.charAt(i) == s.charAt(j)) {
                if(i == j) common = s.charAt(i)+"";
                else ans+= s.charAt(i);

                i--;
                j++;
            }
            else {
                break;
            }
        }
        String rev = "";
        for(int k =ans.length()-1; k>=0;k--) {
            rev += ans.charAt(k);
        }
        ans = rev + common + ans;
        return ans;
    }
}