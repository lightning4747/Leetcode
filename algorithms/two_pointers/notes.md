# Two Pointers Algorithm: Complete Master Guide

## Table of Contents
1. [Introduction](#introduction)
2. [Core Concepts](#core-concepts)
3. [When to Use Two Pointers](#when-to-use-two-pointers)
4. [Pattern Classifications](#pattern-classifications)
5. [Implementation Strategies](#implementation-strategies)
6. [Common Problem Types](#common-problem-types)
7. [Time and Space Complexity](#time-and-space-complexity)
8. [Problem-Solving Framework](#problem-solving-framework)
9. [Advanced Techniques](#advanced-techniques)
10. [Practice Problems](#practice-problems)

---

## Introduction

The Two Pointers technique is a powerful algorithmic pattern that uses two references (pointers) to traverse a data structure, typically arrays or linked lists. This approach often reduces time complexity from O(n²) to O(n) and eliminates the need for additional data structures.

### Key Benefits
- **Efficiency**: Reduces nested loops to single-pass solutions
- **Space Optimization**: Usually O(1) extra space
- **Simplicity**: Intuitive logic and clean code
- **Versatility**: Applicable to many problem domains

---

## Core Concepts

### What Are Pointers?

In the context of this algorithm, "pointers" are indices or references that traverse through data structures. They can:
- Move in the same direction (both left-to-right)
- Move in opposite directions (one from start, one from end)
- Move at different speeds (fast and slow)

### Fundamental Principles

1. **Reduction of Search Space**: Each pointer movement eliminates possibilities
2. **Invariant Maintenance**: Pointers maintain specific relationships or conditions
3. **Single Pass**: Most problems solved in one traversal
4. **Decision Making**: At each step, decide which pointer(s) to move based on conditions

---

## When to Use Two Pointers

### Strong Indicators

✅ **Use Two Pointers When:**
- Working with **sorted arrays** or lists
- Need to find **pairs or triplets** with specific properties
- Looking for **subarrays** or **subsequences** meeting criteria
- Problem involves **palindromes** or **mirror patterns**
- Need to **remove duplicates** or **partition** data in-place
- Comparing elements from **both ends** of structure
- Problem has **O(n²)** brute force that can be optimized
- Working with **linked lists** and need to find cycles or middle

❌ **Avoid When:**
- Data is **unsorted** and sorting would be too expensive
- Need to access **random** elements frequently
- Problem requires **backtracking** or **recursive exploration**
- Multiple passes are unavoidable

### Problem Keywords to Watch For
- "Sorted array"
- "Find pair/triplet"
- "Remove duplicates"
- "Reverse"
- "Palindrome"
- "Container/trap water"
- "Sliding window" (related pattern)
- "In-place"

---

## Pattern Classifications

### 1. Opposite Direction (Converging)

Pointers start at opposite ends and move toward each other.

**Use Cases:**
- Finding pairs in sorted arrays
- Palindrome verification
- Container with most water problems

**Visual:**
```
[1, 2, 3, 4, 5, 6, 7, 8]
 ↑                    ↑
left               right
```

### 2. Same Direction (Parallel/Chasing)

Both pointers start from the same end and move in the same direction.

**Use Cases:**
- Removing duplicates
- Fast/slow cycle detection
- Partitioning arrays

**Visual:**
```
[1, 2, 3, 4, 5, 6, 7, 8]
 ↑  ↑
slow fast
```

### 3. Sliding Window (Variable/Fixed Size)

Two pointers define a window that expands or contracts.

**Use Cases:**
- Longest substring problems
- Maximum sum subarray
- Anagrams in strings

**Visual:**
```
[1, 2, 3, 4, 5, 6, 7, 8]
    ↑     ↑
  start  end
```

### 4. Fast and Slow (Tortoise and Hare)

One pointer moves faster than the other.

**Use Cases:**
- Cycle detection in linked lists
- Finding middle of linked list
- Removing nth node from end

**Visual:**
```
LinkedList: 1 → 2 → 3 → 4 → 5
            ↑       ↑
          slow    fast (moves 2x speed)
```

---

## Implementation Strategies

### Strategy 1: Opposite Direction Pattern

**Template:**

```python
# Python
def opposite_direction(arr):
    left = 0
    right = len(arr) - 1
    
    while left < right:
        # Process current state
        if condition_met(arr[left], arr[right]):
            # Found answer
            return result
        elif should_move_left(arr[left], arr[right]):
            left += 1
        else:
            right -= 1
    
    return default_result
```

```javascript
// JavaScript
function oppositeDirection(arr) {
    let left = 0;
    let right = arr.length - 1;
    
    while (left < right) {
        // Process current state
        if (conditionMet(arr[left], arr[right])) {
            // Found answer
            return result;
        } else if (shouldMoveLeft(arr[left], arr[right])) {
            left++;
        } else {
            right--;
        }
    }
    
    return defaultResult;
}
```

```java
// Java
public class Solution {
    public ResultType oppositeDirection(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            // Process current state
            if (conditionMet(arr[left], arr[right])) {
                // Found answer
                return result;
            } else if (shouldMoveLeft(arr[left], arr[right])) {
                left++;
            } else {
                right--;
            }
        }
        
        return defaultResult;
    }
}
```

**Example: Two Sum in Sorted Array**

```python
# Python
def two_sum_sorted(nums, target):
    left, right = 0, len(nums) - 1
    
    while left < right:
        current_sum = nums[left] + nums[right]
        
        if current_sum == target:
            return [left, right]
        elif current_sum < target:
            left += 1  # Need larger sum
        else:
            right -= 1  # Need smaller sum
    
    return [-1, -1]
```

```javascript
// JavaScript
function twoSumSorted(nums, target) {
    let left = 0, right = nums.length - 1;
    
    while (left < right) {
        const currentSum = nums[left] + nums[right];
        
        if (currentSum === target) {
            return [left, right];
        } else if (currentSum < target) {
            left++;  // Need larger sum
        } else {
            right--;  // Need smaller sum
        }
    }
    
    return [-1, -1];
}
```

```java
// Java
public int[] twoSumSorted(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    
    while (left < right) {
        int currentSum = nums[left] + nums[right];
        
        if (currentSum == target) {
            return new int[]{left, right};
        } else if (currentSum < target) {
            left++;  // Need larger sum
        } else {
            right--;  // Need smaller sum
        }
    }
    
    return new int[]{-1, -1};
}
```

### Strategy 2: Same Direction Pattern

**Template:**

```python
# Python
def same_direction(arr):
    slow = 0
    
    for fast in range(len(arr)):
        if condition_met(arr[fast]):
            # Process element at fast
            arr[slow] = arr[fast]
            slow += 1
    
    return slow  # or arr[:slow]
```

```javascript
// JavaScript
function sameDirection(arr) {
    let slow = 0;
    
    for (let fast = 0; fast < arr.length; fast++) {
        if (conditionMet(arr[fast])) {
            // Process element at fast
            arr[slow] = arr[fast];
            slow++;
        }
    }
    
    return slow;  // or arr.slice(0, slow)
}
```

```java
// Java
public int sameDirection(int[] arr) {
    int slow = 0;
    
    for (int fast = 0; fast < arr.length; fast++) {
        if (conditionMet(arr[fast])) {
            // Process element at fast
            arr[slow] = arr[fast];
            slow++;
        }
    }
    
    return slow;
}
```

**Example: Remove Duplicates from Sorted Array**

```python
# Python
def remove_duplicates(nums):
    if not nums:
        return 0
    
    slow = 0
    
    for fast in range(1, len(nums)):
        if nums[fast] != nums[slow]:
            slow += 1
            nums[slow] = nums[fast]
    
    return slow + 1
```

```javascript
// JavaScript
function removeDuplicates(nums) {
    if (nums.length === 0) return 0;
    
    let slow = 0;
    
    for (let fast = 1; fast < nums.length; fast++) {
        if (nums[fast] !== nums[slow]) {
            slow++;
            nums[slow] = nums[fast];
        }
    }
    
    return slow + 1;
}
```

```java
// Java
public int removeDuplicates(int[] nums) {
    if (nums.length == 0) return 0;
    
    int slow = 0;
    
    for (int fast = 1; fast < nums.length; fast++) {
        if (nums[fast] != nums[slow]) {
            slow++;
            nums[slow] = nums[fast];
        }
    }
    
    return slow + 1;
}
```

### Strategy 3: Sliding Window Pattern

**Template:**

```python
# Python
def sliding_window(arr):
    left = 0
    result = 0
    window_state = initialize_state()
    
    for right in range(len(arr)):
        # Expand window - add arr[right]
        update_state(window_state, arr[right])
        
        # Contract window while invalid
        while not is_valid(window_state):
            remove_from_state(window_state, arr[left])
            left += 1
        
        # Update result with current window
        result = max(result, right - left + 1)
    
    return result
```

```javascript
// JavaScript
function slidingWindow(arr) {
    let left = 0;
    let result = 0;
    let windowState = initializeState();
    
    for (let right = 0; right < arr.length; right++) {
        // Expand window - add arr[right]
        updateState(windowState, arr[right]);
        
        // Contract window while invalid
        while (!isValid(windowState)) {
            removeFromState(windowState, arr[left]);
            left++;
        }
        
        // Update result with current window
        result = Math.max(result, right - left + 1);
    }
    
    return result;
}
```

```java
// Java
public int slidingWindow(int[] arr) {
    int left = 0;
    int result = 0;
    StateType windowState = initializeState();
    
    for (int right = 0; right < arr.length; right++) {
        // Expand window - add arr[right]
        updateState(windowState, arr[right]);
        
        // Contract window while invalid
        while (!isValid(windowState)) {
            removeFromState(windowState, arr[left]);
            left++;
        }
        
        // Update result with current window
        result = Math.max(result, right - left + 1);
    }
    
    return result;
}
```

**Example: Longest Substring Without Repeating Characters**

```python
# Python
def length_of_longest_substring(s):
    char_set = set()
    left = 0
    max_length = 0
    
    for right in range(len(s)):
        # Contract window until no duplicates
        while s[right] in char_set:
            char_set.remove(s[left])
            left += 1
        
        # Add current character
        char_set.add(s[right])
        max_length = max(max_length, right - left + 1)
    
    return max_length
```

```javascript
// JavaScript
function lengthOfLongestSubstring(s) {
    const charSet = new Set();
    let left = 0;
    let maxLength = 0;
    
    for (let right = 0; right < s.length; right++) {
        // Contract window until no duplicates
        while (charSet.has(s[right])) {
            charSet.delete(s[left]);
            left++;
        }
        
        // Add current character
        charSet.add(s[right]);
        maxLength = Math.max(maxLength, right - left + 1);
    }
    
    return maxLength;
}
```

```java
// Java
public int lengthOfLongestSubstring(String s) {
    Set<Character> charSet = new HashSet<>();
    int left = 0;
    int maxLength = 0;
    
    for (int right = 0; right < s.length(); right++) {
        // Contract window until no duplicates
        while (charSet.contains(s.charAt(right))) {
            charSet.remove(s.charAt(left));
            left++;
        }
        
        // Add current character
        charSet.add(s.charAt(right));
        maxLength = Math.max(maxLength, right - left + 1);
    }
    
    return maxLength;
}
```

### Strategy 4: Fast and Slow Pattern

**Template:**

```python
# Python - Linked List
def fast_slow_linked_list(head):
    slow = head
    fast = head
    
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        
        if meets_condition(slow, fast):
            return result
    
    return default_result
```

```javascript
// JavaScript - Linked List
function fastSlowLinkedList(head) {
    let slow = head;
    let fast = head;
    
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
        
        if (meetsCondition(slow, fast)) {
            return result;
        }
    }
    
    return defaultResult;
}
```

```java
// Java - Linked List
public ResultType fastSlowLinkedList(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        
        if (meetsCondition(slow, fast)) {
            return result;
        }
    }
    
    return defaultResult;
}
```

**Example: Detect Cycle in Linked List**

```python
# Python
class ListNode:
    def __init__(self, val=0):
        self.val = val
        self.next = None

def has_cycle(head):
    if not head:
        return False
    
    slow = head
    fast = head
    
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        
        if slow == fast:
            return True
    
    return False
```

```javascript
// JavaScript
class ListNode {
    constructor(val = 0, next = null) {
        this.val = val;
        this.next = next;
    }
}

function hasCycle(head) {
    if (!head) return false;
    
    let slow = head;
    let fast = head;
    
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
        
        if (slow === fast) {
            return true;
        }
    }
    
    return false;
}
```

```java
// Java
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public boolean hasCycle(ListNode head) {
    if (head == null) return false;
    
    ListNode slow = head;
    ListNode fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        
        if (slow == fast) {
            return true;
        }
    }
    
    return false;
}
```

---

## Common Problem Types

### 1. Pair/Triplet Finding

**Problem:** Find two/three numbers that sum to target.

**Approach:**
- Sort the array first (if not sorted)
- Use opposite direction pointers
- For triplets, fix one element and use two pointers for the rest

**Example: Three Sum**

```python
# Python
def three_sum(nums):
    nums.sort()
    result = []
    
    for i in range(len(nums) - 2):
        # Skip duplicates for first element
        if i > 0 and nums[i] == nums[i-1]:
            continue
        
        left, right = i + 1, len(nums) - 1
        
        while left < right:
            total = nums[i] + nums[left] + nums[right]
            
            if total == 0:
                result.append([nums[i], nums[left], nums[right]])
                
                # Skip duplicates for second element
                while left < right and nums[left] == nums[left+1]:
                    left += 1
                # Skip duplicates for third element
                while left < right and nums[right] == nums[right-1]:
                    right -= 1
                
                left += 1
                right -= 1
            elif total < 0:
                left += 1
            else:
                right -= 1
    
    return result
```

```javascript
// JavaScript
function threeSum(nums) {
    nums.sort((a, b) => a - b);
    const result = [];
    
    for (let i = 0; i < nums.length - 2; i++) {
        // Skip duplicates for first element
        if (i > 0 && nums[i] === nums[i-1]) continue;
        
        let left = i + 1, right = nums.length - 1;
        
        while (left < right) {
            const total = nums[i] + nums[left] + nums[right];
            
            if (total === 0) {
                result.push([nums[i], nums[left], nums[right]]);
                
                // Skip duplicates
                while (left < right && nums[left] === nums[left+1]) left++;
                while (left < right && nums[right] === nums[right-1]) right--;
                
                left++;
                right--;
            } else if (total < 0) {
                left++;
            } else {
                right--;
            }
        }
    }
    
    return result;
}
```

```java
// Java
public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    
    for (int i = 0; i < nums.length - 2; i++) {
        // Skip duplicates for first element
        if (i > 0 && nums[i] == nums[i-1]) continue;
        
        int left = i + 1, right = nums.length - 1;
        
        while (left < right) {
            int total = nums[i] + nums[left] + nums[right];
            
            if (total == 0) {
                result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                
                // Skip duplicates
                while (left < right && nums[left] == nums[left+1]) left++;
                while (left < right && nums[right] == nums[right-1]) right--;
                
                left++;
                right--;
            } else if (total < 0) {
                left++;
            } else {
                right--;
            }
        }
    }
    
    return result;
}
```

### 2. Palindrome Problems

**Problem:** Check if string is palindrome or find longest palindrome.

**Approach:**
- Use opposite direction pointers
- Compare characters from both ends
- Move inward until they meet

**Example: Valid Palindrome**

```python
# Python
def is_palindrome(s):
    # Clean string: lowercase and alphanumeric only
    cleaned = ''.join(c.lower() for c in s if c.isalnum())
    
    left, right = 0, len(cleaned) - 1
    
    while left < right:
        if cleaned[left] != cleaned[right]:
            return False
        left += 1
        right -= 1
    
    return True
```

```javascript
// JavaScript
function isPalindrome(s) {
    // Clean string: lowercase and alphanumeric only
    const cleaned = s.toLowerCase().replace(/[^a-z0-9]/g, '');
    
    let left = 0, right = cleaned.length - 1;
    
    while (left < right) {
        if (cleaned[left] !== cleaned[right]) {
            return false;
        }
        left++;
        right--;
    }
    
    return true;
}
```

```java
// Java
public boolean isPalindrome(String s) {
    // Clean string: lowercase and alphanumeric only
    String cleaned = s.toLowerCase().replaceAll("[^a-z0-9]", "");
    
    int left = 0, right = cleaned.length() - 1;
    
    while (left < right) {
        if (cleaned.charAt(left) != cleaned.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }
    
    return true;
}
```

### 3. Container/Water Problems

**Problem:** Find maximum area or volume that can be contained.

**Approach:**
- Use opposite direction pointers
- Calculate area/volume at each step
- Move pointer with smaller height (greedy choice)

**Example: Container With Most Water**

```python
# Python
def max_area(height):
    left, right = 0, len(height) - 1
    max_water = 0
    
    while left < right:
        # Calculate current area
        width = right - left
        current_height = min(height[left], height[right])
        current_area = width * current_height
        max_water = max(max_water, current_area)
        
        # Move pointer with smaller height
        if height[left] < height[right]:
            left += 1
        else:
            right -= 1
    
    return max_water
```

```javascript
// JavaScript
function maxArea(height) {
    let left = 0, right = height.length - 1;
    let maxWater = 0;
    
    while (left < right) {
        // Calculate current area
        const width = right - left;
        const currentHeight = Math.min(height[left], height[right]);
        const currentArea = width * currentHeight;
        maxWater = Math.max(maxWater, currentArea);
        
        // Move pointer with smaller height
        if (height[left] < height[right]) {
            left++;
        } else {
            right--;
        }
    }
    
    return maxWater;
}
```

```java
// Java
public int maxArea(int[] height) {
    int left = 0, right = height.length - 1;
    int maxWater = 0;
    
    while (left < right) {
        // Calculate current area
        int width = right - left;
        int currentHeight = Math.min(height[left], height[right]);
        int currentArea = width * currentHeight;
        maxWater = Math.max(maxWater, currentArea);
        
        // Move pointer with smaller height
        if (height[left] < height[right]) {
            left++;
        } else {
            right--;
        }
    }
    
    return maxWater;
}
```

### 4. Partitioning/Segregation

**Problem:** Partition array based on a condition (e.g., even/odd, colors, pivot).

**Approach:**
- Use same direction pointers
- Slow pointer tracks position for next valid element
- Fast pointer explores array

**Example: Move Zeros to End**

```python
# Python
def move_zeros(nums):
    slow = 0  # Position for next non-zero
    
    for fast in range(len(nums)):
        if nums[fast] != 0:
            # Swap non-zero element to slow position
            nums[slow], nums[fast] = nums[fast], nums[slow]
            slow += 1
    
    return nums
```

```javascript
// JavaScript
function moveZeros(nums) {
    let slow = 0;  // Position for next non-zero
    
    for (let fast = 0; fast < nums.length; fast++) {
        if (nums[fast] !== 0) {
            // Swap non-zero element to slow position
            [nums[slow], nums[fast]] = [nums[fast], nums[slow]];
            slow++;
        }
    }
    
    return nums;
}
```

```java
// Java
public void moveZeros(int[] nums) {
    int slow = 0;  // Position for next non-zero
    
    for (int fast = 0; fast < nums.length; fast++) {
        if (nums[fast] != 0) {
            // Swap non-zero element to slow position
            int temp = nums[slow];
            nums[slow] = nums[fast];
            nums[fast] = temp;
            slow++;
        }
    }
}
```

### 5. Linked List Problems

**Problem:** Find middle, detect cycles, remove nth from end.

**Approach:**
- Use fast and slow pointers
- Fast moves 2x speed of slow
- When fast reaches end, slow is at middle

**Example: Find Middle of Linked List**

```python
# Python
def find_middle(head):
    slow = fast = head
    
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
    
    return slow  # Middle node
```

```javascript
// JavaScript
function findMiddle(head) {
    let slow = head, fast = head;
    
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
    }
    
    return slow;  // Middle node
}
```

```java
// Java
public ListNode findMiddle(ListNode head) {
    ListNode slow = head, fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    
    return slow;  // Middle node
}
```

---

## Time and Space Complexity

### Typical Complexities

| Pattern | Time Complexity | Space Complexity | Notes |
|---------|----------------|------------------|-------|
| Opposite Direction | O(n) | O(1) | Single pass from both ends |
| Same Direction | O(n) | O(1) | Single pass, in-place |
| Sliding Window | O(n) | O(k) | k = window state size |
| Fast/Slow | O(n) | O(1) | May traverse list twice |
| With Sorting | O(n log n) | O(1) or O(n) | Depends on sort algorithm |

### Complexity Analysis Examples

**Two Sum (Sorted Array):**
- Time: O(n) - single pass with two pointers
- Space: O(1) - only pointer variables

**Three Sum:**
- Time: O(n²) - O(n) for outer loop × O(n) for two pointers
- Space: O(1) - ignoring output space

**Longest Substring Without Repeating:**
- Time: O(n) - each character visited at most twice
- Space: O(min(n, m)) - where m is charset size

---

## Problem-Solving Framework

### Step-by-Step Approach

#### 1. Identify the Pattern

Ask yourself:
- Is the data sorted or can it be sorted?
- Do I need to find pairs/triplets?
- Am I looking for a subarray/substring?
- Is this about partitioning or removing elements?
- Does the problem involve a linked list?

#### 2. Choose the Right Variant

Match problem characteristics to pattern:
- **Sorted + pairs** → Opposite direction
- **In-place modification** → Same direction
- **Substring/subarray** → Sliding window
- **Linked list cycles** → Fast/slow

#### 3. Define Pointer Initialization

Decide starting positions:
```python
# Opposite direction
left, right = 0, len(arr) - 1

# Same direction
slow, fast = 0, 0

# Sliding window
start, end = 0, 0

# Fast/slow linked list
slow = fast = head
```

#### 4. Define Movement Logic

Determine when to move each pointer:
- What condition triggers left/slow movement?
- What condition triggers right/fast movement?
- Should both move simultaneously?

#### 5. Define Termination Condition

When should the loop stop?
```python
# Opposite direction
while left < right:

# Same direction
for fast in range(len(arr)):

# Sliding window
while end < len(arr):

# Fast/slow
while fast and fast.next:
```

#### 6. Handle Edge Cases

Consider:
- Empty input
- Single element
- All elements same
- No valid answer exists

### Decision Tree for Pattern Selection

```
Start
  │
  ├─ Is data sorted?
  │   ├─ Yes → Looking for pairs/sum?
  │   │         ├─ Yes → Opposite Direction
  │   │         └─ No → Check other factors
  │   └─ No → Can you sort it efficiently?
  │            ├─ Yes → Consider sorting + two pointers
  │            └─ No → Check if same direction works
  │
  ├─ Need to modify array in-place?
  │   └─ Yes → Same Direction
  │
  ├─ Finding substring/subarray with property?
  │   └─ Yes → Sliding Window
  │
  └─ Working with linked list?
      └─ Yes → Fast/Slow
