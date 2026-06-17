class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> res = new HashSet<>();
        Set<String> set = new HashSet<>();

        for(int i=0;i<s.length()-9;i++) {
            String str = s.substring(i,i+10);
            if(!set.add(str)) res.add(str);
        }

        return new ArrayList<String>(res);
    }
}