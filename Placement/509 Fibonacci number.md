# LeetCode 509: Fibonacci Number

## Problem Information

**Difficulty:** Easy  
**LeetCode Number:** 509  
**Problem Link:** [https://leetcode.com/problems/fibonacci-number/](https://leetcode.com/problems/fibonacci-number/)

---

## Problem Statement

The Fibonacci numbers form a sequence where each number is the sum of the two preceding ones, starting from 0 and 1.

**Mathematical Definition:**
```
F(0) = 0
F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1
```

**Task:** Given an integer `n`, calculate and return `F(n)`.

---

## Examples

### Example 1
```
Input: n = 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1
```

### Example 2
```
Input: n = 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2
```

### Example 3
```
Input: n = 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3
```

### Example 4
```
Input: n = 10
Output: 55
Explanation: Sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55
```

---

## Constraints

```
0 <= n <= 30
```

---

## Core Idea & Intuition

### 💡 Key Insight

The Fibonacci sequence is a classic example of **overlapping subproblems** - the same calculations are repeated multiple times when using naive recursion. This makes it perfect for demonstrating dynamic programming concepts.

### Intuition

Think of building the Fibonacci sequence like climbing stairs:
- At position 0, you have 0 ways (F(0) = 0)
- At position 1, you have 1 way (F(1) = 1)
- At any position n, you can arrive from either position (n-1) or (n-2)
- The total ways to reach position n is the sum of ways to reach the two previous positions

**The Problem with Naive Recursion:**
If we calculate F(5), we need F(4) and F(3).
- F(4) needs F(3) and F(2)
- F(3) needs F(2) and F(1)
- Notice F(3) and F(2) are calculated multiple times!

This redundancy grows exponentially, making naive recursion extremely slow.

---

## Visual Representation

### Fibonacci Sequence
```
Position:  0   1   2   3   4   5   6   7   8   9   10
Value:     0   1   1   2   3   5   8   13  21  34  55
           │   │   └───┴──→ 1+0=1
           │   └───────────┴──→ 1+1=2
           └───────────────────┴──→ 2+1=3
```

### Recursion Tree for F(5) - Naive Approach
```
                    F(5)
                   /    \
               F(4)      F(3)
              /   \      /   \
          F(3)   F(2)  F(2)  F(1)
         /  \    /  \   /  \
      F(2) F(1) F(1) F(0) F(1) F(0)
      /  \
   F(1) F(0)
```
**Problem:** Notice how F(3), F(2), F(1), F(0) are calculated multiple times!

---

## Solutions

### Approach 1: Naive Recursion (Brute Force)

**Time Complexity:** O(2^n) - Exponential  
**Space Complexity:** O(n) - Recursion stack depth

**Concept:**
Directly translate the mathematical definition into code without optimization.

**Why It's Slow:**
Each function call branches into two more calls, creating an exponential number of operations. For F(30), this would result in over 1 billion function calls!

**Java Implementation:**

```java
class Solution {
    public int fib(int n) {
        // Base cases
        if (n <= 1) {
            return n;
        }
        
        // Recursive case: F(n) = F(n-1) + F(n-2)
        return fib(n - 1) + fib(n - 2);
    }
}
```

**Execution Trace for F(4):**
```
fib(4)
├─ fib(3)
│  ├─ fib(2)
│  │  ├─ fib(1) → 1
│  │  └─ fib(0) → 0
│  │  Result: 1
│  └─ fib(1) → 1
│  Result: 2
└─ fib(2)
   ├─ fib(1) → 1
   └─ fib(0) → 0
   Result: 1
Final Result: 3

Total function calls: 9
```

**Problems:**
- ❌ Recalculates same values multiple times
- ❌ Extremely slow for large n (n > 20)
- ❌ Stack overflow risk for very large n

---

### Approach 2: Memoization (Top-Down Dynamic Programming)

**Time Complexity:** O(n) - Each value calculated once  
**Space Complexity:** O(n) - Memoization array + recursion stack

**Concept:**
Store previously calculated values in a cache (memo array) to avoid redundant calculations.

**Key Improvement:**
When we need F(n), we first check if it's already calculated. If yes, return it immediately. If no, calculate it, store it, then return it.

**Java Implementation:**

```java
public class FibonacciMemoization {
    private int[] memo;
    
    public FibonacciMemoization(int n) {
        memo = new int[n + 1];
        // Initialize with -1 to indicate "not calculated yet"
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
    }
    
    public int fibonacci(int n) {
        // Base cases
        if (n <= 1) {
            return n;
        }
        
        // Check if already calculated
        if (memo[n] != -1) {
            return memo[n];
        }
        
        // Calculate and store in memo
        memo[n] = fibonacci(n - 1) + fibonacci(n - 2);
        return memo[n];
    }
    
    public static void main(String[] args) {
        int n = 10;
        FibonacciMemoization fib = new FibonacciMemoization(n);
        System.out.println("Fibonacci number at position " + n + " is: " + fib.fibonacci(n));
        // Output: Fibonacci number at position 10 is: 55
    }
}
```

**Alternative: Using Map for Memoization**

```java
class Solution {
    private Map<Integer, Integer> memo = new HashMap<>();
    
    public int fib(int n) {
        // Base cases
        if (n <= 1) {
            return n;
        }
        
        // Check memo
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        // Calculate and memoize
        int result = fib(n - 1) + fib(n - 2);
        memo.put(n, result);
        return result;
    }
}
```

**Execution Trace for F(5) with Memoization:**
```
Call Stack and Memo State:

fib(5)
├─ fib(4)
│  ├─ fib(3)
│  │  ├─ fib(2)
│  │  │  ├─ fib(1) → 1 (base)
│  │  │  └─ fib(0) → 0 (base)
│  │  │  memo[2] = 1 ✓
│  │  └─ fib(1) → 1 (base)
│  │  memo[3] = 2 ✓
│  └─ fib(2) → 1 (from memo!) ✅
│  memo[4] = 3 ✓
└─ fib(3) → 2 (from memo!) ✅
memo[5] = 5 ✓

Total unique function calls: 6 (vs 15 for naive)
Total operations: 6 (vs 15 for naive)
```

**Advantages:**
- ✅ Each Fibonacci number calculated only once
- ✅ Linear time complexity
- ✅ Easy to understand and implement
- ✅ Good for recursive problems

**Disadvantages:**
- ❌ Uses extra space for memoization array
- ❌ Still uses recursion stack space
- ❌ Slightly more complex than iterative approach

---

### Approach 3: Tabulation (Bottom-Up Dynamic Programming)

**Time Complexity:** O(n) - Single loop  
**Space Complexity:** O(n) - DP array

**Concept:**
Build the solution from the bottom up by filling a table (array) iteratively, starting from the base cases.

**Java Implementation:**

```java
class Solution {
    public int fib(int n) {
        // Handle base cases
        if (n <= 1) {
            return n;
        }
        
        // Create DP array
        int[] dp = new int[n + 1];
        
        // Initialize base cases
        dp[0] = 0;
        dp[1] = 1;
        
        // Fill table bottom-up
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
}
```

**Execution Trace for F(6):**
```
Initial: dp = [0, 1, ?, ?, ?, ?, ?]
                0  1  2  3  4  5  6

i=2: dp[2] = dp[1] + dp[0] = 1 + 0 = 1
     dp = [0, 1, 1, ?, ?, ?, ?]

i=3: dp[3] = dp[2] + dp[1] = 1 + 1 = 2
     dp = [0, 1, 1, 2, ?, ?, ?]

i=4: dp[4] = dp[3] + dp[2] = 2 + 1 = 3
     dp = [0, 1, 1, 2, 3, ?, ?]

i=5: dp[5] = dp[4] + dp[3] = 3 + 2 = 5
     dp = [0, 1, 1, 2, 3, 5, ?]

i=6: dp[6] = dp[5] + dp[4] = 5 + 3 = 8
     dp = [0, 1, 1, 2, 3, 5, 8]

Return: dp[6] = 8
```

**Advantages:**
- ✅ No recursion (no stack overflow risk)
- ✅ Simple iterative logic
- ✅ Predictable performance

**Disadvantages:**
- ❌ Uses O(n) space for entire array
- ❌ Calculates all values even if not needed

---

### Approach 4: Space-Optimized Iterative (Optimal)

**Time Complexity:** O(n) - Single loop  
**Space Complexity:** O(1) - Only two variables

**Concept:**
We only need the last two Fibonacci numbers to calculate the next one. No need to store the entire sequence!

**Java Implementation:**

```java
class Solution {
    public int fib(int n) {
        // Base cases
        if (n <= 1) {
            return n;
        }
        
        // Only keep track of last two numbers
        int prev = 0;  // F(0)
        int curr = 1;  // F(1)
        
        // Calculate from 2 to n
        for (int i = 2; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        
        return curr;
    }
}
```

**Execution Trace for F(7):**
```
Initial: prev=0, curr=1

i=2: next = 0+1=1,  prev=1, curr=1
i=3: next = 1+1=2,  prev=1, curr=2
i=4: next = 1+2=3,  prev=2, curr=3
i=5: next = 2+3=5,  prev=3, curr=5
i=6: next = 3+5=8,  prev=5, curr=8
i=7: next = 5+8=13, prev=8, curr=13

Return: curr = 13
```

**Visual Sliding Window:**
```
Position: 0   1   2   3   4   5   6   7
Value:    0   1   1   2   3   5   8   13

Step 1:  [0   1]  → next = 1
Step 2:      [1   1]  → next = 2
Step 3:          [1   2]  → next = 3
Step 4:              [2   3]  → next = 5
Step 5:                  [3   5]  → next = 8
Step 6:                      [5   8]  → next = 13
```

**Advantages:**
- ✅ Optimal time and space complexity
- ✅ No recursion overhead
- ✅ Memory efficient
- ✅ Simple and clean code

**This is the BEST solution for this problem!**

---

## Detailed Algorithm Comparison

### Performance Comparison Table

| Approach | Time | Space | Recursion | Best For |
|----------|------|-------|-----------|----------|
| Naive Recursion | O(2^n) | O(n) | Yes | Understanding concept |
| Memoization | O(n) | O(n) | Yes | Learning DP |
| Tabulation | O(n) | O(n) | No | Medium-sized problems |
| Space-Optimized | O(n) | O(1) | No | **Production code** |

### Function Calls Comparison for F(10)

```
Naive Recursion:    177 calls
Memoization:        11 calls
Tabulation:         1 call (9 iterations)
Space-Optimized:    1 call (9 iterations)
```

---

## Step-by-Step Example: Computing F(6)

### Using Space-Optimized Approach

```
Goal: Find F(6)
Sequence: 0, 1, 1, 2, 3, 5, 8
                          ↑
                       F(6) = 8

Step-by-step:
┌──────┬──────┬──────┬──────┬─────────────────────┐
│  i   │ prev │ curr │ next │      Action         │
├──────┼──────┼──────┼──────┼─────────────────────┤
│ Init │  0   │  1   │  -   │ Base values         │
│  2   │  0   │  1   │  1   │ 0+1=1, slide window │
│  3   │  1   │  1   │  2   │ 1+1=2, slide window │
│  4   │  1   │  2   │  3   │ 1+2=3, slide window │
│  5   │  2   │  3   │  5   │ 2+3=5, slide window │
│  6   │  3   │  5   │  8   │ 3+5=8, slide window │
└──────┴──────┴──────┴──────┴─────────────────────┘

Result: curr = 8
```

---

## Common Mistakes & Edge Cases

### Edge Cases to Consider

1. **n = 0**
   - Input: 0
   - Output: 0
   - First Fibonacci number

2. **n = 1**
   - Input: 1
   - Output: 1
   - Second Fibonacci number

3. **n = 2**
   - Input: 2
   - Output: 1
   - First computed value

4. **Maximum constraint**
   - Input: 30
   - Output: 832040
   - Test performance limits

### Common Mistakes

❌ **Mistake 1:** Starting loop from i=0 or i=1 instead of i=2
```java
// Wrong
for (int i = 0; i <= n; i++)  // Will overwrite base cases!

// Correct
for (int i = 2; i <= n; i++)
```

❌ **Mistake 2:** Not handling base cases
```java
// Wrong - will cause ArrayIndexOutOfBounds for n=0,1
int[] dp = new int[n + 1];
for (int i = 2; i <= n; i++) {
    dp[i] = dp[i-1] + dp[i-2];
}

// Correct
if (n <= 1) return n;
```

❌ **Mistake 3:** Array size too small
```java
// Wrong - IndexOutOfBounds when accessing dp[n]
int[] dp = new int[n];  

// Correct
int[] dp = new int[n + 1];
```

❌ **Mistake 4:** Forgetting to update variables in space-optimized
```java
// Wrong - infinite loop with same values
int next = prev + curr;
// forgot: prev = curr; curr = next;

// Correct
int next = prev + curr;
prev = curr;
curr = next;
```

---

## Why Different Approaches?

### When to Use Each Approach

**1. Naive Recursion:**
- Teaching/Learning recursion concepts
- Very small inputs (n < 10)
- Understanding the problem structure

**2. Memoization:**
- Learning dynamic programming
- When you need to cache results for multiple queries
- Problems with complex recursive structures

**3. Tabulation:**
- Need iterative solution
- Don't want recursion overhead
- Medium space constraints acceptable

**4. Space-Optimized:**
- Production code
- Performance critical applications
- Strict memory constraints
- **Always prefer this for Fibonacci!**

---

## Related Problems

### Similar LeetCode Problems

1. **[LeetCode 70: Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)**
   - Identical pattern to Fibonacci
   - Same recurrence relation

2. **[LeetCode 1137: N-th Tribonacci Number](https://leetcode.com/problems/n-th-tribonacci-number/)**
   - Extension: sum of three previous numbers
   - Same optimization techniques apply

3. **[LeetCode 746: Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs/)**
   - Dynamic programming with Fibonacci pattern
   - Adds cost consideration

4. **[LeetCode 198: House Robber](https://leetcode.com/problems/house-robber/)**
   - Similar DP pattern
   - More complex state transitions

---

## Advanced: Matrix Exponentiation (Bonus)

For those interested, there's an O(log n) solution using matrix exponentiation:

**Concept:**
```
[F(n+1)]   [1 1]^n   [1]
[F(n)  ] = [1 0]   × [0]
```

**Time Complexity:** O(log n)  
**Space Complexity:** O(1)

This is useful when n is extremely large (n > 10^6), but for the constraint 0 ≤ n ≤ 30, the space-optimized iterative approach is best.

---

## Practice Tips

### How to Master This Problem

1. **Start with recursion** - Understand the base cases and recursive relation
2. **Add memoization** - Learn to identify and cache repeated calculations
3. **Convert to iteration** - Practice bottom-up thinking
4. **Optimize space** - Identify what data is actually needed
5. **Test edge cases** - Always check n=0, n=1, and n=2

### Coding Interview Tips

- Default to **space-optimized iterative** in interviews
- Explain the naive approach first to show understanding
- Mention that memoization and tabulation are alternatives
- Discuss time/space tradeoffs
- Handle edge cases explicitly

---

## Summary

| Aspect | Best Approach |
|--------|---------------|
| **Algorithm** | Space-Optimized Iterative |
| **Time Complexity** | O(n) |
| **Space Complexity** | O(1) |
| **Key Insight** | Only need last two values |
| **Difficulty** | Easy |
| **Pattern** | Dynamic Programming, Sequence |

### Key Takeaways

✅ Fibonacci is a classic DP problem with overlapping subproblems  
✅ Memoization converts O(2^n) to O(n) by caching results  
✅ Tabulation builds solution bottom-up iteratively  
✅ Space optimization reduces O(n) space to O(1)  
✅ Always handle base cases: F(0)=0, F(1)=1  

The Fibonacci problem is an excellent introduction to dynamic programming and demonstrates how different optimization techniques can dramatically improve performance!
