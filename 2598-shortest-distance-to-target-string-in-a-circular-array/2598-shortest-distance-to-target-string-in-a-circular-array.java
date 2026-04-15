class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int res = 0, f = 0, b = 0;
        int n = words.length;
        boolean flag = false;
        int len = startIndex+words.length;
        for(int i =0;i<words.length;i++) {
            if(target.equals(words[i])) flag = true;
        }
        if(!flag) return -1;

        for(int i= startIndex; i<len;i++) {
            int index = i % n;
            if(target.equals(words[index])){
                break;
            }
            f++;
        }

        for(int i= len; i>=0;i--) {
            int index = i % n;
            if(target.equals(words[index])){
                break;
            }
            b++;
        }
        System.out.print(f + " " +b);
        return Math.min(f,b);
    }
}