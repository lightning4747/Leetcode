import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        // Create a map to store the list of indices for each value
        Map<Integer, List<Integer>> valueIndices = new HashMap<>();

        // Populate the map with indices for each value
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!valueIndices.containsKey(num)) {
                valueIndices.put(num, new ArrayList<>());
            }
            valueIndices.get(num).add(i);
        }

        int minDistance = Integer.MAX_VALUE;

        // Iterate over each value's indices
        for (List<Integer> indices : valueIndices.values()) {
            int n = indices.size();
            // If a value appears at least 3 times, check all possible triplets
            if (n >= 3) {
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {
                        for (int k = j + 1; k < n; k++) {
                            int iIdx = indices.get(i);
                            int jIdx = indices.get(j);
                            int kIdx = indices.get(k);
                            int distance = Math.abs(iIdx - jIdx) + Math.abs(jIdx - kIdx) + Math.abs(kIdx - iIdx);
                            if (distance < minDistance) {
                                minDistance = distance;
                            }
                        }
                    }
                }
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}