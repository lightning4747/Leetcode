
# 🧮 Container With Most Water

## 🔗 Problem Link
LeetCode: https://leetcode.com/problems/container-with-most-water/

---

## 📘 Problem Statement

You are given an integer array `height` of length `n`.  
There are `n` vertical lines drawn such that the two endpoints of the `iᵗʰ` line are `(i, 0)` and `(i, height[i])`.

Find **two lines** that together with the x-axis form a container, such that the container contains the **most water**.

👉 Return the **maximum amount of water** a container can store.

---

## 🧠 Key Insight

The amount of water stored between two lines depends on:
- **Width** = distance between indices
- **Height** = minimum of the two line heights
```

Area = min(height[left], height[right]) × (right - left)

```

---

## 🐌 Brute Force Approach

### 💡 Idea
Check **every possible pair of lines**, calculate the area, and track the maximum.

### 🔄 Algorithm
1. Use two nested loops
2. For each pair `(i, j)`:
   - Calculate area
   - Update maximum

### ⏱ Time & Space Complexity
- **Time:** O(n²)
- **Space:** O(1)

### 🧾 Brute Force Pseudocode
```

maxArea = 0
for i from 0 to n-1:
for j from i+1 to n-1:
area = min(height[i], height[j]) * (j - i)
maxArea = max(maxArea, area)
return maxArea

```

---

## ⚡ Optimized Approach (Two Pointers)

### 💡 Idea
Instead of checking all pairs:
- Start with **two pointers** at both ends
- Move the pointer with the **smaller height**
- This works because height limits the area

---

## 🔄 Optimized Algorithm Flow

1. Initialize:
   - `left = 0`
   - `right = n - 1`
   - `maxArea = 0`
2. While `left < right`:
   - Calculate area
   - Update `maxArea`
   - Move the pointer pointing to the **shorter line**
3. Return `maxArea`

---

## 🧪 Example Walkthrough

### Input
```

height = [1,8,6,2,5,4,8,3,7]

````

### Step-by-step

| Left | Right | Heights | Width | Area | Max |
|-----|------|--------|------|------|-----|
| 0 | 8 | 1,7 | 8 | 8 | 8 |
| 1 | 8 | 8,7 | 7 | 49 | 49 |
| 1 | 7 | 8,3 | 6 | 18 | 49 |
| 1 | 6 | 8,8 | 5 | 40 | 49 |
| ... | ... | ... | ... | ... | 49 |

✅ **Answer = 49**

---

## ⏱ Complexity Analysis (Optimized)

- **Time:** O(n)
- **Space:** O(1)

---

## 🐍 Python – Optimized Solution

```python
class Solution:
    def maxArea(self, height):
        left, right = 0, len(height) - 1
        max_area = 0

        while left < right:
            width = right - left
            area = min(height[left], height[right]) * width
            max_area = max(max_area, area)

            if height[left] < height[right]:
                left += 1
            else:
                right -= 1

        return max_area
````

---

## ☕ Java – Optimized Solution

```java
class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int area = Math.min(height[left], height[right]) * width;
            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
```

---

## ✅ Summary

| Approach     | Time     | Space    |
| ------------ | -------- | -------- |
| Brute Force  | O(n²)    | O(1)     |
| Two Pointers | **O(n)** | **O(1)** |

✔ The **Two Pointer approach** is optimal and required to pass large inputs.

---

## 🏁 Final Note

This is a **classic two-pointer problem** and appears frequently in interviews.
Mastering the **pointer movement logic** is key
