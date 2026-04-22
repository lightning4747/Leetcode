class Solution {
    public int longestMountain(int[] arr) {
        int max = 0;

        for(int i = 1;i<arr.length-1;i++) {
            if(arr[i] > arr[i-1] && arr[i] > arr[i+1]) {
                int count = 1;

                int j = i;
                while(j > 0 && arr[j] > arr[j-1]) {
                    count++;j--;
                }

                int k = i;
                while(k < arr.length-1 && arr[k] > arr[k+1]) {
                    count++;k++;
                }

                max = Math.max(max, count);
            }
        }

        return max;
    }
}