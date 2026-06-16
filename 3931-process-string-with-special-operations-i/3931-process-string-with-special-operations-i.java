class Solution {
    public String processStr(String s) {
        StringBuilder res = new StringBuilder();

        for(int i =0;i<s.length();i++) {
            if(s.charAt(i) == '*') {
                if(res.length() > 0) 
                res = star(res);
                continue;
            }
            if(s.charAt(i) == '#') {
                res = hash(res);
                continue;
            }
            if(s.charAt(i) == '%') {
                res = module(res);
                continue;
            }
            res.append(s.charAt(i));
        }
        return res.toString();
    }

    private StringBuilder star(StringBuilder str) {
        String res = str.substring(0, str.length()-1);
        return new StringBuilder(res);
    }

    private StringBuilder hash(StringBuilder str) {
        return str.append(str);
    }

    private StringBuilder module(StringBuilder str) {
        return str.reverse();
    }
}