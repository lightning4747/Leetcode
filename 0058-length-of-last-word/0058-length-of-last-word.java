class Solution {
    public int lengthOfLastWord(String s) {
        int pointer = s.length()-1;
        
        while(pointer >= 0 && s.charAt(pointer) == ' ') pointer--;

        int count = 0;

        while(pointer >= 0 && s.charAt(pointer) != ' '){ pointer--; count++;}

        return count;
    }
}