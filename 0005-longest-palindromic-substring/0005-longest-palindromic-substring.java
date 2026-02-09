class Solution {
    public String longestPalindrome(String s) {
        String max = "";
        for(int i =0; i<s.length();i++){
            for(int j =i;j<s.length();j++) {
                String temp = s.substring(i, j+1);
                if(plindrome(temp)) {
                    if(max.length() < temp.length()) {
                        max = temp;
                    }
                } 
            }
        }
        return max;
    }
    private boolean plindrome(String s) {
        int r = s.length() -1;
        int l = 0;

        while (r>l) {
            if(s.charAt(r) != s.charAt(l)) return false;
            l++;r--;
        }
        return true;
    }
}