# Tree Data Structure - Complete Guide

## Table of Contents
1. [Introduction](#introduction)
2. [Tree Terminology](#tree-terminology)
3. [Types of Trees](#types-of-trees)
4. [Tree Traversal Techniques](#tree-traversal-techniques)
5. [Java Implementation](#java-implementation)
6. [Applications](#applications)

---

## Introduction

A **Tree** is a hierarchical, non-linear data structure consisting of nodes connected by edges. It's one of the most important data structures in computer science, used extensively in databases, file systems, artificial intelligence, and many other domains.

### Key Characteristics
- One node is designated as the **root** node
- Every node (except root) is connected by exactly one edge from another node
- There is exactly one path between the root and any node
- No cycles exist in the structure

---

## Tree Terminology

### Basic Terms

**Node**: The fundamental unit of a tree containing data and references to child nodes.

**Root**: The topmost node in a tree with no parent. Every tree has exactly one root.

**Parent**: A node that has one or more child nodes connected below it.

**Child**: A node directly connected to another node when moving away from the root.

**Siblings**: Nodes that share the same parent.

**Leaf (External Node)**: A node with no children.

**Internal Node**: A node with at least one child.

**Edge**: The connection between two nodes.

**Path**: A sequence of nodes and edges connecting a node with a descendant.

**Height of a Node**: The number of edges on the longest path from that node to a leaf. Leaf nodes have height 0.

**Height of a Tree**: The height of the root node, representing the longest path from root to any leaf.

**Depth of a Node**: The number of edges from the root to that node. The root has depth 0.

**Level**: All nodes at the same depth. The root is at level 0.

**Subtree**: A tree formed by a node and all its descendants.

**Degree of a Node**: The number of children a node has.

**Ancestor**: Any node on the path from the root to a given node.

**Descendant**: Any node reachable by moving downward from a given node.

---

## Types of Trees

### 1. Binary Tree
A tree where each node has at most two children, referred to as left child and right child.

### 2. Binary Search Tree (BST)
A binary tree where:
- Left subtree contains only nodes with values less than the parent node
- Right subtree contains only nodes with values greater than the parent node
- Both left and right subtrees are also binary search trees

### 3. AVL Tree
A self-balancing binary search tree where the heights of left and right subtrees of any node differ by at most one.

### 4. Red-Black Tree
A self-balancing binary search tree with an extra bit for color (red or black) used to ensure the tree remains balanced during insertions and deletions.

### 5. B-Tree
A self-balancing tree optimized for systems that read and write large blocks of data, commonly used in databases.

### 6. Heap
A complete binary tree where each node satisfies the heap property (max-heap or min-heap).

### 7. Trie (Prefix Tree)
A tree used to store strings where each path down the tree represents a prefix of stored strings.

### 8. N-ary Tree
A tree where each node can have at most N children.

---

## Tree Traversal Techniques

Tree traversal refers to visiting all nodes in a tree in a specific order. There are two main categories:

### Depth-First Search (DFS) Traversals

#### 1. Inorder Traversal (Left-Root-Right)
Process the left subtree, then the root, then the right subtree.

**Use Case**: For BSTs, inorder traversal produces nodes in sorted order.

**Algorithm**:
```
1. Traverse the left subtree
2. Visit the root
3. Traverse the right subtree
```

#### 2. Preorder Traversal (Root-Left-Right)
Process the root first, then the left subtree, then the right subtree.

**Use Case**: Used to create a copy of the tree or get prefix expression of an expression tree.

**Algorithm**:
```
1. Visit the root
2. Traverse the left subtree
3. Traverse the right subtree
```

#### 3. Postorder Traversal (Left-Right-Root)
Process the left subtree, then the right subtree, then the root.

**Use Case**: Used to delete the tree or get postfix expression of an expression tree.

**Algorithm**:
```
1. Traverse the left subtree
2. Traverse the right subtree
3. Visit the root
```

### Breadth-First Search (BFS) Traversals

#### Level Order Traversal
Visit nodes level by level from left to right.

**Use Case**: Finding the shortest path, level-wise processing.

**Algorithm**:
```
1. Use a queue to track nodes
2. Start with root
3. For each node, process it and add its children to queue
4. Continue until queue is empty
```

---

## Java Implementation

### Basic Binary Tree Node

```java
class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
```

### Binary Tree Class with Traversals

```java
import java.util.Queue;
import java.util.LinkedList;

class BinaryTree {
    TreeNode root;
    
    public BinaryTree() {
        this.root = null;
    }
    
    // Inorder Traversal (Recursive)
    public void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.print(node.data + " ");
        inorderTraversal(node.right);
    }
    
    // Preorder Traversal (Recursive)
    public void preorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }
    
    // Postorder Traversal (Recursive)
    public void postorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.data + " ");
    }
    
    // Level Order Traversal (BFS)
    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.data + " ");
            
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }
    
    // Calculate height of tree
    public int height(TreeNode node) {
        if (node == null) {
            return -1; // height of empty tree is -1
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    // Count total nodes
    public int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
    
    // Search for a value
    public boolean search(TreeNode node, int value) {
        if (node == null) {
            return false;
        }
        if (node.data == value) {
            return true;
        }
        return search(node.left, value) || search(node.right, value);
    }
}
```

### Binary Search Tree Implementation

```java
class BinarySearchTree {
    TreeNode root;
    
    public BinarySearchTree() {
        this.root = null;
    }
    
    // Insert a node
    public TreeNode insert(TreeNode node, int data) {
        if (node == null) {
            return new TreeNode(data);
        }
        
        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        }
        
        return node;
    }
    
    // Search for a value in BST
    public boolean search(TreeNode node, int value) {
        if (node == null) {
            return false;
        }
        
        if (node.data == value) {
            return true;
        } else if (value < node.data) {
            return search(node.left, value);
        } else {
            return search(node.right, value);
        }
    }
    
    // Find minimum value node
    public TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    // Delete a node
    public TreeNode delete(TreeNode node, int value) {
        if (node == null) {
            return null;
        }
        
        if (value < node.data) {
            node.left = delete(node.left, value);
        } else if (value > node.data) {
            node.right = delete(node.right, value);
        } else {
            // Node to be deleted found
            
            // Case 1: No children (leaf node)
            if (node.left == null && node.right == null) {
                return null;
            }
            
            // Case 2: One child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            
            // Case 3: Two children
            TreeNode minNode = findMin(node.right);
            node.data = minNode.data;
            node.right = delete(node.right, minNode.data);
        }
        
        return node;
    }
    
    // Inorder traversal (gives sorted order for BST)
    public void inorder(TreeNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }
}
```

### Complete Example Usage

```java
public class TreeExample {
    public static void main(String[] args) {
        // Binary Tree Example
        BinaryTree bt = new BinaryTree();
        bt.root = new TreeNode(1);
        bt.root.left = new TreeNode(2);
        bt.root.right = new TreeNode(3);
        bt.root.left.left = new TreeNode(4);
        bt.root.left.right = new TreeNode(5);
        
        System.out.println("Binary Tree Traversals:");
        System.out.print("Inorder: ");
        bt.inorderTraversal(bt.root);
        System.out.println();
        
        System.out.print("Preorder: ");
        bt.preorderTraversal(bt.root);
        System.out.println();
        
        System.out.print("Postorder: ");
        bt.postorderTraversal(bt.root);
        System.out.println();
        
        System.out.print("Level Order: ");
        bt.levelOrderTraversal();
        System.out.println();
        
        System.out.println("Height: " + bt.height(bt.root));
        System.out.println("Total Nodes: " + bt.countNodes(bt.root));
        
        // Binary Search Tree Example
        BinarySearchTree bst = new BinarySearchTree();
        bst.root = bst.insert(bst.root, 50);
        bst.insert(bst.root, 30);
        bst.insert(bst.root, 70);
        bst.insert(bst.root, 20);
        bst.insert(bst.root, 40);
        bst.insert(bst.root, 60);
        bst.insert(bst.root, 80);
        
        System.out.println("\nBinary Search Tree:");
        System.out.print("Inorder (Sorted): ");
        bst.inorder(bst.root);
        System.out.println();
        
        System.out.println("Search 40: " + bst.search(bst.root, 40));
        System.out.println("Search 90: " + bst.search(bst.root, 90));
        
        bst.delete(bst.root, 30);
        System.out.print("After deleting 30: ");
        bst.inorder(bst.root);
        System.out.println();
    }
}
```

### Iterative Traversals (Using Stack)

```java
import java.util.Stack;

class IterativeTraversals {
    
    // Iterative Inorder
    public void inorderIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            System.out.print(current.data + " ");
            current = current.right;
        }
    }
    
    // Iterative Preorder
    public void preorderIterative(TreeNode root) {
        if (root == null) return;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            System.out.print(current.data + " ");
            
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }
    
    // Iterative Postorder (Two Stack Method)
    public void postorderIterative(TreeNode root) {
        if (root == null) return;
        
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        
        while (!stack1.isEmpty()) {
            TreeNode current = stack1.pop();
            stack2.push(current);
            
            if (current.left != null) {
                stack1.push(current.left);
            }
            if (current.right != null) {
                stack1.push(current.right);
            }
        }
        
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + " ");
        }
    }
}
```

---

## Applications

### 1. File Systems
Operating systems use tree structures to organize files and directories. Each directory can contain files and subdirectories, forming a hierarchical tree structure.

### 2. Database Indexing
B-Trees and B+ Trees are extensively used in database systems for indexing, allowing fast data retrieval with logarithmic time complexity.

### 3. Expression Parsing
Trees are used to represent and evaluate mathematical and logical expressions. Expression trees allow easy evaluation and manipulation of complex expressions.

### 4. Artificial Intelligence
Decision trees are fundamental in machine learning for classification and regression tasks. Game trees are used in AI for game playing algorithms like minimax.

### 5. Network Routing
Routing algorithms use tree structures to find optimal paths between network nodes. Spanning trees are used to prevent loops in network topologies.

### 6. Compression Algorithms
Huffman coding uses binary trees to compress data efficiently by assigning shorter codes to more frequent characters.

### 7. DOM (Document Object Model)
Web browsers use tree structures to represent HTML and XML documents, where each element is a node in the tree.

### 8. Autocomplete and Search
Tries (prefix trees) are used in autocomplete features, spell checkers, and IP routing tables for efficient prefix matching.

### 9. Organization Hierarchies
Corporate structures, family trees, and taxonomies are naturally represented as trees showing hierarchical relationships.

### 10. Compiler Design
Abstract Syntax Trees (AST) are used in compilers to represent the structure of program code for analysis and optimization.

### 11. Priority Queues
Heaps (a type of tree) efficiently implement priority queues used in scheduling algorithms, Dijkstra's algorithm, and heap sort.

### 12. Social Networks
Tree structures can represent follower relationships, organizational connections, and hierarchical social structures.

---

## Time Complexity Analysis

### Binary Search Tree (Average Case - Balanced)
- **Search**: O(log n)
- **Insert**: O(log n)
- **Delete**: O(log n)
- **Traversal**: O(n)

### Binary Search Tree (Worst Case - Skewed)
- **Search**: O(n)
- **Insert**: O(n)
- **Delete**: O(n)

### Balanced Trees (AVL, Red-Black)
- **Search**: O(log n) - guaranteed
- **Insert**: O(log n) - guaranteed
- **Delete**: O(log n) - guaranteed

### Space Complexity
- **All Tree Operations**: O(h) for recursion stack, where h is height
- **Level Order Traversal**: O(w) for queue, where w is maximum width

---

## Best Practices

1. **Use appropriate tree type**: Choose BST for sorted data, heap for priority queues, trie for string operations.

2. **Consider balance**: For frequent insertions/deletions, use self-balancing trees to maintain O(log n) operations.

3. **Recursion vs Iteration**: Recursive implementations are cleaner but may cause stack overflow for very deep trees.

4. **Null checks**: Always validate nodes before accessing properties to avoid NullPointerException.

5. **Choose the right traversal**: Use inorder for BST sorted output, level order for shortest path, postorder for deletion.

6. **Memory management**: Be mindful of memory usage in large trees; consider iterative approaches for better space efficiency.

---

## Conclusion

Trees are fundamental data structures that provide efficient solutions to many computational problems. Understanding tree terminology, traversal techniques, and various tree types allows you to choose the right tool for specific applications. The hierarchical nature of trees makes them indispensable in computer science, from file systems to databases to artificial intelligence.
