class Solution {
    public int rotatedDigits(int n) {
        int count = 0;
        boolean valid = false;
        boolean changed = false;

        for(int i =1 ;i<=n;i++) {
            valid = true;
            changed = false;
            int temp = i;
            while(temp != 0 && valid) {
                int digit = temp%10;

                if(digit == 3 || digit == 4 || digit == 7) valid = false;

                if(digit == 2 || digit == 5 || digit == 6 || digit == 9) changed = true;

                temp/=10;
            }

            if( valid == true && changed == true) count++;
        }
        return count;
    }
}