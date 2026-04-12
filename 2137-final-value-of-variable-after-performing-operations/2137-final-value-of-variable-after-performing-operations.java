class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int x = 0;

        for(String str : operations) {
            if(str.charAt(0) == '-' || str.charAt(2) =='-') x--;
            else x++;
        }
        return x;
    }
}