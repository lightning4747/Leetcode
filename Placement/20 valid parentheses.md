# LeetCode 20: Valid Parentheses

## Problem Information

**Difficulty:** Easy  
**LeetCode Number:** 20  
**Problem Link:** [https://leetcode.com/problems/valid-parentheses/](https://leetcode.com/problems/valid-parentheses/)

---

## Problem Statement

Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if open brackets are closed by the same type of brackets, open brackets are closed in the correct order, and every close bracket has a corresponding open bracket of the same type.

**Return `true` if `s` is a valid string, and `false` otherwise.**

---

## Examples

### Example 1
```
Input: s = "()"
Output: true
Explanation: The string contains one pair of matching parentheses.
```

### Example 2
```
Input: s = "()[]{}"
Output: true
Explanation: All three types of brackets are properly matched and closed in order.
```

### Example 3
```
Input: s = "(]"
Output: false
Explanation: The opening '(' does not match the closing ']'.
```

### Example 4
```
Input: s = "([)]"
Output: false
Explanation: The brackets are not closed in the correct order.
```

### Example 5
```
Input: s = "{[]}"
Output: true
Explanation: The brackets are properly nested and closed in order.
```

### Example 6
```
Input: s = "((("
Output: false
Explanation: No closing brackets - invalid.
```

---

## Constraints

```
1 ≤ s.length ≤ 10^4
s consists of parentheses only: '()[]{}'
```

---

## Core Idea & Intuition

### 💡 Key Insight

This problem is fundamentally about **matching pairs** in the **correct order**. The key observation is that brackets must be closed in a **Last In, First Out (LIFO)** manner — exactly how a **stack** works!

### Real-World Analogy

Think of stacking plates:
- When you open a bracket `(`, you place a plate on the stack
- When you close a bracket `)`, you remove the top plate
- The plate you remove **must match** the type you're trying to close
- At the end, all plates must be removed (empty stack)

### Why Stack?

Consider the string `"([{}])"`:
```
Step-by-step matching:
1. ( → Stack: [(]
2. [ → Stack: [(, []
3. { → Stack: [(, [, {]
4. } → Matches {, pop → Stack: [(, []
5. ] → Matches [, pop → Stack: [(]
6. ) → Matches (, pop → Stack: []

Result: Empty stack = Valid! ✓
```

The **most recently opened bracket must be the first to close** — this is the LIFO property of stacks!

### Intuition

**Three Rules for Valid Parentheses:**
1. **Matching Type:** `(` must close with `)`, `{` with `}`, `[` with `]`
2. **Correct Order:** The most recent opening bracket closes first
3. **Complete Pairs:** Every opening bracket has a closing bracket

**Why other data structures fail:**
- **Queue:** FIFO doesn't match the nesting order
- **Array without stack logic:** Hard to track the most recent opening
- **Hash Map alone:** Can't handle ordering

---

## Visual Representation

### Valid Example: `"({[]})"`

```
Character:  (    {    [    ]    }    )
           ┌─┐  ┌─┐  ┌─┐  Pop  Pop  Pop
Stack:     │(│  │(│  │(│  │(│  │(│  Empty
           └─┘  │{│  │{│  │{│  └─┘  
                └─┘  │[│  └─┘       
                     └─┘            

Step 1: '(' → Push '(' to stack
Step 2: '{' → Push '{' to stack  
Step 3: '[' → Push '[' to stack
Step 4: ']' → Matches '[', pop
Step 5: '}' → Matches '{', pop
Step 6: ')' → Matches '(', pop
Result: Stack empty → Valid ✓
```

### Invalid Example: `"([)]"`

```
Character:  (    [    )    ]
           ┌─┐  ┌─┐  ✗    
Stack:     │(│  │(│  Mismatch! '[' ≠ ')'
           └─┘  │[│  
                └─┘  

Step 1: '(' → Push '(' to stack
Step 2: '[' → Push '[' to stack
Step 3: ')' → Top is '[', doesn't match ')'
Result: Invalid ✗
```

### Stack Behavior Visualization

```
Opening brackets → PUSH
Closing brackets → POP and MATCH

       Push        Push        Push
       ↓           ↓           ↓
    ┌───┐       ┌───┐       ┌───┐
    │ ( │       │ [ │       │ { │
    └───┘       └───┘       └───┘
    Stack       Stack       Stack

       Pop         Pop         Pop
       ↑           ↑           ↑
    ┌───┐       ┌───┐       ┌───┐
    │ ) │       │ ] │       │ } │
    └───┘       └───┘       └───┘
    Match?      Match?      Match?
```

---

## Solutions

### Approach 1: Brute Force (String Replacement)

**Time Complexity:** O(n²) - Quadratic  
**Space Complexity:** O(n) - String copies

**Concept:**
Repeatedly find and remove matching pairs `()`, `[]`, `{}` from the string until no more pairs exist. If the final string is empty, it was valid.

**Why It's Inefficient:**
Each replacement requires scanning the entire string and creating a new string, leading to quadratic time complexity.

**Java Implementation:**

```java
class Solution {
    public boolean isValid(String s) {
        // Keep replacing valid pairs until no more can be found
        while (s.contains("()") || s.contains("[]") || s.contains("{}")) {
            s = s.replace("()", "");
            s = s.replace("[]", "");
            s = s.replace("{}", "");
        }
        
        // If string is empty, all brackets were matched
        return s.isEmpty();
    }
}
```

**Execution Trace for `s = "({[]})"`:**
```
Initial:    "({[]})"
           
Iteration 1:
  - Replace "[]" with "" → "({()})"
  
Iteration 2:
  - Replace "()" with "" → "{}"
  
Iteration 3:
  - Replace "{}" with "" → ""
  
Result: Empty string → Valid ✓

Total iterations: 3
String operations: 9 (3 checks × 3 iterations)
```

**Execution Trace for `s = "([)]"`:**
```
Initial:    "([)]"

Iteration 1:
  - No "()" found
  - No "[]" found  
  - No "{}" found
  
Result: Non-empty string "([)]" → Invalid ✗
```

**Problems:**
- ❌ Very slow: O(n²) time complexity
- ❌ Multiple string copies in memory
- ❌ Doesn't scale for large inputs
- ❌ Not suitable for interviews

---

### Approach 2: Stack with Closing Bracket Matching

**Time Complexity:** O(n) - Linear  
**Space Complexity:** O(n) - Stack storage

**Concept:**
Use a hash map to store bracket pairs and a stack to track opening brackets. For each closing bracket, verify it matches the most recent opening bracket.

**Java Implementation:**

```java
class Solution {
    public boolean isValid(String s) {
        // Create a map of closing to opening brackets
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put('}', '{');
        pairs.put(']', '[');
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            // If it's a closing bracket
            if (pairs.containsKey(c)) {
                // Check if stack is empty or top doesn't match
                if (stack.isEmpty() || stack.pop() != pairs.get(c)) {
                    return false;
                }
            } 
            // If it's an opening bracket
            else {
                stack.push(c);
            }
        }
        
        // Valid only if all brackets matched (stack empty)
        return stack.isEmpty();
    }
}
```

**Advantages:**
- ✅ Linear time complexity
- ✅ Clear separation of logic
- ✅ Easy to understand with hash map

---

### Approach 3: Stack with Smart Push (Optimal & Elegant)

**Time Complexity:** O(n) - Linear  
**Space Complexity:** O(n) - Stack storage

**Concept:**
When encountering an opening bracket, push the **corresponding closing bracket** onto the stack. When encountering a closing bracket, simply check if it matches the top of the stack.

**This is the MOST ELEGANT solution!** 🌟

**Java Implementation:**

```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            // If opening bracket, push the EXPECTED closing bracket
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } 
            // If closing bracket, check if it matches expected
            else {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
        
        // Valid only if all brackets matched
        return stack.isEmpty();
    }
}
```

**Why This is Brilliant:**

1. **No hash map needed** - Direct matching
2. **Fewer operations** - One comparison instead of two
3. **Cleaner logic** - Push expected, pop and compare
4. **Same complexity** - Still O(n) time and space

---

## Detailed Algorithm Walkthrough

### Example 1: Valid String `"({[]})"`

**Character-by-character execution:**

```
┌──────┬─────────┬────────────────────────┬───────────────┐
│ Step │  Char   │      Action            │    Stack      │
├──────┼─────────┼────────────────────────┼───────────────┤
│  1   │   '('   │ Push ')'               │ [')']         │
│  2   │   '{'   │ Push '}'               │ [')', '}']    │
│  3   │   '['   │ Push ']'               │ [')', '}', ']'│
│  4   │   ']'   │ Pop, matches ']' ✓     │ [')', '}']    │
│  5   │   '}'   │ Pop, matches '}' ✓     │ [')']         │
│  6   │   ')'   │ Pop, matches ')' ✓     │ []            │
└──────┴─────────┴────────────────────────┴───────────────┘

Final Stack: Empty
Result: Valid ✓
```

### Example 2: Invalid String `"([)]"`

```
┌──────┬─────────┬────────────────────────┬───────────────┐
│ Step │  Char   │      Action            │    Stack      │
├──────┼─────────┼────────────────────────┼───────────────┤
│  1   │   '('   │ Push ')'               │ [')']         │
│  2   │   '['   │ Push ']'               │ [')', ']']    │
│  3   │   ')'   │ Pop ']', not equal ')'!│ FAIL ✗        │
└──────┴─────────┴────────────────────────┴───────────────┘

Result: Invalid ✗
Reason: Expected ']' but got ')'
```

### Example 3: Invalid String `"((("` (Unclosed brackets)

```
┌──────┬─────────┬────────────────────────┬───────────────┐
│ Step │  Char   │      Action            │    Stack      │
├──────┼─────────┼────────────────────────┼───────────────┤
│  1   │   '('   │ Push ')'               │ [')']         │
│  2   │   '('   │ Push ')'               │ [')', ')']    │
│  3   │   '('   │ Push ')'               │ [')', ')', ')']│
└──────┴─────────┴────────────────────────┴───────────────┘

Final Stack: [')', ')', ')'] - NOT EMPTY
Result: Invalid ✗
Reason: Unclosed brackets remaining
```

### Example 4: Invalid String `")))"` (Only closing brackets)

```
┌──────┬─────────┬────────────────────────┬───────────────┐
│ Step │  Char   │      Action            │    Stack      │
├──────┼─────────┼────────────────────────┼───────────────┤
│  1   │   ')'   │ Stack empty, FAIL ✗    │ []            │
└──────┴─────────┴────────────────────────┴───────────────┘

Result: Invalid ✗
Reason: No opening bracket for closing ')'
```

---

## Step-by-Step Example: `"[{()}]"`

**Visual Stack Operations:**

```
Input: "[{()}]"

┌─────────────────────────────────────────────────────────┐
│                    EXECUTION TRACE                      │
└─────────────────────────────────────────────────────────┘

Character: '['
Action: Opening bracket → Push ']'
Stack: [']']
Status: ✓ Continue

Character: '{'
Action: Opening bracket → Push '}'
Stack: [']', '}']
Status: ✓ Continue

Character: '('
Action: Opening bracket → Push ')'
Stack: [']', '}', ')']
Status: ✓ Continue

Character: ')'
Action: Closing bracket → Pop and check
Top of stack: ')'
Match: ')' == ')' ✓
Stack: [']', '}']
Status: ✓ Continue

Character: '}'
Action: Closing bracket → Pop and check
Top of stack: '}'
Match: '}' == '}' ✓
Stack: [']']
Status: ✓ Continue

Character: ']'
Action: Closing bracket → Pop and check
Top of stack: ']'
Match: ']' == ']' ✓
Stack: []
Status: ✓ Continue

┌─────────────────────────────────────────────────────────┐
│ Final Stack: []                                         │
│ Result: VALID ✓                                         │
└─────────────────────────────────────────────────────────┘
```

---

## Why Stack is Perfect for This Problem

### Stack Properties Match Problem Requirements

| Requirement | Stack Feature |
|-------------|---------------|
| Most recent bracket closes first | LIFO (Last In, First Out) |
| Track opening brackets | Push operation |
| Match with closing brackets | Pop and compare |
| All brackets must match | Empty stack at end |

### Comparison with Other Data Structures

```
┌─────────────────┬──────────┬─────────────────────────┐
│ Data Structure  │ Suitable │       Reason            │
├─────────────────┼──────────┼─────────────────────────┤
│ Stack           │   ✓✓✓    │ Perfect LIFO matching   │
│ Queue           │    ✗     │ FIFO doesn't match order│
│ Array (random)  │    ✗     │ Can't track recent      │
│ Hash Map only   │    ✗     │ Can't handle order      │
│ Linked List     │    ~     │ Works but not optimal   │
└─────────────────┴──────────┴─────────────────────────┘
```

---

## Complexity Analysis

### Time Complexity: O(n)

```
- Single pass through the string: O(n)
- Each character processed once
- Stack operations (push/pop): O(1) each
- Total: O(n)
```

### Space Complexity: O(n)

```
- Worst case: All opening brackets
- Example: "(((((" → Stack size = 5
- Stack can grow up to n/2 for valid strings
- Total: O(n)
```

### Space Complexity Breakdown by Input

| Input Type | Stack Size | Example |
|------------|------------|---------|
| All opening | n | "(((" |
| All closing | 0 (fails early) | ")))" |
| Balanced nested | n/2 | "({[]})" |
| Alternating valid | 1 | "()()()" |

---

## Common Mistakes & Edge Cases

### Edge Cases to Handle

**1. Empty String**
```java
Input: s = ""
Output: true
Explanation: No brackets to match, valid by default
```

**2. Single Character**
```java
Input: s = "("
Output: false
Explanation: Unclosed bracket
```

**3. Odd Length**
```java
Input: s = "(()"
Output: false
Explanation: Cannot have all pairs with odd length
```

**4. All Same Opening Brackets**
```java
Input: s = "[[["
Output: false
Explanation: No closing brackets
```

**5. All Same Closing Brackets**
```java
Input: s = "]]]"
Output: false
Explanation: No opening brackets
```

**6. Mixed Valid Pairs**
```java
Input: s = "()[]{}"
Output: true
Explanation: Three consecutive valid pairs
```

### Common Mistakes

❌ **Mistake 1:** Not checking if stack is empty before popping
```java
// Wrong - will throw EmptyStackException
if (stack.pop() != c) return false;

// Correct - check first
if (stack.isEmpty() || stack.pop() != c) return false;
```

❌ **Mistake 2:** Forgetting to check if stack is empty at the end
```java
// Wrong - doesn't catch unclosed brackets
return true;

// Correct
return stack.isEmpty();
```

❌ **Mistake 3:** Using wrong bracket matching logic
```java
// Wrong - pushes opening bracket
if (c == '(') stack.push('(');

// Correct approach 1 - push expected closing
if (c == '(') stack.push(')');

// Correct approach 2 - use hash map
if (pairs.containsKey(c)) ...
```

❌ **Mistake 4:** Not handling odd-length strings
```java
// Optimization: odd length cannot be valid
if (s.length() % 2 != 0) return false;
```

---

## Optimizations & Variations

### Optimization 1: Early Return for Odd Length

```java
class Solution {
    public boolean isValid(String s) {
        // Odd length strings cannot have all pairs
        if (s.length() % 2 != 0) {
            return false;
        }
        
        Stack<Character> stack = new Stack<>();
        // ... rest of the code
    }
}
```

### Optimization 2: Using Array Instead of Stack

```java
class Solution {
    public boolean isValid(String s) {
        char[] stack = new char[s.length()];
        int top = -1;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack[++top] = ')';
            } else if (c == '{') {
                stack[++top] = '}';
            } else if (c == '[') {
                stack[++top] = ']';
            } else {
                if (top == -1 || stack[top--] != c) {
                    return false;
                }
            }
        }
        
        return top == -1;
    }
}
```

**Benefits:** Slightly faster, no Stack object overhead

### Optimization 3: Using Switch Statement

```java
class Solution {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) return false;
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(': stack.push(')'); break;
                case '{': stack.push('}'); break;
                case '[': stack.push(']'); break;
                default:
                    if (stack.isEmpty() || stack.pop() != c) {
                        return false;
                    }
            }
        }
        
        return stack.isEmpty();
    }
}
```

---

## Real-World Applications

### Where This Pattern Appears

1. **Compiler Design**
   - Parsing syntax in programming languages
   - Checking balanced braces in code

2. **Text Editors**
   - Auto-closing brackets/quotes
   - Syntax highlighting
   - Code formatting

3. **Expression Evaluation**
   - Mathematical expression parsing
   - Calculator applications
   - Formula validation

4. **HTML/XML Parsing**
   - Validating nested tags
   - DOM tree construction

5. **Balanced Equations**
   - Chemical formulas
   - Mathematical expressions

---

## Related Problems

### Similar LeetCode Problems

1. **[LeetCode 22: Generate Parentheses](https://leetcode.com/problems/generate-parentheses/)**
   - Generate all valid combinations
   - Backtracking + Stack validation

2. **[LeetCode 32: Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/)**
   - Find longest valid substring
   - Advanced stack usage

3. **[LeetCode 921: Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/)**
   - Count missing brackets
   - Stack-based counting

4. **[LeetCode 1111: Maximum Nesting Depth of Two Valid Parentheses Strings](https://leetcode.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/)**
   - Split into two valid strings
   - Depth tracking

5. **[LeetCode 1249: Minimum Remove to Make Valid Parentheses](https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/)**
   - Remove invalid brackets
   - Stack + String manipulation

---

## Practice Tips

### How to Master This Problem

1. **Understand the LIFO property** of stacks
2. **Visualize the stack** as you process each character
3. **Practice edge cases** (empty, single char, all opening/closing)
4. **Try both approaches** (hash map vs smart push)
5. **Optimize step by step** (add early returns, use arrays)

### Interview Tips

**Start by explaining:**
1. "This is a bracket matching problem requiring LIFO behavior"
2. "I'll use a stack to track opening brackets"
3. "When I see a closing bracket, I'll check if it matches the most recent opening"

**Walk through an example:**
- Choose a simple valid case: `"()"`
- Then a simple invalid case: `"(]"`
- Show the stack state at each step

**Discuss complexity:**
- "Time: O(n) - single pass through string"
- "Space: O(n) - worst case all opening brackets"

**Mention optimizations:**
- "We can early-return if length is odd"
- "Can use an array instead of Stack for slight performance gain"

---

## Complete Working Code

```java
public class ValidParentheses {
    
    // Solution 1: Smart Push (Most Elegant)
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) return false;
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    // Solution 2: Using HashMap
    public boolean isValidHashMap(String s) {
        if (s.length() % 2 != 0) return false;
        
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put('}', '{');
        pairs.put(']', '[');
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (pairs.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != pairs.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        
        return stack.isEmpty();
    }
    
    // Test cases
    public static void main(String[] args) {
        ValidParentheses solution = new ValidParentheses();
        
        System.out.println(solution.isValid("()"));       // true
        System.out.println(solution.isValid("()[]{}"));   // true
        System.out.println(solution.isValid("(]"));       // false
        System.out.println(solution.isValid("([)]"));     // false
        System.out.println(solution.isValid("{[]}"));     // true
        System.out.println(solution.isValid(""));         // true
        System.out.println(solution.isValid("((("));      // false
    }
}
```

**Output:**
```
true
true
false
false
true
true
false
```

---

## Summary

| Aspect | Details |
|--------|---------|
| **Best Approach** | Stack with Smart Push |
| **Time Complexity** | O(n) |
| **Space Complexity** | O(n) |
| **Key Data Structure** | Stack (LIFO) |
| **Difficulty** | Easy |
| **Pattern** | Stack, String Matching |

### Key Takeaways

✅ Valid parentheses must follow last-opened, first-closed order — making stacks ideal due to their LIFO behavior  
✅ Push expected closing brackets for cleaner matching logic  
✅ Always check if stack is empty before popping  
✅ Valid string ends with an empty stack  
✅ O(n) time, O(n) space is optimal for this problem  
✅ Early return on odd length saves unnecessary computation  

This problem is a fundamental introduction to stack-based algorithms and appears frequently in coding interviews. Master it well, as the pattern applies to many other problems!
