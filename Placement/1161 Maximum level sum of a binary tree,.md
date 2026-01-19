# LeetCode 1161: Maximum Level Sum of a Binary Tree

## Problem Information
- **Problem Number:** 1161
- **Difficulty:** Medium
- **Problem Link:** [https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/](https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/)

## Problem Statement
Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on. Return the **smallest level x** such that the sum of all the values of nodes at level x is **maximal**.

### Examples

**Example 1:**
```
Input: root = [1,7,0,7,-8,null,null]
Output: 2

Explanation:
Level 1 sum = 1
Level 2 sum = 7 + 0 = 7
Level 3 sum = 7 + (-8) = -1
So we return level 2 (the maximum sum is 7)
```

**Example 2:**
```
Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
Output: 2
```

### Constraints
- The number of nodes in the tree is in the range `[1, 10⁴]`
- `-10⁵ <= Node.val <= 10⁵`

## Visual Representation

```
Example Tree:
        1              Level 1: sum = 1
       / \
      7   0            Level 2: sum = 7 + 0 = 7 (maximum!)
     / \
    7  -8              Level 3: sum = 7 + (-8) = -1
```

---

## Core Intuition & Key Insight

### The Fundamental Principle
This problem requires us to:
1. **Process the tree level by level** (not node by node)
2. **Calculate the sum** of all node values at each level
3. **Track which level has the maximum sum**
4. **Return the smallest level number** if there are ties

### Why Level Order Traversal (BFS)?
Level order traversal is the natural fit because:
- We need to process all nodes at a specific level together
- We need to keep track of which level we're currently on
- BFS naturally groups nodes by their depth/level

### Critical Detail: "Smallest Level"
If multiple levels have the same maximum sum, we return the **smallest** (earliest) level number. This means when we find a sum equal to the current max, we DON'T update our answer—only when we find a **strictly greater** sum.

---

## Solution 1: Naive/Brute Force Approach

### Algorithm
1. Use recursion to traverse the entire tree multiple times
2. For each level from 1 to tree height, calculate the sum
3. Track the maximum sum and corresponding level

### Code (Java)
```java
public class MaximumLevelSumBruteForce {
    public int maxLevelSum(TreeNode root) {
        int height = getHeight(root);
        int maxSum = Integer.MIN_VALUE;
        int resultLevel = 1;
        
        // For each level, calculate sum
        for (int level = 1; level <= height; level++) {
            int levelSum = getLevelSum(root, level);
            if (levelSum > maxSum) {
                maxSum = levelSum;
                resultLevel = level;
            }
        }
        
        return resultLevel;
    }
    
    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
    
    private int getLevelSum(TreeNode root, int level) {
        if (root == null) return 0;
        if (level == 1) return root.val;
        return getLevelSum(root.left, level - 1) + 
               getLevelSum(root.right, level - 1);
    }
}
```

### Complexity Analysis
- **Time Complexity:** O(n × h) where h is height - we traverse the tree multiple times
- **Space Complexity:** O(h) - recursion stack space

### Drawback
Very inefficient! We're visiting the same nodes multiple times unnecessarily.

---

## Solution 2: DFS with Level Tracking (Better)

### Algorithm
Use depth-first search to traverse the tree once, storing the sum for each level in an array.

### Code (Java)
```java
public class MaximumLevelSumDFS {
    public int maxLevelSum(TreeNode root) {
        List<Integer> levelSums = new ArrayList<>();
        dfs(root, 0, levelSums);
        
        int maxSum = Integer.MIN_VALUE;
        int resultLevel = 1;
        
        for (int i = 0; i < levelSums.size(); i++) {
            if (levelSums.get(i) > maxSum) {
                maxSum = levelSums.get(i);
                resultLevel = i + 1;  // Levels are 1-indexed
            }
        }
        
        return resultLevel;
    }
    
    private void dfs(TreeNode node, int level, List<Integer> levelSums) {
        if (node == null) return;
        
        // Create new level entry if needed
        if (level >= levelSums.size()) {
            levelSums.add(0);
        }
        
        // Add current node's value to its level sum
        levelSums.set(level, levelSums.get(level) + node.val);
        
        // Traverse children
        dfs(node.left, level + 1, levelSums);
        dfs(node.right, level + 1, levelSums);
    }
}
```

### Complexity Analysis
- **Time Complexity:** O(n) - visit each node once
- **Space Complexity:** O(h) - recursion stack + O(h) for level sums array

---

## Solution 3: BFS with Queue (Optimal & Most Intuitive)

### The Key Insight
BFS naturally processes nodes level by level using a queue. We can calculate the sum for each level in a single pass without needing extra storage for all level sums.

### Algorithm Flow
1. Initialize a queue with the root node
2. Track current level number (starting at 1)
3. For each level:
   - Capture the current queue size (nodes at this level)
   - Process exactly that many nodes
   - Calculate the sum of all values at this level
   - Add children to queue for next level
4. Update max sum and result level if current sum is greater

### Why This Works
The key trick is capturing `levelSize = queue.size()` BEFORE processing nodes. This ensures we only process nodes from the current level, not their children that get added during processing.

### Code (Java) - Your Solution
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
            // CRITICAL: Capture size before processing
            int levelSize = queue.size();
            int currentLevelSum = 0;

            // Process exactly 'levelSize' nodes (current level only)
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

            // Update if we found a strictly greater sum
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
- **Time Complexity:** O(n) - visit each node exactly once
- **Space Complexity:** O(w) - where w is maximum width of tree (max nodes at any level)
  - In worst case (complete tree), w = n/2, so O(n)

