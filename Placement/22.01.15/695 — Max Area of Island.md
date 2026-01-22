# 🏝️ LeetCode 695 — Max Area of Island

## 📌 Problem Overview

**LeetCode Number:** 695
**Problem Link:** [https://leetcode.com/problems/max-area-of-island/](https://leetcode.com/problems/max-area-of-island/)

You are given an `m x n` binary matrix `grid` where:

* `1` represents land
* `0` represents water

An **island** is formed by connecting adjacent lands **horizontally or vertically** (not diagonally).

👉 The task is to **return the maximum area of an island** in the grid. If there is no island, return `0`.

---

## 🧠 Intuition

Think of the grid as a map:

* Each `1` is a piece of land
* Islands are groups of connected lands

To find the **largest island**, we:

1. Traverse every cell
2. When we find land (`1`), explore the entire island
3. Count how many connected cells belong to that island
4. Keep track of the maximum area found

This is a classic **graph traversal on a grid** problem.

---

## 🖼️ Visual Representation

![Island Grid Example](https://leetcode.com/problems/max-area-of-island/Figures/695/695_Example_1.png)

---

## 🔍 Core Idea (Thinking View)

* Treat the grid as a graph
* Each cell is a node
* Adjacent `1`s are connected
* Use **DFS or BFS** to explore connected components
* Mark visited cells to avoid revisiting

> The problem reduces to finding the **largest connected component** in a grid.

---

## 🐢 Brute Force Approach

### 💡 Idea

* For every cell with value `1`, start a DFS
* Use a separate `visited` array
* Count the area for each DFS
* Track the maximum

### ⏱️ Time Complexity

* **O(m × n)** — every cell visited once

### 💾 Space Complexity

* **O(m × n)** — visited array + recursion stack

### 🧾 Brute Force Code (Java)

```java
class Solution {
    boolean[][] visited;
    int rows, cols;

    public int maxAreaOfIsland(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        int maxArea = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= rows || j >= cols ||
            grid[i][j] == 0 || visited[i][j]) {
            return 0;
        }

        visited[i][j] = true;
        return 1 + dfs(grid, i + 1, j)
                 + dfs(grid, i - 1, j)
                 + dfs(grid, i, j + 1)
                 + dfs(grid, i, j - 1);
    }
}
```

---

## ⚡ Optimized Approach (In-place DFS)

### 💡 Optimization Idea

Instead of using a `visited` array:

* **Modify the grid directly**
* Mark visited land as `0`

This saves memory and simplifies logic.

---

### 🧾 Optimized Code (Your Uploaded Solution)

```java
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 0; // mark visited
        int area = 1;
        area += dfs(grid, i + 1, j);
        area += dfs(grid, i - 1, j);
        area += dfs(grid, i, j + 1);
        area += dfs(grid, i, j - 1);
        return area;
    }
}
```

---

## 🔄 Algorithm Flow (Example Walkthrough)

### Example Input

```
grid = [
  [0,0,1,0,0],
  [1,1,1,0,0],
  [0,0,0,1,1]
]
```

### Step-by-Step Flow

1. Start scanning from top-left
2. First land found at `(0,2)` → DFS starts
3. DFS expands to `(1,2)`, `(1,1)`, `(1,0)`
4. Area calculated = `4`
5. Continue scanning
6. Second island at `(2,3)` → area = `2`
7. Max area = `4`

---

## 📊 Complexity Analysis

| Approach      | Time     | Space                            |
| ------------- | -------- | -------------------------------- |
| Brute Force   | O(m × n) | O(m × n)                         |
| Optimized DFS | O(m × n) | O(1) (excluding recursion stack) |

---

## ✅ Key Takeaways

* Grid problems often reduce to **graph traversal**
* DFS/BFS are ideal for connected components
* In-place modification can optimize space
* This pattern appears in many problems like:

  * Number of Islands (LC 200)
  * Island Perimeter (LC 463)
  * Flood Fill (LC 733)

---

✨ **Pro Tip:** If recursion depth is a concern, use BFS with a queue.

Happy Coding 🚀
