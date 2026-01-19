# LeetCode 104 — Maximum Depth of Binary Tree

**Problem Link:** [https://leetcode.com/problems/maximum-depth-of-binary-tree/](https://leetcode.com/problems/maximum-depth-of-binary-tree/)

---

## 🧠 Problem Statement

Given the `root` of a binary tree, return its **maximum depth**.

The **maximum depth** is the number of nodes along the longest path from the root node down to the farthest leaf node.

---

## 📥 Example

**Input:**

```
      3
     / \
    9  20
       / \
      15  7
```

**Output:**

```
3
```

---

## 🖼️ Visual Explanation

![Binary Tree Depth Example](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/Binary_tree.svg/512px-Binary_tree.svg.png)

The longest path is: `3 → 20 → 15` (or `3 → 20 → 7`), which has **3 nodes**.

---

## 💡 Intuition

A binary tree is naturally recursive:

* Every node has a **left subtree** and a **right subtree**.
* The depth of a tree is:

```
1 + max(depth of left subtree, depth of right subtree)
```

If the node is `null`, the depth is `0`.

---

## 🧩 Core Idea & Thinking View

* If the tree is empty → depth is `0`
* Otherwise:

  * Recursively compute the depth of left child
  * Recursively compute the depth of right child
  * Take the maximum of both and add `1` for the current node

This naturally leads to a **Depth-First Search (DFS)** solution.

---

## 🐌 Brute Force Approach (DFS Recursion)

### Algorithm

1. If the node is `null`, return `0`
2. Recursively find left subtree depth
3. Recursively find right subtree depth
4. Return `1 + max(leftDepth, rightDepth)`

### Java Code

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        return 1 + Math.max(left, right);
    }
}
```

### Time & Space Complexity

* **Time:** `O(n)` — every node is visited once
* **Space:** `O(n)` — recursion stack (worst case skewed tree)

---

## 🚀 Optimized Approach (BFS – Level Order Traversal)

Instead of recursion, we can use **Breadth-First Search (BFS)** and count levels.

### Algorithm

1. Use a queue
2. Push root into the queue
3. For each level:

   * Process all nodes in the queue
   * Push their children
   * Increment depth counter

### Java Code

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            depth++;
        }
        return depth;
    }
}
```

### Time & Space Complexity

* **Time:** `O(n)`
* **Space:** `O(n)` (queue storage)

---

## 🔍 Example Walkthrough

Tree:

```
      1
     / \
    2   3
   /
  4
```

### DFS Flow:

* Node 4 → depth = 1
* Node 2 → 1 + max(1, 0) = 2
* Node 3 → 1
* Node 1 → 1 + max(2, 1) = **3**

---

## 🧠 Summary

| Approach    | Technique       | Time | Space |
| ----------- | --------------- | ---- | ----- |
| Brute Force | DFS (Recursion) | O(n) | O(n)  |
| Optimized   | BFS (Queue)     | O(n) | O(n)  |

✔ DFS is simpler and most commonly used
✔ BFS avoids recursion stack overflow

---

## ✅ Final Takeaway

This problem is a **classic recursion + tree depth** question. If you understand:

* Base case
* Recursive relation

You can solve many binary tree problems easily 🌳

---

Happy Coding 🚀
