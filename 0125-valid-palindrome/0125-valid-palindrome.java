class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        StringBuilder res = new StringBuilder();
        int l =0;
        int h = 0;

        for(int i =0;i<s.length();i++) {
            if(Character.isLetterOrDigit(s.charAt(i))) {
                res.append(s.charAt(i));
            }
        }

        h = res.length() - 1;

        while(l<h) {
            if(res.charAt(l) != res.charAt(h)) return false;
            l++;h--;
        }
        return true;
    }
}