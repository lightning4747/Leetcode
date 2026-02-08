class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        
        for(String str: strs) {
            int[] count = new int[26];
            for(char c : str.toCharArray())
            count[c - 'a']++;

            StringBuilder sb = new StringBuilder();
            for(int num : count) {
                sb.append(num).append("#");
            }
            String key = sb.toString();

            if(!res.containsKey(key)) {
                res.put(key, new ArrayList<>());
            }
            res.get(key).add(str);
        }  
        return new ArrayList<>(res.values()); 
    }
}