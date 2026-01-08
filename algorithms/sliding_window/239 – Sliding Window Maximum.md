# LeetCode 239 – Sliding Window Maximum

**Difficulty:** Hard  

**LeetCode ID:** 239  

**Link:** https://leetcode.com/problems/sliding-window-maximum/  

**Topics / Tags:**  
`Array`, `Sliding Window`, `Deque`, `Monotonic Queue`

---

## Problem Statement

You are given an integer array `nums` and an integer `k`.

A sliding window of size `k` moves from left to right across the array.  
At each step, return the **maximum element** inside the current window.

---

## Example

### Input
```

nums = [1,3,-1,-3,5,3,6,7], k = 3

```

### Output
```

[3,3,5,5,6,7]

```

### Window Visualization

```

[1  3  -1] -3  5  3  6  7   -> 3
1 [3  -1  -3] 5  3  6  7   -> 3
1  3 [-1  -3  5] 3  6  7   -> 5
1  3  -1 [-3  5  3] 6  7   -> 5
1  3  -1  -3 [5  3  6] 7   -> 6
1  3  -1  -3  5 [3  6  7]  -> 7

````

---

## Core Idea

This problem is **not** about finding a max repeatedly.  
It is about **remembering only useful candidates** for the maximum.

If an element is:
- smaller than a newer element  
- or outside the window  

it has **no future value** and should be discarded immediately.

This leads to a **monotonic decreasing deque**.

---

## Intuition (Thinking View)

Brute force scans the window again and again. That’s lazy thinking.

Optimized thinking:
- Each number should be added **once**
- Each number should be removed **once**
- No rescanning

If an element gets beaten by a newer, larger value, it will **never** be the max again.  
Kill it early.

---

## Approach 1: Brute Force

### Idea
For every window of size `k`, scan all `k` elements and find the max.

### Algorithm
1. Start window at index `0`
2. For each window:
   - Loop from `i` to `i + k - 1`
   - Track the maximum
3. Move window one step right

### Java Code
```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            int max = nums[i];
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }

        return result;
    }
}
````

### Complexity

* **Time:** `O(n * k)`
* **Space:** `O(1)`

This passes small inputs. It dies on large ones.

---

## Approach 2: Optimized (Deque / Monotonic Queue)

### Key Observation

Maintain a deque of **indices**, not values:

* Front of deque → index of **maximum element**
* Deque is always **decreasing by value**

---

### Algorithm Flow

For each index `i`:

1. **Remove out-of-window indices**

   * If `dq.front <= i - k`, remove it
2. **Maintain decreasing order**

   * While `nums[dq.last] < nums[i]`, remove last
3. **Insert current index**
4. **Record result**

   * Once `i >= k - 1`, max is `nums[dq.front]`

---

### Example Walkthrough

`nums = [1,3,-1,-3,5,3,6,7], k = 3`

* `i = 0`: dq = [1]
* `i = 1`: 3 > 1 → remove 1 → dq = [3]
* `i = 2`: dq = [3, -1] → max = 3
* `i = 4`: 5 removes all smaller elements → dq = [5]
* Continue...

Deque never grows unnecessarily.
Every index enters and leaves **once**.

---

### Java Code (Optimized)

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {

            // Remove indices outside the window
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // Remove smaller elements from the back
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }

            dq.offerLast(i);

            // Record the maximum
            if (i >= k - 1) {
                result[i - k + 1] = nums[dq.peekFirst()];
            }
        }

        return result;
    }
}
```

---

## Complexity Analysis

* **Time:** `O(n)`
* **Space:** `O(k)` (Deque size)


Common mistake:

* Trying to store values instead of indices
* Forgetting to remove out-of-window elements
* Not understanding *why* monotonic structure works


