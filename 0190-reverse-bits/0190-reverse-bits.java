class Solution {
    public int reverseBits(int n) {
        int res = n;

        for(int i=0; i<32;i++) {
            res = (res << 1) | n&1;
            n >>=1;
        }
        return res;
    }
}