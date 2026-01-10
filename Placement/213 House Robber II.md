# 🏠 LeetCode 213 — House Robber II

**Difficulty:** Medium
**LeetCode Number:** 213
**Problem Link:** [https://leetcode.com/problems/house-robber-ii/](https://leetcode.com/problems/house-robber-ii/)

---

## 📘 Problem Statement

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. **All houses are arranged in a circle**, meaning the first and the last house are adjacent.

You cannot rob two adjacent houses.

Return the **maximum amount of money** you can rob without alerting the police.

---

## 🧠 Core Challenge

In **House Robber I**, houses are in a straight line.

In **House Robber II**, houses form a **circle**, which introduces a key constraint:

> ❌ You **cannot rob both the first and last house**.

---

## 💡 Intuition (Thinking View)

Because the first and last houses are adjacent:

* If you rob the **first house**, you **must skip the last**
* If you rob the **last house**, you **must skip the first**

So the circular problem can be broken into **two linear problems**:

1. Rob houses from index **0 to n-2** (exclude last house)
2. Rob houses from index **1 to n-1** (exclude first house)

👉 Take the **maximum** of the two results.

---

## 🖼️ Visual Explanation

### Circular Street

![Circular Houses](https://assets.leetcode.com/uploads/2020/10/01/house_robber_2.png)

You must break the circle into two straight lines:

```
Case 1: [ H0  H1  H2  H3 ]   (exclude last)
Case 2: [ H1  H2  H3  H4 ]   (exclude first)
```

---

## 🧪 Example Walkthrough

### Example Input

```text
nums = [2, 3, 2]
```

### Case 1: Rob from index 0 → n-2

```text
[2, 3] → max = 3
```

### Case 2: Rob from index 1 → n-1

```text
[3, 2] → max = 3
```

### ✅ Final Answer

```text
max(3, 3) = 3
```

---

## 🐌 Brute Force Approach (Recursion)

### Idea

Try all possible combinations of robbing or skipping houses while ensuring:

* No two adjacent houses are robbed
* First and last house are not both robbed

### Time Complexity

❌ **O(2ⁿ)** — Exponential (TLE)

### Java Code (Conceptual)

```java
public int rob(int[] nums) {
    return helper(nums, 0, false);
}

private int helper(int[] nums, int index, boolean prevRobbed) {
    if (index >= nums.length) return 0;

    int skip = helper(nums, index + 1, false);
    int take = 0;
    if (!prevRobbed) {
        take = nums[index] + helper(nums, index + 1, true);
    }
    return Math.max(skip, take);
}
```

⚠️ This approach is **not practical** for large inputs.

---

## ⚡ Optimized Approach (Dynamic Programming)

### Step 1: Handle Edge Cases

* If `n == 1` → return `nums[0]`

### Step 2: Solve Two Linear Robber Problems

Use **House Robber I logic** twice:

* `robLinear(nums, 0, n-2)`
* `robLinear(nums, 1, n-1)`

---

### 🧩 Linear Robber Logic

For a linear array:

```text
dp[i] = max(dp[i-1], dp[i-2] + nums[i])
```

We can optimize space using two variables.

---

## ✅ Optimized Java Solution

```java
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        return Math.max(
            robLinear(nums, 0, n - 2),
            robLinear(nums, 1, n - 1)
        );
    }

    private int robLinear(int[] nums, int start, int end) {
        int prev2 = 0; // dp[i-2]
        int prev1 = 0; // dp[i-1]

        for (int i = start; i <= end; i++) {
            int curr = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
```

---

## ⏱️ Complexity Analysis

| Approach     | Time  | Space |
| ------------ | ----- | ----- |
| Brute Force  | O(2ⁿ) | O(n)  |
| Optimized DP | O(n)  | O(1)  |

---

## 🧠 Final Takeaway

* Circular dependency is handled by **breaking the problem** into two linear cases
* Reuse **House Robber I** logic
* Always think: *"Can I split the problem to avoid conflict?"*

---

✨ **This is a classic example of reducing a complex constraint using smart case analysis.**