---

## Step-by-Step Example

Let's trace through the BFS solution with `root = [1,7,0,7,-8,null,null]`:

```
Initial Tree:
        1
       / \
      7   0
     / \
    7  -8

Initial State:
queue = [1]
currentLevel = 1
maxSum = -∞
resultLevel = 1
```

### Iteration 1 - Level 1:
```
levelSize = 1 (only root)
currentLevelSum = 0

Process node 1:
  - currentLevelSum += 1 → currentLevelSum = 1
  - Add left child (7) to queue
  - Add right child (0) to queue
  
queue = [7, 0]
currentLevelSum = 1
1 > -∞, so update:
  maxSum = 1
  resultLevel = 1
currentLevel = 2
```

### Iteration 2 - Level 2:
```
levelSize = 2 (nodes 7 and 0)
currentLevelSum = 0

Process node 7:
  - currentLevelSum += 7 → currentLevelSum = 7
  - Add left child (7) to queue
  - Add right child (-8) to queue
  
Process node 0:
  - currentLevelSum += 0 → currentLevelSum = 7
  - No children to add
  
queue = [7, -8]
currentLevelSum = 7
7 > 1, so update:
  maxSum = 7
  resultLevel = 2
currentLevel = 3
```

### Iteration 3 - Level 3:
```
levelSize = 2 (nodes 7 and -8)
currentLevelSum = 0

Process node 7:
  - currentLevelSum += 7 → currentLevelSum = 7
  - No children to add
  
Process node -8:
  - currentLevelSum += (-8) → currentLevelSum = -1
  - No children to add
  
queue = [] (empty)
currentLevelSum = -1
-1 is NOT > 7, so don't update
currentLevel = 4
```

### Final Result:
```
Return resultLevel = 2
```

---

## Detailed BFS Visualization

```
Queue Evolution:

Start:           [1]
After Level 1:   [7, 0]
After Level 2:   [7, -8]
After Level 3:   []

Level Sums:
Level 1: 1
Level 2: 7    ← Maximum!
Level 3: -1

Result: 2 (smallest level with maximum sum)
```

---

## Key Implementation Details

### 1. Why `levelSize = queue.size()` is Critical
```java
// WRONG - will process children added during this iteration
while (!queue.isEmpty()) {
    TreeNode node = queue.poll();
    // This processes ALL nodes, not level by level!
}

// CORRECT - captures size before processing
int levelSize = queue.size();
for (int i = 0; i < levelSize; i++) {
    // Only processes nodes from current level
}
```

### 2. Why Use `> maxSum` (not `>= maxSum`)
```java
// CORRECT: Only update for strictly greater sums
if (currentLevelSum > maxSum) {
    resultLevel = currentLevel;
}

// WRONG: Would update for equal sums, returning larger level
if (currentLevelSum >= maxSum) {
    resultLevel = currentLevel;  // Violates "smallest level" requirement
}
```

### 3. Handling Edge Cases
```java
// Empty tree
if (root == null) return 0;

// Single node - it's level 1 by default
// Negative values - use Integer.MIN_VALUE as initial maxSum
int maxSum = Integer.MIN_VALUE;  // Not 0!
```

---

## Thinking Framework

### When to Use Each Approach

1. **Brute Force (Recursive per level):**
   - Quick to code in an interview to show understanding
   - Not recommended for actual use
   - Good for explaining the problem

2. **DFS with Level Tracking:**
   - When you're more comfortable with recursion
   - Slightly more space efficient (no queue)
   - Good for problems requiring level information

3. **BFS with Queue (Recommended):**
   - Most intuitive for level-based problems
   - Standard approach for level order traversal
   - Easiest to explain and understand
   - Best for interviews

### Common Mistakes to Avoid

1. **Not capturing queue size before processing**
   - Results in mixing nodes from different levels

2. **Using `>=` instead of `>` for comparison**
   - Violates the "smallest level" requirement

3. **Initializing maxSum to 0**
   - Fails when all node values are negative
   - Always use `Integer.MIN_VALUE`

4. **Forgetting null checks**
   - Check if children exist before adding to queue

5. **Off-by-one errors with level numbering**
   - Remember: root is level 1 (not 0)

---

## Related Problems

- **Binary Tree Level Order Traversal (LeetCode 102)** - Foundation for this problem
- **Binary Tree Zigzag Level Order Traversal (LeetCode 103)** - Similar BFS pattern
- **Minimum Depth of Binary Tree (LeetCode 111)** - BFS to find closest leaf
- **Maximum Depth of Binary Tree (LeetCode 104)** - Can use BFS or DFS
- **Binary Tree Right Side View (LeetCode 199)** - Level order with specific nodes
- **Kth Largest Sum in a Binary Tree (LeetCode 2583)** - Extension of this problem

---

## Key Takeaways

1. **BFS is ideal for level-based problems** - Natural grouping by depth
2. **Queue size capture is the key trick** - Isolates current level from next
3. **Integer.MIN_VALUE for negative values** - Always safe for max problems
4. **Strict inequality for "smallest" requirement** - `>` not `>=`
5. **O(n) time, O(w) space** - Efficient single-pass solution

---

## BFS Template for Level Order Problems

This template works for many level-based binary tree problems:

```java
public void levelOrderTemplate(TreeNode root) {
    if (root == null) return;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int level = 1;
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        
        // Process all nodes at current level
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            
            // Process current node
            // [Your logic here]
            
            // Add children for next level
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        
        level++;
    }
}
```
