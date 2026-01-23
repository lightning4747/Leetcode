# 🧠 LeetCode 133 – Clone Graph

## 🔢 LeetCode Number
**133**

## 🔗 Problem Link
https://leetcode.com/problems/clone-graph/

---

## 📘 Problem Statement

Given a reference of a node in a **connected undirected graph**, return a **deep copy (clone)** of the graph.

Each node in the graph contains:
- an integer value `val`
- a list of its neighbors

### Graph Representation
- Nodes are labeled from **1 to N**
- The graph is **undirected**
- The given node is always the **first node (val = 1)**

---

## 🖼️ Graph Visualization

Original Graph:

![Original Graph](https://assets.leetcode.com/uploads/2019/11/04/133_clone_graph_question.png)

Cloned Graph:

![Cloned Graph](https://assets.leetcode.com/uploads/2019/11/04/133_clone_graph_answer.png)

---

## 🧠 Intuition

At first glance, this looks like a **simple traversal problem**, but the challenge is:

> 🔴 **How do we avoid cloning the same node multiple times?**

Since the graph may contain:
- **cycles**
- **multiple references to the same node**

We need:
- a way to **remember already-cloned nodes**
- ensure **deep copy**, not shallow reference

This naturally leads to:
> ✅ **Graph Traversal + HashMap**

---

## 💡 Core Idea

1. Traverse the graph using **DFS or BFS**
2. Maintain a **HashMap**:
   - `original node → cloned node`
3. If a node is already cloned:
   - return the cloned version
4. Otherwise:
   - create a new node
   - recursively clone its neighbors

---

## 🧩 Thinking View (How to Approach)

- This is **not** a normal DFS
- We are building a **parallel graph**
- Every time we visit a node:
  - check if its clone already exists
- If yes → reuse it
- If no → create it and continue

> Think of it as **copying a complex object with internal references**

---

## 🐌 Brute Force Approach (Incorrect / Naive)

### ❌ Idea
- Traverse the graph
- Create a new node for every visit

### ❌ Problem
- Cycles cause **infinite recursion**
- Same node cloned **multiple times**
- Graph structure breaks

### ❌ Conclusion
Brute force **fails** without memoization.

---

## ⚡ Optimized Solution (DFS + HashMap)

### ✔️ Approach
- Use **DFS**
- Use a `HashMap<Node, Node>` to track clones

---

### 🧑‍💻 Java Code (Optimized)

```java
class Solution {
    Map<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // If already cloned, return it
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // Clone the node
        Node clone = new Node(node.val);
        map.put(node, clone);

        // Clone neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }

        return clone;
    }
}
````

---

## 🔁 Algorithm Flow (Step-by-Step Example)

### Input Graph

```
1 -- 2
|    |
4 -- 3
```

### Step 1

Start cloning node `1`

* Create `1'`
* Store: `{1 → 1'}`

### Step 2

Go to neighbor `2`

* Create `2'`
* Store: `{2 → 2'}`

### Step 3

From `2`, visit `3`

* Create `3'`

### Step 4

From `3`, visit `4`

* Create `4'`

### Step 5

From `4`, visit `1`

* Already cloned → reuse `1'`

✅ Cycle handled safely
✅ Structure preserved
✅ Deep copy achieved

---

## ⏱️ Complexity Analysis

### Time Complexity

```
O(N + E)
```

* N = number of nodes
* E = number of edges

### Space Complexity

```
O(N)
```

* HashMap + recursion stack

---

## 🧠 Key Takeaways

* Graph cloning **requires memory**
* Always track visited nodes in graphs with cycles
* HashMap is your best friend here 🧩
* DFS and BFS both work (DFS shown here)

---

## ✅ Final Verdict

✔️ Efficient
✔️ Clean
✔️ Interview-ready

This is a **classic graph cloning problem** that tests:

* graph traversal
* deep copy understanding
* cycle handling

---
