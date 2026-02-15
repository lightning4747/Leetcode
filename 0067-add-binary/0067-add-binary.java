class Solution {
    public String addBinary(String a, String b) {
        int n1 = a.length()-1, n2 = b.length()-1;
        int carry = 0;
        int max = Math.max(n1, n2);

        StringBuilder sb = new StringBuilder();

        while(n1 >= 0 || n2>= 0 || carry > 0) {
            int A =  n1 >= 0  ?  a.charAt(n1) - '0' : 0;
            int B =  n2 >= 0 ?  b.charAt(n2) - '0' : 0;

            int currentsum = A + B + carry;
            sb.append(currentsum%2);
            carry = currentsum/2;

            n1--;n2--;

        }
        return sb.reverse().toString();
    }
}