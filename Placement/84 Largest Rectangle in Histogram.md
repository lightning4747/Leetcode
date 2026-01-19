# LeetCode 84: Largest Rectangle in Histogram

## Problem Information

- **LeetCode Number**: 84
- **Difficulty**: Hard
- **Problem Link**: [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/)
- **Topics**: Array, Stack, Monotonic Stack

## Problem Statement

Given an array of integers `heights` representing the histogram's bar height where the width of each bar is `1`, return the area of the largest rectangle in the histogram.

## Examples

**Example 1:**

![Histogram Example](https://assets.leetcode.com/uploads/2021/01/04/histogram.jpg)

```
Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
```

**Example 2:**

```
Input: heights = [2,4]
Output: 4
Explanation: The largest rectangle spans both bars with height 2, giving area = 2 × 2 = 4.
```

## Constraints

- `1 <= heights.length <= 10^5`
- `0 <= heights[i] <= 10^4`

## Core Idea & Intuition

### The Key Insight

For each bar in the histogram, if we can determine:
1. **How far left** we can extend (until we hit a shorter bar)
2. **How far right** we can extend (until we hit a shorter bar)

Then the maximum rectangle using that bar's height would be:
```
Area = height × width
where width = (right_boundary - left_boundary - 1)
```

### Why Monotonic Stack?

The **monotonic stack** is perfect for this problem because:
- It helps us find the **nearest smaller element** on both sides efficiently
- We maintain an **increasing stack** of indices
- When we encounter a shorter bar, we know we've found the right boundary for all taller bars in the stack

### The Brilliant Observation

**Key Concept**: When we encounter a bar shorter than the bar at the stack's top:
- The top bar **cannot extend** beyond the current position (right boundary found)
- The bar below the top in the stack is the **first shorter bar on the left** (left boundary)
- We can now calculate the maximum area using this bar's height

This allows us to solve the problem in **one pass** with O(n) time complexity!

## Solutions

### Approach 1: Brute Force (Expand Around Each Bar)

**Concept**: For each bar, expand left and right to find the maximum width at that height.

```java
public class Solution {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        int n = heights.length;
        
        // For each bar, consider it as the height of rectangle
        for (int i = 0; i < n; i++) {
            int minHeight = heights[i];
            
            // Try extending to the right
            for (int j = i; j < n; j++) {
                // Update minimum height in current range
                minHeight = Math.min(minHeight, heights[j]);
                
                // Calculate area with current range and minimum height
                int width = j - i + 1;
                int area = minHeight * width;
                maxArea = Math.max(maxArea, area);
            }
        }
        
        return maxArea;
    }
}
```

**Time Complexity**: O(n²) - nested loops checking all ranges

**Space Complexity**: O(1) - only using a few variables

**Why it's inefficient**: We recalculate the same ranges multiple times and don't leverage the structure of the problem.

### Approach 2: Monotonic Stack (Optimized - One Pass)

**Concept**: Use a stack to maintain increasing heights and calculate areas when we find boundaries.

```java
import java.util.Stack;

public class LargestRectangleInHistogram {
    
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();  // Stack stores indices
        
        // Process each bar (plus one extra iteration with height 0)
        for (int i = 0; i <= n; i++) {
            // Use height 0 at the end to empty the stack
            int currentHeight = (i == n) ? 0 : heights[i];
            
            // While current bar is shorter than stack top
            // We found the right boundary for bars in stack
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                // Pop the bar whose right boundary we just found
                int height = heights[stack.pop()];
                
                // Calculate width:
                // - If stack is empty: the popped bar can extend from 0 to i-1
                // - Otherwise: it can extend from (stack.peek() + 1) to (i - 1)
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                
                // Calculate area and update maximum
                maxArea = Math.max(maxArea, height * width);
            }
            
            // Push current index onto stack
            stack.push(i);
        }
        
        return maxArea;
    }
}
```

**Time Complexity**: O(n) - each element is pushed and popped at most once

**Space Complexity**: O(n) - stack can hold up to n elements

**Why it's efficient**: Single pass through the array, leveraging the stack to avoid redundant calculations.

## Algorithm Flow with Example

Let's trace through **Example 1**: `heights = [2, 1, 5, 6, 2, 3]`

### Visual Representation of Histogram

```
Height
  6 |           ▓
  5 |       ▓   ▓
  4 |       ▓   ▓
  3 |       ▓   ▓   ▓
  2 | ▓     ▓   ▓   ▓
  1 | ▓ ▓   ▓   ▓   ▓
  0 |___________________
      0 1   2   3   4   5  (indices)
```

### Step-by-Step Execution

| i | height | Stack Before | Action | Width Calculation | Area | maxArea | Stack After |
|---|--------|-------------|--------|-------------------|------|---------|-------------|
| 0 | 2 | [] | Push 0 | - | - | 0 | [0] |
| 1 | 1 | [0] | Pop 0: h=2 | w = 1 | 2×1=2 | 2 | [1] |
|   |   | [] | Push 1 | - | - | 2 | [1] |
| 2 | 5 | [1] | Push 2 | - | - | 2 | [1,2] |
| 3 | 6 | [1,2] | Push 3 | - | - | 2 | [1,2,3] |
| 4 | 2 | [1,2,3] | Pop 3: h=6 | w = 4-2-1 = 1 | 6×1=6 | 6 | [1,2] |
|   |   | [1,2] | Pop 2: h=5 | w = 4-1-1 = 2 | 5×2=10 | **10** | [1] |
|   |   | [1] | Push 4 | - | - | 10 | [1,4] |
| 5 | 3 | [1,4] | Push 5 | - | - | 10 | [1,4,5] |
| 6 | 0 | [1,4,5] | Pop 5: h=3 | w = 6-4-1 = 1 | 3×1=3 | 10 | [1,4] |
|   |   | [1,4] | Pop 4: h=2 | w = 6-1-1 = 4 | 2×4=8 | 10 | [1] |
|   |   | [1] | Pop 1: h=1 | w = 6 (empty) | 1×6=6 | 10 | [] |

**Final Answer**: maxArea = **10** ✓

### Why Area is 10?

The largest rectangle is formed by bars at indices 2 and 3:
- Height: min(5, 6) = 5
- Width: 2 bars
- Area: 5 × 2 = **10**

## Visual Representation of Stack Behavior

```
Processing heights = [2, 1, 5, 6, 2, 3]

i=0 (h=2): Stack=[0]           |██|
                               |██|

i=1 (h=1): Stack=[1]           |  |
                               |██|
           (popped 0, calculated area 2×1=2)

i=2 (h=5): Stack=[1,2]         |  |██████|
                               |██|██████|

i=3 (h=6): Stack=[1,2,3]       |  |██████|████████|
                               |██|██████|████████|

i=4 (h=2): Stack=[1,4]         |  |      |  |
                               |██|      |██|
           (popped 3→area=6, popped 2→area=10 ✓)

i=5 (h=3): Stack=[1,4,5]       |  |      |  |██|
                               |██|      |██|██|

i=6 (h=0): Stack=[]
           (empty stack, all remaining bars processed)
```

## Why We Add a Bar of Height 0 at the End

The trick `int h = (i == n) ? 0 : heights[i];` ensures:
- All remaining bars in the stack get processed
- By using height 0, we force all bars to be popped
- This handles the case where the histogram ends with increasing heights

**Without this trick**: Bars remaining in the stack wouldn't be processed!

## How Width is Calculated

The width calculation is the tricky part:

```java
int width = stack.isEmpty() ? i : i - stack.peek() - 1;
```

**Case 1: Stack is empty**
- The popped bar can extend all the way from index 0 to i-1
- Width = i (from 0 to i-1, inclusive)

**Case 2: Stack is not empty**
- The popped bar is bounded by:
  - **Left boundary**: stack.peek() (next shorter bar on the left)
  - **Right boundary**: i (current position, shorter bar on the right)
- Width = i - stack.peek() - 1

**Example**: When we pop index 2 (height 5) at i=4:
- stack.peek() = 1
- Width = 4 - 1 - 1 = 2 (bars at indices 2 and 3)

## Edge Cases

1. **All bars same height**: 
   - Example: `heights = [2,2,2,2]` → Output: 8 (2 × 4)

2. **Strictly increasing heights**:
   - Example: `heights = [1,2,3,4,5]` → Output: 9 (3 × 3)
   - Stack fills up, then empties at the end

3. **Strictly decreasing heights**:
   - Example: `heights = [5,4,3,2,1]` → Output: 9 (3 × 3)
   - Each bar gets processed immediately

4. **Single bar**:
   - Example: `heights = [5]` → Output: 5

5. **Empty or zero heights**:
   - Example: `heights = [0,0,0]` → Output: 0

## Key Takeaways

1. **Monotonic Stack Pattern**: Keep stack in increasing order to find boundaries efficiently

2. **Index Storage**: Store indices (not heights) to calculate widths easily

3. **Boundary Finding**: When we pop, we've found both left and right boundaries

4. **One Pass Solution**: Each element pushed and popped exactly once → O(n)

5. **Width Formula**: `i - stack.peek() - 1` gives the width between boundaries

6. **End Sentinel**: Adding height 0 at the end ensures all bars are processed

## Common Mistakes to Avoid

❌ **Storing heights instead of indices**: Makes width calculation difficult

❌ **Forgetting the end sentinel**: Bars at the end won't be processed

❌ **Wrong width calculation**: Must account for the bar at stack.peek()

❌ **Not handling empty stack**: When stack is empty, bar extends from start

✅ **Use monotonic stack**: Maintains increasing order for efficient boundary detection

## Practice Tips

- Understand why we maintain an **increasing** stack
- Draw the stack state at each step for small examples
- Practice the width calculation formula until it's intuitive
- Remember: this is a **classic monotonic stack problem**

## Similar Problems

- LeetCode 85: Maximal Rectangle
- LeetCode 42: Trapping Rain Water
- LeetCode 496: Next Greater Element I
- LeetCode 739: Daily Temperatures
- LeetCode 901: Online Stock Span

## Complexity Summary

| Approach | Time Complexity | Space Complexity |
|----------|----------------|------------------|
| Brute Force (Expand Each) | O(n²) | O(1) |
| Monotonic Stack | O(n) | O(n) |

## Visual Algorithm Summary

```
Monotonic Stack Approach:

1. Initialize: stack = [], maxArea = 0

2. For each bar (including sentinel at end):
   - While current height < stack top height:
     * Pop the stack
     * Calculate area using popped height
     * Update maxArea
   - Push current index

3. Return maxArea

Key Insight:
  When we pop index j from stack:
  - Left boundary: stack.peek() 
  - Right boundary: current index i
  - Width: i - stack.peek() - 1
  - Area: heights[j] × width
```

---

