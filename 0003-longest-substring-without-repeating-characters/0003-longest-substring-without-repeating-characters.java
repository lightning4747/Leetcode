class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        if (s.length() == 0) return 0;
        int max = 0;
        for(int right = 0;right<s.length();right++) {
            char ch = s.charAt(right);
            if(map.containsKey(ch)) {
                left = Math.max(map.get(ch)+1, left);
            }
            map.put(ch, right);
            max = Math.max(max, right - left+1);
        }
        return max;
    }
}