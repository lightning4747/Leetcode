class Solution {
    public int totalWaviness(int num1, int num2) {
        int count = 0;

        for(int j = num1; j<= num2 ;j++) {
            int temp = j;
            List<Integer> list = new ArrayList<>();

            while(temp != 0) {
                list.add(temp%10);
                temp/=10;
            }

            for(int i = 1; i<list.size()-1;i++) {
                boolean peak = list.get(i) > list.get(i-1) && list.get(i) > list.get(i+1);
                boolean valley = list.get(i) < list.get(i-1) && list.get(i) < list.get(i+1);

                if(peak || valley) count++;
            }
        }
        return count;
    }
}