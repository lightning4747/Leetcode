# LeetCode 1161: Maximum Level Sum of a Binary Tree

## Problem Information
- **LeetCode Number:** 1161
- **Difficulty:** Medium
- **Problem Link:** [https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/](https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/)
- **Topics:** Tree, Breadth-First Search (BFS), Binary Tree

---

## Problem Statement

Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on. Return the smallest level x such that the sum of all the values of nodes at level x is maximal.

**Important Note:** If multiple levels have the same maximum sum, return the smallest level number.

---

## Examples

### Example 1
```
Input: root = [1,7,0,7,-8,null,null]
Output: 2

Explanation:
Level 1 sum = 1
Level 2 sum = 7 + 0 = 7
Level 3 sum = 7 + (-8) = -1
So we return level 2 (maximum sum = 7)
```

### Example 2
```
Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
Output: 2
```

---

## Constraints
- The number of nodes in the tree is in the range `[1, 10^4]`
- `-10^5 <= Node.val <= 10^5`

---

## Core Intuition

The problem requires us to:
1. **Process the tree level by level** (not node by node)
2. **Calculate the sum at each level**
3. **Track which level has the maximum sum**
4. **Return the smallest level if there's a tie**

Since we need to process nodes level by level, **Breadth-First Search (BFS)** is the natural choice. BFS processes all nodes at depth `d` before moving to depth `d+1`.

---

## Approach 1: Brute Force (DFS with Level Tracking)

### Algorithm
Use Depth-First Search (DFS) to traverse the tree recursively while tracking each node's level. Store level sums in an array/list where the index represents the level.

### Implementation
```java
public class MaximumLevelSumBruteForce {
    public int maxLevelSum(TreeNode root) {
        List<Integer> levelSums = new ArrayList<>();
        dfs(root, 0, levelSums);
        
        int maxSum = Integer.MIN_VALUE;
        int resultLevel = 1;
        
        for (int i = 0; i < levelSums.size(); i++) {
            if (levelSums.get(i) > maxSum) {
                maxSum = levelSums.get(i);
                resultLevel = i + 1; // Levels are 1-indexed
            }
        }
        
        return resultLevel;
    }
    
    private void dfs(TreeNode node, int level, List<Integer> levelSums) {
        if (node == null) return;
        
        // If this is the first node at this level, add a new entry
        if (level >= levelSums.size()) {
            levelSums.add(0);
        }
        
        // Add current node's value to its level sum
        levelSums.set(level, levelSums.get(level) + node.val);
        
        // Recursively process children
        dfs(node.left, level + 1, levelSums);
        dfs(node.right, level + 1, levelSums);
    }
}
```

### Complexity Analysis
- **Time Complexity:** O(n) - Visit each node once
- **Space Complexity:** O(h) where h is the height of the tree (recursion stack)

### Drawbacks
- Requires two passes: one to collect sums, another to find maximum
- Uses recursion which can lead to stack overflow for very deep trees
- Not as intuitive for a level-based problem

---

## Approach 2: Optimized Solution (BFS with Queue)

### Algorithm
Use a queue to perform level-order traversal. For each level:
1. Capture the current queue size (number of nodes at this level)
2. Process exactly that many nodes
3. Calculate the sum while adding children to the queue
4. Update the maximum sum and result level if current sum is greater

### Implementation
```java
public class MaximumLevelSumofaBinaryTree {
    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxSum = Integer.MIN_VALUE;
        int resultLevel = 1;
        int currentLevel = 1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Critical: capture size before processing
            int currentLevelSum = 0;

            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevelSum += currentNode.val;

                // Add children for next level
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // Update max if current level sum is greater
            if (currentLevelSum > maxSum) {
                maxSum = currentLevelSum;
                resultLevel = currentLevel;
            }
            currentLevel++;
        }

        return resultLevel;
    }
}
```

### Complexity Analysis
- **Time Complexity:** O(n) - Visit each node exactly once
- **Space Complexity:** O(w) where w is the maximum width of the tree (maximum nodes at any level)

---

## Algorithm Flow with Example

