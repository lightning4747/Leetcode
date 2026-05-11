class Solution {
    public List<String> letterCasePermutation(String s) {
        StringBuilder str = new StringBuilder(s);
        List<String> list = new ArrayList<>();

        recursion(0, str, list);
        return list;
    }

    private void recursion(int idx, StringBuilder str, List<String> list) {
        if(idx >= str.length()){ list.add(str.toString()); return;}

        if(Character.isDigit(str.charAt(idx))) {
            recursion(idx+1, str, list);
            return;
        }

        char ch = str.charAt(idx);

        if(Character.isUpperCase(ch)) str.setCharAt(idx,Character.toLowerCase(ch));
        else str.setCharAt(idx,Character.toUpperCase(ch));

        recursion(idx+1,str, list);

        str.setCharAt(idx, ch);

        recursion(idx+1, str, list);
    }
}