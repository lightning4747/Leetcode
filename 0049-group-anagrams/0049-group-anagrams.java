class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for(String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            if(!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
             map.get(key).add(str);
        }

        for(List<String> str : map.values()) {
            res.add(str);
        }
        return res;
    }
}