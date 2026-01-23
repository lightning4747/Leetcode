# 🏝️ LeetCode 1254 – Number of Closed Islands

## 🔢 LeetCode Number
**1254**

## 🔗 Problem Link
https://leetcode.com/problems/number-of-closed-islands/

---

## 📘 Problem Statement

Given a 2D grid where:
- `0` represents **land**
- `1` represents **water**

A **closed island** is a group of connected land cells (`0`s) that is **completely surrounded by water** (`1`s) on all four sides.

Your task is to return the **number of closed islands** in the grid.

### Rules
- Land cells are connected **4-directionally** (up, down, left, right)
- Land touching the **boundary of the grid** is **NOT** a closed island

---

## 🖼️ Visual Explanation

### Example Grid
![Closed Island Example](https://assets.leetcode.com/uploads/2020/01/16/sample_3_1610.png)

- Islands touching the boundary ❌
- Islands fully surrounded by water ✅

---

## 🧠 Intuition

The key idea is simple:

> 💡 **Any land connected to the boundary can never be a closed island**

So instead of counting directly:
1. First **eliminate all land connected to boundaries**
2. Then count the remaining isolated land components

This turns the problem into a **flood-fill + counting problem**.

---

## 💡 Core Idea

- Use **DFS/BFS**
- First pass: remove (sink) all land (`0`) connected to the border
- Second pass: count the remaining land components
- Each DFS in the second pass represents **one closed island**

---

## 🧩 Thinking View (How to Approach)

Instead of asking:
> “Is this island closed?”

Flip the thinking:
> “Which islands are definitely NOT closed?”

This reversal simplifies logic and avoids repeated boundary checks.

---

## 🐌 Brute Force Approach

### ❌ Idea
- For every land cell:
  - DFS to check if it reaches boundary
  - Count if it doesn’t

### ❌ Problems
- Same island checked multiple times
- Repeated DFS → inefficient

### ⏱ Complexity
```

Time: O((m × n)²)
Space: O(m × n)

````

❌ **Not optimal**

---

## ⚡ Optimized Solution (DFS Flood Fill)

### ✔️ Strategy

### Step 1: Remove boundary-connected land
- Traverse first & last rows
- Traverse first & last columns
- DFS from any `0` found and convert it to `1`

### Step 2: Count closed islands
- Traverse grid
- For each remaining `0`, increment count and DFS to mark it visited

---

## 🧑‍💻 Java Code (Optimized)

```java
class Solution {
    int m, n;

    public int closedIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Step 1: Eliminate boundary-connected land
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            dfs(grid, 0, j);
            dfs(grid, m - 1, j);
        }

        // Step 2: Count closed islands
        int count = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0) {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    void dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == 1)
            return;

        grid[r][c] = 1;

        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}
````

---

## 🔁 Algorithm Flow (Example Walkthrough)

### Input

```
1 1 1 1
1 0 0 1
1 0 0 1
1 1 1 1
```

### Step 1: Boundary Flood Fill

* No boundary land → grid unchanged

### Step 2: Count Islands

* One DFS from `(1,1)`
* Entire island consumed
* Count = 1

### Output

```
1
```

---

## ⏱️ Complexity Analysis

### Time Complexity

```
O(m × n)
```

Each cell visited at most once

### Space Complexity

```
O(m × n)
```

DFS recursion stack (worst case)

---

## 🧠 Key Takeaways

* Boundary conditions decide island validity
* Eliminate invalid cases first
* DFS flood fill is powerful for grid problems
* Reverse thinking often simplifies logic

---

## ✅ Final Verdict

✔️ Clean
✔️ Efficient
✔️ Interview-favorite

This problem strongly tests:

* grid traversal
* boundary reasoning
* DFS mastery

---
