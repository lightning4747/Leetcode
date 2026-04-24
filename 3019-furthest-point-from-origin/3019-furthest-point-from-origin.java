class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int l =0;
        int r = 0;
        int dash = 0;
        for(int i =0;i<moves.length();i++) {
            if(moves.charAt(i) == 'L') l++;
            if(moves.charAt(i) == 'R') r++;
            if(moves.charAt(i) == '_') dash++;
        }

        return Math.abs(l-r)+ dash;
    }
}