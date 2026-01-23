# 🧩 LeetCode 130 – Surrounded Regions

## 🔢 LeetCode Number
**130**

## 🔗 Problem Link
https://leetcode.com/problems/surrounded-regions/

---

## 📘 Problem Statement

You are given an `m x n` 2D board containing `'X'` and `'O'`.

Your task is to **capture all regions surrounded by `'X'`**.

A region is captured by flipping all `'O'`s into `'X'`s **if and only if**:
- The region is **completely surrounded**
- `'O'` cells connected to the **boundary** must **NOT** be flipped

### Rules
- Connections are **4-directional** (up, down, left, right)
- Boundary-connected `'O'` cells are always safe

---

## 🖼️ Visual Explanation

### Input Board
![Input Board](https://assets.leetcode.com/uploads/2021/02/19/xogrid.jpg)

### Output Board
![Output Board](https://assets.leetcode.com/uploads/2021/02/19/xogrid_output.jpg)

---

## 🧠 Intuition

At first, it feels natural to flip every `'O'` surrounded by `'X'`.

But here’s the catch 👇  
> **How do we know if an `'O'` is truly surrounded?**

💡 Key Insight:
> Any `'O'` connected to the **boundary** can never be surrounded.

So instead of finding surrounded regions, we:
1. **Protect boundary-connected `'O'`s**
2. Flip everything else

This reversal makes the problem much simpler.

---

## 💡 Core Idea

1. Start DFS/BFS from **all boundary `'O'`s**
2. Mark them as **safe**
3. Traverse the board:
   - Flip remaining `'O'` → `'X'`
   - Restore safe cells back to `'O'`

---

## 🧩 Thinking View (How to Approach)

Instead of asking:
> “Should this `'O'` be flipped?”

Ask:
> “Is this `'O'` connected to the boundary?”

This **reverse thinking** avoids repeated checks and simplifies logic.

---

## 🐌 Brute Force Approach

### ❌ Idea
For every `'O'`:
- Run DFS
- Check if the region touches boundary
- Flip if not

### ❌ Problems
- Same region explored multiple times
- High time complexity

### ⏱ Complexity
```

Time: O((m × n)²)
Space: O(m × n)

````

❌ Inefficient and unnecessary

---

## ⚡ Optimized Solution (DFS from Boundary)

### ✔️ Strategy

### Step 1: Mark Safe Regions
- Traverse:
  - First row
  - Last row
  - First column
  - Last column
- DFS from `'O'`
- Mark safe cells as `'#'`

### Step 2: Final Conversion
- `'O'` → `'X'`
- `'#'` → `'O'`

---

## 🧑‍💻 Java Code (Optimized)

```java
class Solution {
    int m, n;

    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;

        m = board.length;
        n = board[0].length;

        // Step 1: DFS from boundary
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            dfs(board, 0, j);
            dfs(board, m - 1, j);
        }

        // Step 2: Flip surrounded regions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'O')
            return;

        board[i][j] = '#';

        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }
}
````

---

## 🔁 Algorithm Flow (Example Walkthrough)

### Input

```
X X X X
X O O X
X X O X
X O X X
```

### Step 1: Boundary DFS

```
X X X X
X O O X
X X O X
X # X X
```

### Step 2: Flip

```
X X X X
X X X X
X X X X
X O X X
```

✔ Surrounded regions flipped
✔ Boundary region preserved

---

## ⏱️ Complexity Analysis

### Time Complexity

```
O(m × n)
```

Each cell visited once

### Space Complexity

```
O(m × n)
```

DFS recursion stack (worst case)

---

## 🧠 Key Takeaways

* Boundary-connected regions are always safe
* Reverse thinking simplifies complex conditions
* DFS/BFS is perfect for grid-region problems
* Always **mark first, modify later**

---

## ✅ Final Verdict

✔️ Clean logic
✔️ Optimal performance
✔️ Very common interview problem

Tests:

* Grid traversal
* DFS mastery
* Boundary reasoning
