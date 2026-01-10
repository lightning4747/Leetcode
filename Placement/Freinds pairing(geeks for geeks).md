# Friends Pairing Problem

## Problem Information

**Platform:** GeeksforGeeks  
**Difficulty:** Medium  
**Problem Link:** [https://www.geeksforgeeks.org/problems/friends-pairing-problem5425/1](https://www.geeksforgeeks.org/problems/friends-pairing-problem5425/1)  
**Article:** [https://www.geeksforgeeks.org/friends-pairing-problem/](https://www.geeksforgeeks.org/friends-pairing-problem/)

---

## Problem Statement

Given **n friends**, each friend can either:
1. **Remain single**, or
2. **Be paired up** with exactly one other friend

Each friend can be paired **only once**. Find the **total number of ways** in which friends can remain single or be paired up.

**Note:** The order of pairing doesn't matter. For example, {1, 2} and {2, 1} are considered the same pairing.

---

## Examples

### Example 1
```
Input: n = 3
Output: 4
Explanation:
Friends are {1, 2, 3}
Possible arrangements:
1. {1}, {2}, {3}       → All single
2. {1}, {2, 3}         → 2 and 3 paired, 1 single
3. {1, 2}, {3}         → 1 and 2 paired, 3 single
4. {1, 3}, {2}         → 1 and 3 paired, 2 single
```

### Example 2
```
Input: n = 4
Output: 10
Explanation:
Friends are {1, 2, 3, 4}
Possible arrangements:
1. {1}, {2}, {3}, {4}          → All single
2. {1}, {2}, {3, 4}            → One pair
3. {1}, {2, 3}, {4}            → One pair
4. {1}, {2, 4}, {3}            → One pair
5. {1, 2}, {3}, {4}            → One pair
6. {1, 3}, {2}, {4}            → One pair
7. {1, 4}, {2}, {3}            → One pair
8. {1, 2}, {3, 4}              → Two pairs
9. {1, 3}, {2, 4}              → Two pairs
10. {1, 4}, {2, 3}             → Two pairs
```

### Example 3
```
Input: n = 2
Output: 2
Explanation:
1. {1}, {2}     → Both single
2. {1, 2}       → Paired together
```

---

## Constraints

```
1 ≤ n ≤ 100
Answer should be returned modulo 10^9 + 7
```

---

## Core Idea & Intuition

### 💡 Key Insight

This problem is about **counting combinations** where each person either stays alone or pairs with someone else. It's similar to the Fibonacci problem but with a twist!

### Intuition: The nth Person's Choice

Consider the **nth friend**. This person has two choices:

**Choice 1: Stay Single**
- If the nth person stays single, we need to find arrangements for the remaining (n-1) friends
- Number of ways = `f(n-1)`

**Choice 2: Pair Up**
- The nth person can pair with any of the remaining (n-1) friends
- Once paired, those 2 people are "taken care of"
- We need to find arrangements for the remaining (n-2) friends
- Number of ways = `(n-1) × f(n-2)`

**Recurrence Relation:**
```
f(n) = f(n-1) + (n-1) × f(n-2)
       └─────┘   └────────────┘
       Single      Paired up
```

**Base Cases:**
```
f(0) = 1  (no friends, one way: do nothing)
f(1) = 1  (one friend stays single)
f(2) = 2  (both single OR paired together)
```

### Why It Works

Think of it like building a sequence from left to right:
- When we reach person n, we decide: "Should they stay alone or pair with someone?"
- Each decision leads to a smaller subproblem
- We add up all possibilities

---

## Visual Representation

### Decision Tree for n=4

```
                        f(4)
                    /            \
            Person 4 Single    Person 4 Pairs
                /                    \
            f(3)                  3 choices × f(2)
           /    \                    /    |    \
      Single  Pairs            Pair with Pair with Pair with
        /       \              friend 1  friend 2  friend 3
     f(2)    2×f(1)               |         |         |
     /  \       |                f(2)      f(2)      f(2)
   ...  ...    ...
```

### Sequence Building

```
n = 0:  []                                    → 1 way
n = 1:  [1]                                   → 1 way
n = 2:  [1, 2]  or  [(1,2)]                   → 2 ways
n = 3:  [1, 2, 3]                             → 4 ways
        [1, (2,3)]
        [(1,2), 3]
        [(1,3), 2]
n = 4:  [1, 2, 3, 4]                          → 10 ways
        [1, 2, (3,4)]
        [1, (2,3), 4]
        [1, (2,4), 3]
        [(1,2), 3, 4]
        [(1,3), 2, 4]
        [(1,4), 2, 3]
        [(1,2), (3,4)]
        [(1,3), (2,4)]
        [(1,4), (2,3)]
```

---

## Solutions

### Approach 1: Naive Recursion (Brute Force)

**Time Complexity:** O(2^n) - Exponential  
**Space Complexity:** O(n) - Recursion stack depth

**Concept:**
Directly implement the recurrence relation without optimization. Each call branches into multiple recursive calls, leading to exponential time complexity due to overlapping subproblems.

**Why It's Slow:**
The same subproblems are calculated multiple times. For example, when calculating f(5), we calculate f(3) multiple times through different paths.

**Java Implementation:**

```java
class Solution {
    static final int MOD = 1000000007;
    
    public long countFriendsPairings(int n) {
        // Base cases
        if (n <= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        
        // Recursive case: f(n) = f(n-1) + (n-1) * f(n-2)
        long staysSingle = countFriendsPairings(n - 1);
        long getPaired = ((n - 1) * countFriendsPairings(n - 2)) % MOD;
        
        return (staysSingle + getPaired) % MOD;
    }
}
```

**Execution Trace for n=4:**
```
countFriendsPairings(4)
├── countFriendsPairings(3)
│   ├── countFriendsPairings(2) → 2
│   └── 2 × countFriendsPairings(1) → 2 × 1 = 2
│   Result: 2 + 2 = 4
└── 3 × countFriendsPairings(2) → 3 × 2 = 6
Result: 4 + 6 = 10

Total function calls: 9+
```

**Problems with Naive Approach:**
- ❌ Recalculates same values multiple times
- ❌ Exponential time complexity
- ❌ Stack overflow for large n
- ❌ Very slow for n > 20

---

### Approach 2: Memoization (Top-Down Dynamic Programming)

**Time Complexity:** O(n) - Each value calculated once  
**Space Complexity:** O(n) - Memoization array + recursion stack

**Concept:**
Cache previously calculated results to avoid redundant computations. When we need f(n), we first check if it's already computed.

**Java Implementation:**

```java
class Solution {
    static final int MOD = 1000000007;
    private long[] memo;
    
    public long countFriendsPairings(int n) {
        memo = new long[n + 1];
        // Initialize with -1 to indicate "not calculated"
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        return countHelper(n);
    }
    
    private long countHelper(int n) {
        // Base cases
        if (n <= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        
        // Check if already calculated
        if (memo[n] != -1) {
            return memo[n];
        }
        
        // Calculate and store in memo
        long staysSingle = countHelper(n - 1) % MOD;
        long getPaired = ((n - 1) * countHelper(n - 2)) % MOD;
        
        memo[n] = (staysSingle + getPaired) % MOD;
        return memo[n];
    }
}
```

**Execution Trace with Memoization for n=5:**
```
Call Stack and Memo State:

countHelper(5)
├── countHelper(4)
│   ├── countHelper(3)
│   │   ├── countHelper(2) → 2 (base)
│   │   └── 2 × countHelper(1) → 2 × 1 = 2 (base)
│   │   memo[3] = 4 ✓
│   └── 3 × countHelper(2) → 3 × 2 = 6
│   memo[4] = 10 ✓
└── 4 × countHelper(3) → 4 × 4 = 16 (from memo!) ✅
memo[5] = 26 ✓

Total unique calculations: 5 (vs 15+ for naive)
```

**Advantages:**
- ✅ Each value calculated only once
- ✅ Linear time complexity
- ✅ Easy to understand
- ✅ Good for learning DP concepts

**Disadvantages:**
- ❌ Uses O(n) extra space for memo
- ❌ Still uses recursion stack

---

### Approach 3: Tabulation (Bottom-Up Dynamic Programming)

**Time Complexity:** O(n) - Single loop  
**Space Complexity:** O(n) - DP array

**Concept:**
Build the solution iteratively from bottom to top using a table (array), starting from base cases.

**Java Implementation:**

```java
class Solution {
    static final int MOD = 1000000007;
    
    public long countFriendsPairings(int n) {
        // Handle base cases
        if (n <= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        
        // Create DP array
        long[] dp = new long[n + 1];
        
        // Initialize base cases
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        
        // Fill table bottom-up using the recurrence relation
        // f(i) = f(i-1) + (i-1) * f(i-2)
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + ((i - 1) * dp[i - 2]) % MOD) % MOD;
        }
        
        return dp[n];
    }
}
```

**Execution Trace for n=5:**
```
Initial: dp = [1, 1, 2, ?, ?, ?]
              0  1  2  3  4  5

i=3: dp[3] = dp[2] + 2×dp[1] = 2 + 2×1 = 4
     dp = [1, 1, 2, 4, ?, ?]

i=4: dp[4] = dp[3] + 3×dp[2] = 4 + 3×2 = 10
     dp = [1, 1, 2, 4, 10, ?]

i=5: dp[5] = dp[4] + 4×dp[3] = 10 + 4×4 = 26
     dp = [1, 1, 2, 4, 10, 26]

Return: dp[5] = 26
```

**Step-by-Step Table Building:**
```
┌─────┬────────┬────────────────────────────────────┐
│  i  │  dp[i] │           Calculation              │
├─────┼────────┼────────────────────────────────────┤
│  0  │   1    │  Base case (no friends)            │
│  1  │   1    │  Base case (one friend)            │
│  2  │   2    │  Base case (two friends)           │
│  3  │   4    │  dp[2] + 2×dp[1] = 2 + 2 = 4       │
│  4  │   10   │  dp[3] + 3×dp[2] = 4 + 6 = 10      │
│  5  │   26   │  dp[4] + 4×dp[3] = 10 + 16 = 26    │
│  6  │   76   │  dp[5] + 5×dp[4] = 26 + 50 = 76    │
└─────┴────────┴────────────────────────────────────┘
```

**Advantages:**
- ✅ No recursion (no stack overflow)
- ✅ Simple iterative logic
- ✅ Predictable performance
- ✅ Easy to debug

**Disadvantages:**
- ❌ Uses O(n) space for entire array

---

### Approach 4: Space-Optimized DP (Optimal Solution)

**Time Complexity:** O(n) - Single loop  
**Space Complexity:** O(1) - Only two variables

**Concept:**
We only need the last two values to calculate the next one. No need to store the entire sequence!

**Java Implementation:**

```java
class Solution {
    static final int MOD = 1000000007;
    
    public long countFriendsPairings(int n) {
        // Base cases
        if (n <= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        
        // Only keep track of last two values
        long prev2 = 1;  // f(i-2)
        long prev1 = 2;  // f(i-1)
        long curr = 0;
        
        // Calculate from 3 to n
        for (int i = 3; i <= n; i++) {
            curr = (prev1 + ((i - 1) * prev2) % MOD) % MOD;
            prev2 = prev1;
            prev1 = curr;
        }
        
        return curr;
    }
}
```

**Execution Trace for n=6:**
```
Initial: prev2=1 (f(1)), prev1=2 (f(2))

i=3: curr = 2 + 2×1 = 4,     prev2=2,  prev1=4
i=4: curr = 4 + 3×2 = 10,    prev2=4,  prev1=10
i=5: curr = 10 + 4×4 = 26,   prev2=10, prev1=26
i=6: curr = 26 + 5×10 = 76,  prev2=26, prev1=76

Return: curr = 76
```

**Visual Sliding Window:**
```
Position:  0   1   2   3   4   5   6
Value:     1   1   2   4   10  26  76

Step 1:   [1   2]  → curr = 4
Step 2:       [2   4]  → curr = 10
Step 3:           [4   10]  → curr = 26
Step 4:               [10  26]  → curr = 76
```

**Advantages:**
- ✅ Optimal time and space complexity
- ✅ No recursion overhead
- ✅ Memory efficient (O(1) space)
- ✅ Best for production code

**This is the BEST solution for this problem!** ⭐

---

## Detailed Algorithm Walkthrough

### Example: Computing f(4) with All Approaches

**Goal:** Find the number of ways to arrange 4 friends

**Expected Result:** 10 ways

#### Method 1: Manual Enumeration
```
All single:
1. {1}, {2}, {3}, {4}

One pair:
2. {1, 2}, {3}, {4}
3. {1, 3}, {2}, {4}
4. {1, 4}, {2}, {3}
5. {2, 3}, {1}, {4}
6. {2, 4}, {1}, {3}
7. {3, 4}, {1}, {2}

Two pairs:
8. {1, 2}, {3, 4}
9. {1, 3}, {2, 4}
10. {1, 4}, {2, 3}

Total: 10 ways ✓
```

#### Method 2: Using Recurrence Relation
```
f(4) = f(3) + 3×f(2)

Calculate f(3):
f(3) = f(2) + 2×f(1)
     = 2 + 2×1
     = 4

Calculate f(4):
f(4) = f(3) + 3×f(2)
     = 4 + 3×2
     = 4 + 6
     = 10 ✓
```

---

## Mathematical Insight

### Recurrence Relation Breakdown

For the **nth person**:

**Case 1: Person n stays single**
- Arrangements for remaining (n-1) people: `f(n-1)`
- Example: If person 4 is single in a 4-friend group
  - We need to arrange {1, 2, 3}
  - Number of ways = f(3)

**Case 2: Person n pairs with someone**
- Person n can pair with any of (n-1) friends: `(n-1)` choices
- After pairing, we have (n-2) people left: `f(n-2)` ways
- Total: `(n-1) × f(n-2)`
- Example: If person 4 pairs in a 4-friend group
  - 3 choices: pair with 1, 2, or 3
  - If 4 pairs with 2, arrange {1, 3}: f(2) ways
  - Total: 3 × f(2)

**Combined:**
```
f(n) = f(n-1) + (n-1) × f(n-2)
```

### Comparison with Fibonacci

**Fibonacci:** `f(n) = f(n-1) + f(n-2)`  
**Friends Pairing:** `f(n) = f(n-1) + (n-1) × f(n-2)`

The key difference is the `(n-1)` multiplier, which accounts for the number of choices when pairing.

---

## Performance Comparison

### Time Complexity Comparison

| Approach | Time | Function Calls (n=10) |
|----------|------|----------------------|
| Naive Recursion | O(2^n) | 200+ |
| Memoization | O(n) | 10 |
| Tabulation | O(n) | 1 (9 iterations) |
| Space-Optimized | O(n) | 1 (9 iterations) |

### Space Complexity Comparison

| Approach | Space | Notes |
|----------|-------|-------|
| Naive Recursion | O(n) | Recursion stack |
| Memoization | O(n) | Memo array + stack |
| Tabulation | O(n) | DP array |
| Space-Optimized | O(1) | Only 2-3 variables |

---

## Common Mistakes & Edge Cases

### Edge Cases to Handle

1. **n = 0**
   - Input: 0
   - Output: 1
   - No friends, one way (do nothing)

2. **n = 1**
   - Input: 1
   - Output: 1
   - One friend stays single

3. **n = 2**
   - Input: 2
   - Output: 2
   - Both single OR paired

4. **Large n (close to 100)**
   - Must use MOD (10^9 + 7)
   - Test overflow handling

### Common Mistakes

❌ **Mistake 1:** Forgetting the base case for n=0
```java
// Wrong - doesn't handle n=0
if (n == 1) return 1;
if (n == 2) return 2;

// Correct
if (n <= 1) return 1;
if (n == 2) return 2;
```

❌ **Mistake 2:** Not applying MOD correctly
```java
// Wrong - overflow before MOD
long result = (n - 1) * prev2;
result = result % MOD;

// Correct - MOD at each step
long result = ((n - 1) * prev2) % MOD;
```

❌ **Mistake 3:** Incorrect multiplication order
```java
// Wrong - can cause overflow
dp[i] = (dp[i-1] + (i-1) * dp[i-2]) % MOD;

// Correct - MOD intermediate results
dp[i] = (dp[i-1] + ((i-1) * dp[i-2]) % MOD) % MOD;
```

❌ **Mistake 4:** Array index out of bounds
```java
// Wrong - crashes for n < 3
long[] dp = new long[n];
dp[2] = 2;  // Error if n < 3

// Correct - check bounds or size array properly
if (n <= 2) return n;
long[] dp = new long[n + 1];
```

---

## Pattern Recognition

### This Problem Uses:

1. **Dynamic Programming** - Overlapping subproblems
2. **Recurrence Relations** - Build solution from smaller problems
3. **Combinatorics** - Counting arrangements
4. **Space Optimization** - Reduce memory usage

### Similar Problems:

**Same Pattern (Combinatorics + DP):**

1. **[LeetCode 70: Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)**
   - `f(n) = f(n-1) + f(n-2)` (Fibonacci)
   - Each step: 1 or 2 stairs

2. **[LeetCode 509: Fibonacci Number](https://leetcode.com/problems/fibonacci-number/)**
   - `f(n) = f(n-1) + f(n-2)`
   - Classic sequence

3. **[LeetCode 1137: N-th Tribonacci](https://leetcode.com/problems/n-th-tribonacci-number/)**
   - `f(n) = f(n-1) + f(n-2) + f(n-3)`
   - Sum of three previous

4. **[GFG: Tiling Problem](https://www.geeksforgeeks.org/tiling-problem/)**
   - Similar recurrence pattern
   - Different context (tiles)

---

## Practice Tips

### How to Master This Problem

1. **Understand the choices** - What can person n do?
2. **Write the recurrence** - Express f(n) in terms of smaller problems
3. **Start with recursion** - Implement naive solution first
4. **Add memoization** - Cache results to optimize
5. **Convert to iteration** - Remove recursion overhead
6. **Optimize space** - Use only necessary variables

### Interview Approach

1. **Clarify the problem** - Understand pairing rules
2. **Explain the intuition** - Person n's two choices
3. **Derive the recurrence** - Show the formula
4. **Start with simple DP** - Tabulation approach
5. **Optimize to O(1) space** - Show space optimization
6. **Handle MOD correctly** - Apply at each step
7. **Test with examples** - Verify n=3, n=4

---

## Complete Working Code

```java
public class FriendsPairingProblem {
    static final int MOD = 1000000007;
    
    // Space-Optimized Solution (Best)
    public static long countFriendsPairings(int n) {
        if (n <= 1) return 1;
        if (n == 2) return 2;
        
        long prev2 = 1;
        long prev1 = 2;
        long curr = 0;
        
        for (int i = 3; i <= n; i++) {
            curr = (prev1 + ((i - 1) * prev2) % MOD) % MOD;
            prev2 = prev1;
            prev1 = curr;
        }
        
        return curr;
    }
    
    // Test cases
    public static void main(String[] args) {
        System.out.println("n=0: " + countFriendsPairings(0)); // 1
        System.out.println("n=1: " + countFriendsPairings(1)); // 1
        System.out.println("n=2: " + countFriendsPairings(2)); // 2
        System.out.println("n=3: " + countFriendsPairings(3)); // 4
        System.out.println("n=4: " + countFriendsPairings(4)); // 10
        System.out.println("n=5: " + countFriendsPairings(5)); // 26
        System.out.println("n=6: " + countFriendsPairings(6)); // 76
    }
}
```

**Output:**
```
n=0: 1
n=1: 1
n=2: 2
n=3: 4
n=4: 10
n=5: 26
n=6: 76
```

---

## Summary

| Aspect | Best Approach |
|--------|---------------|
| **Algorithm** | Space-Optimized DP |
| **Time Complexity** | O(n) |
| **Space Complexity** | O(1) |
| **Key Formula** | f(n) = f(n-1) + (n-1)×f(n-2) |
| **Difficulty** | Medium |
| **Pattern** | Dynamic Programming, Combinatorics |

### Key Takeaways

✅ Each person has two choices: stay single or pair up  
✅ Recurrence: `f(n) = f(n-1) + (n-1)×f(n-2)`  
✅ The `(n-1)` factor represents pairing choices  
✅ Overlapping subproblems → Use DP  
✅ Can optimize to O(1) space using sliding window  
✅ Always apply MOD at each step to avoid overflow  

This problem beautifully demonstrates how dynamic programming can solve combinatorial counting problems efficiently!
