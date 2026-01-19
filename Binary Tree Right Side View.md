# 199. Binary Tree Right Side View

## Problem Link
[LeetCode Problem #199](https://leetcode.com/problems/binary-tree-right-side-view/)

## Problem Statement
Given the root of a binary tree, imagine yourself standing on the **right side** of it. Return the values of the nodes you can see ordered from top to bottom.

**Example 1:**
```
Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]
```

**Example 2:**
```
Input: root = [1,null,3]
Output: [1,3]
```

**Example 3:**
```
Input: root = []
Output: []
```

---

## Intuition

When you stand on the right side of a binary tree, you can only see **one node per level** - specifically, the **rightmost node** at each depth. The key insight is that we need to capture the first node we encounter at each level when traversing from right to left.

Think of it like taking a photograph of the tree from the right side - you'd see the rightmost visible node at each level, even if there are other nodes hidden behind them on the left.

---

## Core Idea

The solution uses **Depth-First Search (DFS)** with a clever observation:
- At each depth/level, we only want to record **one node** - the first one we see from the right
- By traversing the **right subtree first**, then the left subtree, we ensure we visit the rightmost node at each level first
- We use the `depth` parameter to track which level we're at
- When `depth == res.size()`, it means we're visiting this level for the first time, so we add the current node's value

---

## Approach 1: DFS (Depth-First Search) - Optimized ✅

### Algorithm Flow

**Step-by-step with Example:** `root = [1,2,3,null,5,null,4]`

```
        1
       / \
      2   3
       \   \
        5   4
```

**Traversal Order:**
1. Start at node `1` (depth=0): `res = []`, depth == size, add `1` → `res = [1]`
2. Go right to node `3` (depth=1): `res = [1]`, depth == size, add `3` → `res = [1,3]`
3. Go right from `3` to node `4` (depth=2): `res = [1,3]`, depth == size, add `4` → `res = [1,3,4]`
4. Backtrack to `3`, go left (null), backtrack to `1`
5. Go left to node `2` (depth=1): `res = [1,3,4]`, depth < size, skip (we already have level 1)
6. Go right from `2` to node `5` (depth=2): `res = [1,3,4]`, depth < size, skip (we already have level 2)

**Final Result:** `[1,3,4]`

### Code (Your Solution)

```java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {    
        List<Integer> res = new ArrayList<>();
        mirror(root, res, 0);
        return res;
    }
    
    private void mirror(TreeNode curr, List<Integer> res, int depth) {
        if (curr == null) {
            return;
        }
        
        // If this is the first node we're visiting at this depth
        if (depth == res.size()) {
            res.add(curr.val);
        }
        
        // Traverse right first, then left
        mirror(curr.right, res, depth + 1);
        mirror(curr.left, res, depth + 1);
    }
}
```

### Complexity Analysis
- **Time Complexity:** O(n) - we visit each node exactly once
- **Space Complexity:** O(h) - recursion stack depth, where h is the height of the tree
  - Best case (balanced tree): O(log n)
  - Worst case (skewed tree): O(n)

---

## Approach 2: BFS (Breadth-First Search) - Alternative

### Algorithm
Use level-order traversal and take the last element at each level.

```java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                // Add the last node of this level
                if (i == levelSize - 1) {
                    res.add(node.val);
                }
                
                // Add children (left to right)
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        return res;
    }
}
```

### Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(w) - where w is the maximum width of the tree (for the queue)

---

## Thinking Process

### Why DFS with Right-First Traversal Works:

1. **Depth tracking:** We track which level we're currently at
2. **Right-first order:** By visiting right children before left children, we guarantee that the rightmost node at each level is visited first
3. **First-time check:** `depth == res.size()` ensures we only add the first node we see at each new level
4. **Natural filtering:** Left nodes at the same depth are automatically ignored since we've already recorded that level

### Visual Representation:

```
Right Side View:
        1          ← See 1 (level 0)
       / \
      2   3        ← See 3 (level 1, rightmost)
       \   \
        5   4      ← See 4 (level 2, rightmost)

Result: [1, 3, 4]
```

---

## Key Takeaways

✅ **DFS approach** is more intuitive and uses less space for balanced trees  
✅ **BFS approach** is easier to understand conceptually (just take last node per level)  
✅ Both approaches have O(n) time complexity  
✅ The trick with DFS is visiting **right subtree first** to naturally capture rightmost nodes  
✅ Using `depth == res.size()` is a elegant way to check if we've seen this level before