Let's trace through Example 1: `root = [1,7,0,7,-8,null,null]`

```
Tree Structure:
        1           (Level 1)
       / \
      7   0         (Level 2)
     / \
    7  -8           (Level 3)
```

### Step-by-Step Execution

**Initialization:**
- Queue: [1]
- maxSum: -∞
- resultLevel: 1
- currentLevel: 1

**Iteration 1 (Level 1):**
- levelSize = 1
- Process node 1: currentLevelSum = 1
- Add children: Queue = [7, 0]
- currentLevelSum (1) > maxSum (-∞) ✓
- Update: maxSum = 1, resultLevel = 1
- currentLevel = 2

**Iteration 2 (Level 2):**
- levelSize = 2 (capture before processing!)
- Process node 7: currentLevelSum = 7, add children [7, -8]
- Process node 0: currentLevelSum = 7 + 0 = 7
- Queue = [7, -8]
- currentLevelSum (7) > maxSum (1) ✓
- Update: maxSum = 7, resultLevel = 2
- currentLevel = 3

**Iteration 3 (Level 3):**
- levelSize = 2
- Process node 7: currentLevelSum = 7
- Process node -8: currentLevelSum = 7 + (-8) = -1
- Queue = []
- currentLevelSum (-1) > maxSum (7) ✗
- No update

**Final Result:** Return resultLevel = 2

---

## Key Concepts & Thinking Process

### 1. Why BFS over DFS?
BFS naturally processes nodes level by level, which aligns perfectly with the problem requirement. We can calculate level sums in a single pass.

### 2. The Critical Line: `int levelSize = queue.size()`
This line is crucial because:
- We capture the number of nodes at the current level BEFORE processing
- As we process nodes, we add their children to the queue
- By only processing `levelSize` nodes, we isolate the current level
- New children added during processing belong to the NEXT level

### 3. Handling Negative Values
Initialize `maxSum` to `Integer.MIN_VALUE` (not 0) because:
- All node values at a level could be negative
- We need to handle the case where the maximum sum is negative

### 4. Tie-Breaking Rule
Use `>` (not `>=`) in the comparison:
```java
if (currentLevelSum > maxSum)  // Correct
```
This ensures if two levels have the same sum, we keep the smaller level number.

---

## Common Pitfalls

1. ❌ **Not capturing queue size**: Processing `queue.size()` directly in the loop condition will cause issues as the size changes
2. ❌ **Initializing maxSum to 0**: Will fail when all sums are negative
3. ❌ **Using `>=` instead of `>`**: Will return the last occurrence instead of the first
4. ❌ **Forgetting null checks**: Always check if children exist before adding to queue

---

## Visual Representation

![Binary Tree Level Order Traversal](https://assets.leetcode.com/uploads/2021/03/09/sum-tree.jpg)

The BFS queue ensures we process one complete level before moving to the next:

```
Queue Evolution:
Initial:  [1]
Level 1:  [7, 0]              Sum: 1
Level 2:  [7, -8]             Sum: 7 ← Maximum!
Level 3:  []                  Sum: -1
```

---

## Interview Tips

1. **Clarify edge cases:** Ask about empty trees, single nodes, all negative values
2. **Discuss both approaches:** Mention DFS (brute force) then pivot to BFS (optimal)
3. **Explain the queue size trick:** Interviewers love when you explain why capturing the size matters
4. **Time/Space tradeoffs:** BFS uses O(w) space vs DFS uses O(h) space

---

## Related Problems
- LeetCode 102: Binary Tree Level Order Traversal
- LeetCode 107: Binary Tree Level Order Traversal II
- LeetCode 103: Binary Tree Zigzag Level Order Traversal
- LeetCode 2583: Kth Largest Sum in a Binary Tree

---

## Summary

This problem is a classic BFS application that tests your understanding of:
- Queue-based level-order traversal
- Processing nodes in batches (by level)
- Handling negative numbers and edge cases
- Writing clean, efficient code

The key insight is recognizing that BFS naturally solves level-based problems, and the critical technique is capturing the queue size to isolate each level during processing.
