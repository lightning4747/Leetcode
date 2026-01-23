# 🎨 LeetCode 1034 – Coloring A Border

## 🔢 LeetCode Number
**1034**

## 🔗 Problem Link
https://leetcode.com/problems/coloring-a-border/

---

## 📘 Problem Statement

You are given an `m x n` integer grid and three integers `row`, `col`, and `color`.

- The cell `(row, col)` is part of a **connected component** (4-directionally) of cells with the **same value**.
- A **border cell** is a cell in the component that:
  - is on the boundary of the grid **OR**
  - has at least one neighbor with a different value

Your task is to **color only the border** of this connected component with the given `color`.

---

## 🖼️ Visual Explanation

### Original Grid
![Original Grid](https://assets.leetcode.com/uploads/2020/01/16/board.png)

### Border Colored
![Colored Border](https://assets.leetcode.com/uploads/2020/01/16/board2.png)

---

## 🧠 Intuition

This problem is not just about coloring cells —  
it’s about **identifying which cells belong to the border of a connected component**.

Key observations:
- We must **not color inner cells**
- Only cells touching the grid boundary or a different color should change
- The component must be explored **before coloring**, otherwise we lose information

👉 This naturally suggests **DFS / BFS traversal** with careful checks.

---

## 💡 Core Idea

1. Use **DFS** to traverse the connected component
2. Track visited cells
3. For each cell, check:
   - Is it on the grid boundary?
   - Does it have a neighbor with a different color?
4. If yes → mark it as a **border**
5. After traversal, color only the marked border cells

---

## 🧩 Thinking View (How to Approach)

- Don’t color while traversing → it breaks comparisons
- First **identify**, then **modify**
- Think in two phases:
  1. Detection
  2. Coloring

This separation keeps logic clean and bug-free.

---

## 🐌 Brute Force Approach

### ❌ Idea
- Traverse entire grid
- For every cell, check neighbors
- Color if mismatch found

### ❌ Problems
- Colors unrelated components
- No guarantee cell belongs to the required connected component
- Inefficient and incorrect

### ⏱ Complexity
```

Time: O(m × n)
Space: O(1)

````

❌ **Rejected**

---

## ⚡ Optimized Solution (DFS)

### ✔️ Approach
- DFS from `(row, col)`
- Use a `visited` matrix
- Store border cells in a list
- Color them at the end

---

## 🧑‍💻 Java Code (Optimized)

```java
class Solution {
    int m, n;
    int[][] grid;
    boolean[][] visited;
    List<int[]> borders = new ArrayList<>();
    int originalColor;

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        originalColor = grid[row][col];

        dfs(row, col);

        for (int[] cell : borders) {
            grid[cell[0]][cell[1]] = color;
        }
        return grid;
    }

    void dfs(int r, int c) {
        visited[r][c] = true;
        int count = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (nr >= 0 && nr < m && nc >= 0 && nc < n &&
                grid[nr][nc] == originalColor) {
                count++;
                if (!visited[nr][nc]) {
                    dfs(nr, nc);
                }
            }
        }

        if (count < 4) {
            borders.add(new int[]{r, c});
        }
    }
}
````

---

## 🔁 Algorithm Flow (Example Walkthrough)

### Input

```
grid =
1 1 1
1 1 1
1 1 1

row = 1, col = 1, color = 2
```

### Step-by-Step

1. Start DFS from center `(1,1)`
2. Traverse all connected `1`s
3. Center cell has **4 valid neighbors** → NOT border
4. Edge cells have fewer neighbors → BORDER
5. Color only border cells with `2`

### Output

```
2 2 2
2 1 2
2 2 2
```

---

## ⏱️ Complexity Analysis

### Time Complexity

```
O(m × n)
```

(DFS visits each cell once)

### Space Complexity

```
O(m × n)
```

(Visited matrix + recursion stack)

---

## 🧠 Key Takeaways

* Always **detect before modifying**
* Border problems require **neighbor counting**
* DFS is ideal for connected component traversal
* Separate **logic** and **mutation**

---

## ✅ Final Verdict

✔️ Elegant DFS application
✔️ Clean border detection
✔️ Strong interview problem

This problem tests:

* graph traversal
* boundary conditions
* careful state handling
