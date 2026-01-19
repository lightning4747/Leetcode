# 103. Binary Tree Zigzag Level Order Traversal

## Problem Link
[LeetCode Problem #103](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/)

## Problem Statement
Given the root of a binary tree, return the **zigzag level order traversal** of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

**Example 1:**
```
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
```

**Example 2:**
```
Input: root = [1]
Output: [[1]]
```

**Example 3:**
```
Input: root = []
Output: []
```

---

## Intuition

Zigzag traversal is like regular level-order traversal with a twist - the direction alternates at each level:
- **Level 0:** Left to Right
- **Level 1:** Right to Left
- **Level 2:** Left to Right
- **Level 3:** Right to Left
- And so on...

The key insight is to use a **flag** to track whether we should add elements normally or in reverse order at each level.

---

## Core Idea & Thinking

The solution uses **BFS (Breadth-First Search)** with a directional flag:

1. **Queue for level-order traversal:** Process nodes level by level
2. **Flag for direction:** Toggle between left-to-right and right-to-left
3. **List manipulation:** Use `add()` for normal order and `addFirst()` for reverse order
4. **Children always added left-to-right:** Regardless of the current direction, we always add children in the same order to the queue

**Why this works:**
- The queue maintains the natural level-order
- We only manipulate how we **add to the result list**, not the queue traversal order
- This keeps the algorithm simple and efficient

---

## Visual Example

**Tree Structure:**
```
        3
       / \
      9   20
         /  \
        15   7
```

**Zigzag Traversal Visualization:**

```
Level 0 (→):  [3]
Level 1 (←):  [20, 9]
Level 2 (→):  [15, 7]
```

---

## Algorithm Flow with Example

**Input:** `root = [3,9,20,null,null,15,7]`

### Step-by-Step Execution:

**Initial State:**
- `queue = [3]`
- `flag = true` (left to right)
- `result = []`

---

**Level 0 (flag = true, left→right):**
1. Queue size = 1
2. Remove `3` from queue
3. `flag = true` → use `add()` → `level = [3]`
4. Add children: queue = `[9, 20]`
5. Toggle flag → `flag = false`
6. Add to result: `result = [[3]]`

---

**Level 1 (flag = false, right→left):**
1. Queue size = 2
2. Remove `9` from queue
   - `flag = false` → use `addFirst()` → `level = [9]`
   - Add children: none
3. Remove `20` from queue
   - `flag = false` → use `addFirst()` → `level = [20, 9]`
   - Add children: queue = `[15, 7]`
4. Toggle flag → `flag = true`
5. Add to result: `result = [[3], [20, 9]]`

---

**Level 2 (flag = true, left→right):**
1. Queue size = 2
2. Remove `15` from queue
   - `flag = true` → use `add()` → `level = [15]`
   - Add children: none
3. Remove `7` from queue
   - `flag = true` → use `add()` → `level = [15, 7]`
   - Add children: none
4. Toggle flag → `flag = false`
5. Add to result: `result = [[3], [20, 9], [15, 7]]`

---

**Final Result:** `[[3], [20, 9], [15, 7]]`

---

## Approach 1: BFS with Flag (Your Solution) ✅

### Code

```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Deque<TreeNode> q = new LinkedList<>();
        boolean flag = true; // true = left to right, false = right to left
        
        if (root == null) {
            return list;
        }
        
        q.add(root);
        
        while (!q.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int n = q.size();
            
            for (int i = 0; i < n; i++) {              
                TreeNode curr = q.remove();
                
                if (flag) {
                    // Left to right: add normally
                    level.add(curr.val);
                } else {
                    // Right to left: add at the beginning
                    level.addFirst(curr.val);
                }
                
                // Always add children in the same order
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }
            
            flag = !flag; // Toggle direction for next level
            list.add(level);
        }
        
        return list;
    }
}
```

### Complexity Analysis
- **Time Complexity:** O(n) - we visit each node exactly once
- **Space Complexity:** O(w) - where w is the maximum width of the tree (queue storage)
  - In the worst case (complete binary tree), w = n/2, so O(n)

---

## Approach 2: BFS with Reverse (Alternative)

### Algorithm
Process level normally, then reverse the list if needed.

```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            
            // Reverse if needed
            if (!leftToRight) {
                Collections.reverse(level);
            }
            
            result.add(level);
            leftToRight = !leftToRight;
        }
        
        return result;
    }
}
```

### Complexity Analysis
- **Time Complexity:** O(n) - visiting all nodes + O(w) for reversing each level
- **Space Complexity:** O(w)

---

## Approach 3: DFS with Level Tracking (Advanced)

### Algorithm
Use DFS with depth tracking and add elements based on level parity.

```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }
    
    private void dfs(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null) return;
        
        // Create new level list if needed
        if (level == result.size()) {
            result.add(new LinkedList<>());
        }
        
        // Add to front or back based on level
        if (level % 2 == 0) {
            result.get(level).add(node.val); // Left to right
        } else {
            result.get(level).addFirst(node.val); // Right to left
        }
        
        dfs(node.left, level + 1, result);
        dfs(node.right, level + 1, result);
    }
}
```

### Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(h) - recursion stack depth

---

## Comparison of Approaches

| Approach | Time | Space | Pros | Cons |
|----------|------|-------|------|------|
| **BFS with Flag** | O(n) | O(w) | Most intuitive, direct | Uses `addFirst()` which can be O(n) for ArrayList |
| **BFS with Reverse** | O(n) | O(w) | Simple logic | Extra O(w) operations for reversing |
| **DFS** | O(n) | O(h) | Space-efficient for skewed trees | Less intuitive |

---

## Key Insights

### Why Use Deque Instead of ArrayList?

```java
List<Integer> level = new ArrayList<>();  // ❌ addFirst() not available
Deque<Integer> level = new LinkedList<>(); // ✅ Has addFirst() method
```

- `LinkedList` implements `Deque` interface
- `addFirst()` is O(1) for LinkedList vs O(n) for ArrayList
- This is crucial for the zigzag pattern

### The Flag Pattern

```java
if (flag) {
    level.add(curr.val);      // Append to end
} else {
    level.addFirst(curr.val); // Prepend to start
}
flag = !flag; // Toggle after each level
```

This elegant pattern handles the alternating direction without complex logic.

---

## Common Mistakes to Avoid

❌ **Mistake 1:** Changing the order of adding children to queue
```java
// WRONG: Don't do this
if (flag) {
    q.add(curr.left);
    q.add(curr.right);
} else {
    q.add(curr.right);
    q.add(curr.left);
}
```

✅ **Correct:** Always add children in the same order, only change how you add to the result list

❌ **Mistake 2:** Using ArrayList without converting to LinkedList
```java
List<Integer> level = new ArrayList<>();
level.addFirst(curr.val); // Compile error!
```

✅ **Correct:** Use `LinkedList` or `Deque` interface

---

## Related Problems

- **102. Binary Tree Level Order Traversal** (No zigzag)
- **107. Binary Tree Level Order Traversal II** (Bottom-up)
- **637. Average of Levels in Binary Tree**
- **429. N-ary Tree Level Order Traversal**

---

## Visualization

```
        3
       / \
      9   20
         /  \
        15   7

Level 0 (→): Process [3]           → Result: [3]
Level 1 (←): Process [9,20]        → Result: [20,9]  (reversed)
Level 2 (→): Process [15,7]        → Result: [15,7]

Final: [[3], [20,9], [15,7]]
```

---

## Summary

The **zigzag level order traversal** is a clever variation of standard BFS that alternates direction at each level. The key is using:
- A **flag** to track direction
- **`addFirst()`** method for reverse insertion (requires Deque/LinkedList)
- Standard BFS queue processing
