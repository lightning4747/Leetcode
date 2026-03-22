class Solution {
    public boolean isAnagram(String s, String t) {
        int ssum = 0;
        int tsum = 0;
        if(s.length() != t.length()) return false;

        for(int i =0;i<s.length();i++) {
            char chs = s.charAt(i);
            char cht = t.charAt(i);

            ssum+= (int) chs;
            tsum+= (int) cht;
        }
        if(ssum == tsum ) return true;
        return false;
    }
}